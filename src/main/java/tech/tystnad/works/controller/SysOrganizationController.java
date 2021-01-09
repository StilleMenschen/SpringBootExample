package tech.tystnad.works.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import tech.tystnad.works.model.PageEntity;
import tech.tystnad.works.model.ResponseObjectEntity;
import tech.tystnad.works.model.dto.SysOrganizationDTO;
import tech.tystnad.works.model.vo.SysOrganizationVO;
import tech.tystnad.works.service.SysOrganizationService;

import java.util.List;

@PreAuthorize("hasAuthority('ORGANIZATION') or hasAuthority('MANAGER')")
@RestController("/organization")
public class SysOrganizationController {

    private final SysOrganizationService sysOrganizationService;

    @Autowired
    public SysOrganizationController(SysOrganizationService sysOrganizationService) {
        this.sysOrganizationService = sysOrganizationService;
    }

    @PreAuthorize("hasAuthority('ORGANIZATION.CREATE') or hasAuthority('MANAGER')")
    @PostMapping
    public ResponseObjectEntity<SysOrganizationVO> create(@RequestBody SysOrganizationDTO sysOrganizationDTO) {
        return sysOrganizationService.save(sysOrganizationDTO);
    }

    @PreAuthorize("hasAuthority('ORGANIZATION.UPDATE') or hasAuthority('MANAGER')")
    @PutMapping
    public ResponseObjectEntity<SysOrganizationVO> save(@RequestBody SysOrganizationDTO sysOrganizationDTO) {
        return sysOrganizationService.save(sysOrganizationDTO);
    }

    @PreAuthorize("hasAuthority('ORGANIZATION.DELETE') or hasAuthority('MANAGER')")
    @DeleteMapping("/{id}")
    public ResponseObjectEntity<?> delete(@PathVariable("id") Long sysOrganizationId) {
        return sysOrganizationService.delete(sysOrganizationId);
    }

    @PreAuthorize("hasAuthority('ORGANIZATION.DELETE') or hasAuthority('MANAGER')")
    @DeleteMapping
    public ResponseObjectEntity<?> deleteMany(@RequestBody List<Long> sysOrganizationIds) {
        return sysOrganizationService.delete(sysOrganizationIds);
    }

    @PreAuthorize("hasAuthority('ORGANIZATION.READ') or hasAuthority('MANAGER')")
    @GetMapping("/{id}")
    public ResponseObjectEntity<SysOrganizationVO> search(@PathVariable("id") Long sysOrganizationId) {
        return sysOrganizationService.search(sysOrganizationId);
    }

    @PreAuthorize("hasAuthority('ORGANIZATION.READ') or hasAuthority('MANAGER')")
    @GetMapping
    public ResponseObjectEntity<SysOrganizationVO> search(@RequestBody SysOrganizationDTO sysOrganizationDTO,
                                                          @RequestParam("page") Integer current,
                                                          @RequestParam("range") Integer range) {
        PageEntity pageEntity = new PageEntity(current, range);
        return sysOrganizationService.search(sysOrganizationDTO, pageEntity);
    }
}
