package tech.tystnad.works.service;

import tech.tystnad.works.model.PageEntity;
import tech.tystnad.works.model.ResponseObjectEntity;
import tech.tystnad.works.model.dto.SysRoleDTO;
import tech.tystnad.works.model.vo.SysRoleVO;

import java.util.List;
import java.util.Map;

public interface SysRoleService {
    ResponseObjectEntity<SysRoleVO> save(SysRoleDTO sysRoleDTO);

    ResponseObjectEntity<?> delete(Long sysRoleId);

    ResponseObjectEntity<?> delete(List<Long> sysRoleIds);

    ResponseObjectEntity<SysRoleVO> search(Long sysRoleId);

    ResponseObjectEntity<SysRoleVO> search(SysRoleDTO sysRoleDTO, PageEntity pageEntity);

    ResponseObjectEntity<Map<String, Object>> authorityTree();

    ResponseObjectEntity<Map<String, Object>> authorityTree(Long roleId);
}
