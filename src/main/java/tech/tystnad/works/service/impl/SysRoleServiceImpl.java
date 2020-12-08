package tech.tystnad.works.service.impl;

import tech.tystnad.works.model.ResponseObjectEntity;
import tech.tystnad.works.model.dto.SysOrganizationDTO;
import tech.tystnad.works.model.dto.SysRoleDTO;
import tech.tystnad.works.model.vo.SysRoleVO;
import tech.tystnad.works.service.SysRoleService;

import java.util.List;

public class SysRoleServiceImpl implements SysRoleService {
    @Override
    public ResponseObjectEntity<SysRoleVO> save(SysRoleDTO sysRoleDTO) {
        return null;
    }

    @Override
    public ResponseObjectEntity<SysRoleVO> delete(Long sysRoleId) {
        return null;
    }

    @Override
    public ResponseObjectEntity<SysRoleVO> delete(List<Long> sysRoleIds) {
        return null;
    }

    @Override
    public ResponseObjectEntity<List<SysRoleVO>> search(Long sysOrganizationId) {
        return null;
    }

    @Override
    public ResponseObjectEntity<List<SysRoleVO>> search(SysOrganizationDTO sysOrganizationDTO) {
        return null;
    }
}
