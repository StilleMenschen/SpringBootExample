package tech.tystnad.works.core.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tech.tystnad.works.model.PageEntity;
import tech.tystnad.works.model.ResponseObjectEntity;

public class BaseService {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 处理成功
     * @param code 响应码
     * @param message 消息
     * @param body 响应体
     * @param <T> 响应对象类型
     * @return
     */
    protected <T> ResponseObjectEntity<T> success(int code, String message, T body) {
        return success(code, message, body, null);
    }

    /**
     * 处理成功
     * @param code 响应码
     * @param message 消息
     * @param body 响应体
     * @param page 翻页
     * @param <T> 响应对象类型
     * @return
     */
    protected <T> ResponseObjectEntity<T> success(int code, String message, T body, PageEntity page) {
        ResponseObjectEntity<T> responseObjectEntity = new ResponseObjectEntity<>();
        responseObjectEntity.setCode(code);
        responseObjectEntity.setMsg(message);
        responseObjectEntity.setValues(body);
        responseObjectEntity.setPage(page);
        return responseObjectEntity;
    }

    /**
     * 响应成功,采用默认的响应码和空的响应消息
     * @param body 响应体
     * @param <T>
     * @return
     */
    protected <T> ResponseObjectEntity<T> ok(T body) {
        return ok(body, null);
    }

    /**
     * 响应成功,采用默认的响应码和空的响应消息
     * @param body 响应体
     * @param page 翻页
     * @param <T>
     * @return
     */
    protected <T> ResponseObjectEntity<T> ok(T body, PageEntity page) {
        return success(0, "", body, page);
    }

    /**
     * 处理失败响应
     * @param code 响应码
     * @param message 错误消息
     * @param <T> 响应对象类型
     * @return
     */
    protected <T> ResponseObjectEntity<T> fail(int code, String message) {
        logger.warn("Processing failed, {}", message);
        ResponseObjectEntity<T> responseObjectEntity = new ResponseObjectEntity<>();
        responseObjectEntity.setCode(code);
        responseObjectEntity.setMsg(message);
        return responseObjectEntity;
    }
}
