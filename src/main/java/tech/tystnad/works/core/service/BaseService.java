package tech.tystnad.works.core.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import tech.tystnad.works.model.JwtUser;
import tech.tystnad.works.model.PageEntity;
import tech.tystnad.works.model.ResponseObjectEntity;

public class BaseService {
    private static final Logger logger = LoggerFactory.getLogger(BaseService.class);

    /**
     * 处理成功
     *
     * @param code    响应码
     * @param message 消息
     * @param body    响应体
     * @param <T>     响应对象类型
     * @return
     */
    protected <T> ResponseObjectEntity<T> success(int code, String message, T body) {
        return success(code, message, body, null);
    }

    /**
     * 处理成功
     *
     * @param code    响应码
     * @param message 消息
     * @param body    响应体
     * @param page    翻页
     * @param <T>     响应对象类型
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
     *
     * @param body 响应体
     * @param <T>
     * @return
     */
    protected <T> ResponseObjectEntity<T> ok(T body) {
        return ok(body, null);
    }

    /**
     * 响应成功,采用默认的响应码和空的响应消息
     *
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
     *
     * @param code    响应码
     * @param message 错误消息
     * @param <T>     响应对象类型
     * @return
     */
    protected <T> ResponseObjectEntity<T> fail(int code, String message) {
        logger.warn("Processing failed, {}", message);
        ResponseObjectEntity<T> responseObjectEntity = new ResponseObjectEntity<>();
        responseObjectEntity.setCode(code);
        responseObjectEntity.setMsg(message);
        return responseObjectEntity;
    }

    /**
     * 获取当前的用户信息
     *
     * @return 当前用户信息
     */
    protected UserDetails getCurrentUser() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof JwtUser) {
            JwtUser jwtUser = (JwtUser) principal;
            logger.info("locate JwtUser {} {}", jwtUser.getId(), jwtUser.getUsername());
            return jwtUser;
        } else if (principal instanceof UserDetails) {
            UserDetails userDetails = (UserDetails) principal;
            logger.info("locate UserDetails {}", userDetails.getUsername());
            return userDetails;
        }
        logger.warn("UserDetails not found");
        return null;
    }
}
