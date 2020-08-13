package tech.tystnad.works.core.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tech.tystnad.works.enums.ResponseMessage;
import tech.tystnad.works.model.PageEntity;
import tech.tystnad.works.model.ResponseObjectEntity;

public class BaseService {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    protected <T> ResponseObjectEntity<T> success(int code, String message, T body) {
        return success(code, message, body, null);
    }

    protected <T> ResponseObjectEntity<T> success(int code, String message, T body, PageEntity page) {
        ResponseObjectEntity<T> responseObjectEntity = new ResponseObjectEntity<>();
        responseObjectEntity.setCode(code);
        responseObjectEntity.setMsg(message);
        responseObjectEntity.setValues(body);
        responseObjectEntity.setPage(page);
        return responseObjectEntity;
    }

    protected <T> ResponseObjectEntity<T> ok(T body) {
        return ok(body, null);
    }

    protected <T> ResponseObjectEntity<T> ok(T body, PageEntity page) {
        return success(0, "", body, page);
    }

    protected <T> ResponseObjectEntity<T> fail(int code, String message) {
        logger.warn("Processing failed, {}", message);
        ResponseObjectEntity<T> responseObjectEntity = new ResponseObjectEntity<>();
        responseObjectEntity.setCode(code);
        responseObjectEntity.setMsg(message);
        return responseObjectEntity;
    }

    protected <T> ResponseObjectEntity<T> fail(ResponseMessage message) {
        return fail(message.getCode(), message.getMsg());
    }
}
