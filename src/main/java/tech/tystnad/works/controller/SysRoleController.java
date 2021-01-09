package tech.tystnad.works.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import tech.tystnad.works.core.validator.groups.SysRoleGroups.addGroup;
import tech.tystnad.works.core.validator.groups.SysRoleGroups.queryGroup;
import tech.tystnad.works.core.validator.groups.SysRoleGroups.updateGroup;
import tech.tystnad.works.model.PageEntity;
import tech.tystnad.works.model.ResponseObjectEntity;
import tech.tystnad.works.model.dto.SysRoleDTO;
import tech.tystnad.works.model.vo.SysAuthorityTreeVO;
import tech.tystnad.works.model.vo.SysRoleVO;
import tech.tystnad.works.service.SysRoleService;

import java.util.List;

@PreAuthorize("hasAuthority('ROLE') or hasAuthority('MANAGER')")
@RestController("/role")
public class SysRoleController {

    private final SysRoleService sysRoleService;

    @Autowired
    public SysRoleController(SysRoleService sysRoleService) {
        this.sysRoleService = sysRoleService;
    }

    @PreAuthorize("hasAuthority('ROLE.CREATE') or hasAuthority('MANAGER')")
    @PostMapping
    ResponseObjectEntity<SysRoleVO> create(
            @RequestBody @Validated(addGroup.class) SysRoleDTO sysRoleDTO) {
        return sysRoleService.save(sysRoleDTO);
    }

    @PreAuthorize("hasAuthority('ROLE.UPDATE') or hasAuthority('MANAGER')")
    @PutMapping
    ResponseObjectEntity<SysRoleVO> save(
            @RequestBody @Validated(updateGroup.class) SysRoleDTO sysRoleDTO) {
        return sysRoleService.save(sysRoleDTO);
    }

    @PreAuthorize("hasAuthority('ROLE.DELETE') or hasAuthority('MANAGER')")
    @DeleteMapping("/{id}")
    public ResponseObjectEntity<?> delete(@PathVariable("id") Long sysRoleId) {
        return sysRoleService.delete(sysRoleId);
    }


    @PreAuthorize("hasAuthority('ROLE.DELETE') or hasAuthority('MANAGER')")
    @DeleteMapping
    public ResponseObjectEntity<?> delete(@RequestBody List<Long> sysRoleIds) {
        return sysRoleService.delete(sysRoleIds);
    }

    @PreAuthorize("hasAuthority('ROLE.READ') or hasAuthority('MANAGER')")
    @GetMapping("/{id}")
    public ResponseObjectEntity<SysRoleVO> search(@PathVariable("id") Long sysRoleId) {
        return sysRoleService.search(sysRoleId);
    }

    @PreAuthorize("hasAuthority('ROLE.READ') or hasAuthority('MANAGER')")
    @GetMapping
    public ResponseObjectEntity<SysRoleVO> search(
            @RequestBody @Validated(queryGroup.class) SysRoleDTO sysRoleDTO,
            @RequestParam("page") Integer current, @RequestParam("range") Integer range) {
        PageEntity pageEntity = new PageEntity(current, range);
        return sysRoleService.search(sysRoleDTO, pageEntity);
    }

    @PreAuthorize("hasAuthority('ROLE.READ') or hasAuthority('MANAGER')")
    @GetMapping("/authority")
    public ResponseObjectEntity<SysAuthorityTreeVO> authorityTree() {
        return sysRoleService.authorityTree();
    }

    @PreAuthorize("hasAuthority('ROLE.READ') or hasAuthority('MANAGER')")
    @GetMapping("/authority/{id}")
    public ResponseObjectEntity<SysAuthorityTreeVO> authorityTree(@PathVariable("id") Long roleId) {
        return sysRoleService.authorityTree(roleId);
    }
}
