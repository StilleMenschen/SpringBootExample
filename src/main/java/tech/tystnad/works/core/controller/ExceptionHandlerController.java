package tech.tystnad.works.core.controller;

import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import tech.tystnad.works.model.ResponseObjectEntity;

import java.util.List;

@ControllerAdvice
@ResponseBody
public class ExceptionHandlerController {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseObjectEntity<Object> dataValidatorException(MethodArgumentNotValidException e) {
        List<FieldError> list = e.getBindingResult().getFieldErrors();
        // 获取第一个错误
        FieldError error = list.get(list.size() - 1);
        ResponseObjectEntity<Object> entity = new ResponseObjectEntity<>();
        entity.setCode(400);
        entity.setMsg(error.getDefaultMessage());
        return entity;
    }
}
