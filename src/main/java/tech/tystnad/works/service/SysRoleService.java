package tech.tystnad.works.service;

import tech.tystnad.works.model.ResponseObjectEntity;
import tech.tystnad.works.model.dto.SysRoleDTO;
import tech.tystnad.works.model.vo.SysRoleVO;

import java.util.List;

public interface SysRoleService {
    ResponseObjectEntity<SysRoleVO> save(SysRoleDTO sysRoleDTO);

    ResponseObjectEntity<SysRoleVO> delete(Long sysRoleId);

    ResponseObjectEntity<SysRoleVO> delete(List<Long> sysRoleIds);

    ResponseObjectEntity<List<SysRoleVO>> search(Long sysRoleId);

    ResponseObjectEntity<List<SysRoleVO>> search(SysRoleDTO sysRoleDTO);
}
