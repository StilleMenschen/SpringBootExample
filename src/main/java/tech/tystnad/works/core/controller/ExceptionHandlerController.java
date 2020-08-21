package tech.tystnad.works.core.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import tech.tystnad.works.model.ResponseObjectEntity;

import java.util.List;

@RestController
@ControllerAdvice
public class ExceptionHandlerController {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResponseObjectEntity<Object>> dataValidatorException(MethodArgumentNotValidException e) {
        List<FieldError> list = e.getBindingResult().getFieldErrors();
        // 获取第一个错误
        FieldError error = list.get(list.size() - 1);
        ResponseObjectEntity<Object> entity = new ResponseObjectEntity<>();
        entity.setCode(400);
        entity.setMsg(error.getDefaultMessage());
        return ResponseEntity.badRequest().body(entity);
    }
}
