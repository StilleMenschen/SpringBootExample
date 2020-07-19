package tech.tystnad.works.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tech.tystnad.works.model.PageEntity;
import tech.tystnad.works.model.ResponseObjectEntity;

public class BaseService {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    protected <T> ResponseObjectEntity<T> success(int code, String message, T body) {
        ResponseObjectEntity<T> responseObjectEntity = new ResponseObjectEntity<>();
        responseObjectEntity.setCode(code);
        responseObjectEntity.setMsg(message);
        responseObjectEntity.setValues(body);
        return responseObjectEntity;
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
        ResponseObjectEntity<T> responseObjectEntity = new ResponseObjectEntity<>();
        responseObjectEntity.setCode(200);
        responseObjectEntity.setMsg("");
        responseObjectEntity.setValues(body);
        return responseObjectEntity;
    }

    protected <T> ResponseObjectEntity<T> ok(T body, PageEntity page) {
        ResponseObjectEntity<T> responseObjectEntity = new ResponseObjectEntity<>();
        responseObjectEntity.setCode(200);
        responseObjectEntity.setMsg("");
        responseObjectEntity.setValues(body);
        responseObjectEntity.setPage(page);
        return responseObjectEntity;
    }

    protected <T> ResponseObjectEntity<T> fail(int code, String message) {
        logger.warn("Processing failed, {}", message);
        ResponseObjectEntity<T> responseObjectEntity = new ResponseObjectEntity<>();
        responseObjectEntity.setCode(code);
        responseObjectEntity.setMsg(message);
        return responseObjectEntity;
    }
}
