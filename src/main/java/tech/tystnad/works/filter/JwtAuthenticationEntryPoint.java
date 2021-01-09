package tech.tystnad.works.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import tech.tystnad.works.model.ResponseObjectEntity;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Serializable;

@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint, Serializable {

    private static final long serialVersionUID = -8970718410437077606L;

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException) throws IOException {
        // 这个异常在未授权用户访问安全资源时会触发，我们会发出一个401未授权的response
//      response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
        ObjectMapper mapper = new ObjectMapper();
        ResponseObjectEntity<Object> entity = new ResponseObjectEntity<>();
        entity.setCode(HttpServletResponse.SC_UNAUTHORIZED);
        entity.setMsg("未授权");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().append(mapper.writeValueAsString(entity)).flush();
    }
}
