package tech.tystnad.works.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;
import tech.tystnad.works.model.ResponseObjectEntity;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtAuthenticationAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException exc)
            throws IOException {
//      response.sendError(HttpServletResponse.SC_FORBIDDEN, "Access denied");
        ObjectMapper mapper = new ObjectMapper();
        ResponseObjectEntity<Object> entity = new ResponseObjectEntity<>();
        entity.setCode(HttpServletResponse.SC_FORBIDDEN);
        entity.setMsg("无权限");
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().append(mapper.writeValueAsString(entity)).flush();
    }
}