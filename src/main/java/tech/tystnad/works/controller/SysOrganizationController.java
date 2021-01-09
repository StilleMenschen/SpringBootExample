package tech.tystnad.works.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import tech.tystnad.works.core.validator.groups.SysOrganizationGroups.addGroup;
import tech.tystnad.works.core.validator.groups.SysOrganizationGroups.queryGroup;
import tech.tystnad.works.core.validator.groups.SysOrganizationGroups.updateGroup;
import tech.tystnad.works.model.PageEntity;
import tech.tystnad.works.model.ResponseObjectEntity;
import tech.tystnad.works.model.dto.SysOrganizationDTO;
import tech.tystnad.works.model.vo.SysOrganizationVO;
import tech.tystnad.works.service.SysOrganizationService;

import java.util.List;
import java.util.Map;

@PreAuthorize("hasAuthority('ORGANIZATION') or hasAuthority('MANAGER')")
@RestController
@RequestMapping("/organization")
public class SysOrganizationController {

    private final SysOrganizationService sysOrganizationService;

    @Autowired
    public SysOrganizationController(SysOrganizationService sysOrganizationService) {
        this.sysOrganizationService = sysOrganizationService;
    }

    @PreAuthorize("hasAuthority('ORGANIZATION.CREATE') or hasAuthority('MANAGER')")
    @PostMapping
    public ResponseObjectEntity<SysOrganizationVO> create(
            @RequestBody @Validated(addGroup.class) SysOrganizationDTO sysOrganizationDTO) {
        return sysOrganizationService.save(sysOrganizationDTO);
    }

    @PreAuthorize("hasAuthority('ORGANIZATION.UPDATE') or hasAuthority('MANAGER')")
    @PutMapping
    public ResponseObjectEntity<SysOrganizationVO> save(
            @RequestBody @Validated(updateGroup.class) SysOrganizationDTO sysOrganizationDTO) {
        return sysOrganizationService.save(sysOrganizationDTO);
    }

    @PreAuthorize("hasAuthority('ORGANIZATION.DELETE') or hasAuthority('MANAGER')")
    @DeleteMapping("/{id}")
    public ResponseObjectEntity<?> delete(@PathVariable("id") Long sysOrganizationId) {
        return sysOrganizationService.delete(sysOrganizationId);
    }

    @PreAuthorize("hasAuthority('ORGANIZATION.DELETE') or hasAuthority('MANAGER')")
    @DeleteMapping
    public ResponseObjectEntity<?> delete(@RequestBody List<Long> sysOrganizationIds) {
        return sysOrganizationService.delete(sysOrganizationIds);
    }

    @PreAuthorize("hasAuthority('ORGANIZATION.READ') or hasAuthority('MANAGER')")
    @GetMapping("/{id}")
    public ResponseObjectEntity<SysOrganizationVO> search(@PathVariable("id") Long sysOrganizationId) {
        return sysOrganizationService.search(sysOrganizationId);
    }

    @PreAuthorize("hasAuthority('ORGANIZATION.READ') or hasAuthority('MANAGER')")
    @GetMapping
    public ResponseObjectEntity<SysOrganizationVO> search(
            @Validated(queryGroup.class) SysOrganizationDTO sysOrganizationDTO,
            @RequestParam("page") Integer current, @RequestParam("range") Integer range) {
        PageEntity pageEntity = new PageEntity(current, range);
        return sysOrganizationService.search(sysOrganizationDTO, pageEntity);
    }

    @PreAuthorize("hasAuthority('ORGANIZATION.READ') or hasAuthority('MANAGER')")
    @GetMapping("/tree")
    public ResponseObjectEntity<Map<String, Object>> tree(@RequestParam("top") Long topId, @RequestParam("parent") Long parentId) {
        return sysOrganizationService.tree(topId, parentId);
    }
}
