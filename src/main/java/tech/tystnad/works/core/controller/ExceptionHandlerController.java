package tech.tystnad.works.core.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import tech.tystnad.works.core.exception.BusinessException;
import tech.tystnad.works.model.ResponseObjectEntity;

import java.sql.SQLException;

@ControllerAdvice
@ResponseBody
public class ExceptionHandlerController {

    private final Logger logger = LoggerFactory.getLogger(getClass());
    /**
     * 若消息需要自定义业务状态码,使用`符合分隔消息和状态码
     * 消息定义在ValidationMessages.properties配置文件
     */
    private static final String MESSAGE_SPLIT = "`";

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseObjectEntity<Object> dataValidatorHandler(MethodArgumentNotValidException e) {
        // 获取第一个错误
        FieldError error = e.getBindingResult().getFieldErrors().get(0);
        String message = error.getDefaultMessage();
        ResponseObjectEntity<Object> entity = new ResponseObjectEntity<>();
        if (message.contains(MESSAGE_SPLIT)) {
            // 根据拆分标志符来拆分响应代码和消息
            String[] code_message = message.split(MESSAGE_SPLIT);
            entity.setCode(Integer.valueOf(code_message[0]));
            entity.setMsg(code_message[1]);
        } else {
            // 无分隔符默认的返回
            entity.setCode(400);
            entity.setMsg(message);
        }
        return entity;
    }

    /**
     * 数据库访问错误
     * @param e
     * @return
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({SQLException.class, DataAccessException.class})
    public ResponseObjectEntity<Object> sqlHandler(SQLException e) {
        logger.error(e.toString(), e);
        ResponseObjectEntity<Object> entity = new ResponseObjectEntity<>();
        entity.setCode(400);
        entity.setMsg("执行数据操作有误");
        return entity;
    }

    /**
     * 业务异常
     * @param e
     * @return
     */
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(BusinessException.class)
    public ResponseObjectEntity<Object> businessHandler(BusinessException e) {
        logger.error(e.toString(), e);
        ResponseObjectEntity<Object> entity = new ResponseObjectEntity<>();
        entity.setCode(-1);
        entity.setMsg(e.getMessage());
        return entity;
    }

    /**
     * 通用错误异常,当spring检测不到具体的ExceptionHandler时,才会默认使用这个ExceptionHandler进行处理
     * @param e
     * @return
     */
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public ResponseObjectEntity<Object> defaultHandler(Exception e) {
        logger.error(e.toString(), e);
        ResponseObjectEntity<Object> entity = new ResponseObjectEntity<>();
        entity.setCode(500);
        entity.setMsg("服务器内部错误,请稍后重试");
        return entity;
    }
}
