package tech.tystnad.works.core.exception;

/**
 * 业务处理异常
 */
public class BusinessException extends Exception {
    public BusinessException() {
        super();
    }

    public BusinessException(String message) {
        super(message);
    }
}
