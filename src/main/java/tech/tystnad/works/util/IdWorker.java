package tech.tystnad.works.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class IdWorker {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    private long workerId;
    private long dataCenterId;
    private long sequence;
    private long lastTimestamp = -1L;

    public IdWorker(long workerId, long dataCenterId) {
        this(workerId, dataCenterId, 0L);
    }

    public IdWorker(long workerId, long dataCenterId, long sequence) {
        // sanity check for workerId
        if (workerId > maxWorkerId || workerId < 0) {
            throw new IllegalArgumentException(
                    String.format("worker id can't be greater than %d or less than 0", maxWorkerId));
        }
        if (dataCenterId > maxDataCenterId || dataCenterId < 0) {
            throw new IllegalArgumentException(
                    String.format("datacenter id can't be greater than %d or less than 0", maxDataCenterId));
        }
        logger.debug("id worker starting. timestamp left shift {}, datacenter id bits {}, worker id bits {}, sequence bits {}, workerid {}",
                timestampLeftShift, dataCenterIdBits, workerIdBits, sequenceBits, workerId);

        this.workerId = workerId;
        this.dataCenterId = dataCenterId;
        this.sequence = sequence;
    }

    private static final long works_epoch = 1604067624344L;

    // 工作机器ID,二进制5位
    private static final long workerIdBits = 5L;
    // 数据中心ID,二进制5位
    private static final long dataCenterIdBits = 5L;
    private static final long maxWorkerId = ~(-1L << workerIdBits);
    private static final long maxDataCenterId = ~(-1L << dataCenterIdBits);
    // 每毫秒序列号,二进制12位
    private static final long sequenceBits = 12L;

    private static final long workerIdShift = sequenceBits;
    private static final long dataCenterIdShift = sequenceBits + workerIdBits;
    private static final long timestampLeftShift = sequenceBits + workerIdBits + dataCenterIdBits;
    private static final long sequenceMask = ~(-1L << sequenceBits);

    public synchronized long nextId() {
        long timestamp = timeGen();

        if (timestamp < lastTimestamp) {
            logger.error("Clock is moving backwards. Rejecting requests until {}.", lastTimestamp);
            throw new RuntimeException(
                    String.format("Clock moved backwards. Refusing to generate id for %d milliseconds",
                            lastTimestamp - timestamp));
        }

        if (lastTimestamp == timestamp) {
            sequence = (sequence + 1) & sequenceMask;
            if (sequence == 0) {
                timestamp = tilNextMillis(lastTimestamp);
            }
        } else {
            sequence = 0;
        }

        lastTimestamp = timestamp;
        final long ID = ((timestamp - works_epoch) << timestampLeftShift) |
                (dataCenterId << dataCenterIdShift) |
                (workerId << workerIdShift) |
                sequence;
        logger.debug("Next ID {}, lastTimestamp {}", ID, lastTimestamp);
        return ID;
    }

    private long tilNextMillis(long lastTimestamp) {
        long timestamp = timeGen();
        while (timestamp <= lastTimestamp) {
            timestamp = timeGen();
        }
        return timestamp;
    }

    private long timeGen() {
        return System.currentTimeMillis();
    }
}