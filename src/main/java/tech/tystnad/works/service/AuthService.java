package tech.tystnad.works.service;

import tech.tystnad.works.model.ResponseObjectEntity;
import tech.tystnad.works.model.dto.SysUserDTO;
import tech.tystnad.works.model.vo.SysUserVO;

public interface AuthService {
    ResponseObjectEntity<SysUserVO> register(SysUserDTO sysUserDTO);
    String login(String username, String password);
    String refresh(String oldToken);
}
