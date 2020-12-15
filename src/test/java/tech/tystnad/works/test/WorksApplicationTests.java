package tech.tystnad.works.test;

import org.hibernate.validator.HibernateValidator;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import tech.tystnad.works.core.validator.groups.SysOrganizationGroups;
import tech.tystnad.works.core.validator.groups.SysOrganizationGroups.queryGroup;
import tech.tystnad.works.core.validator.groups.SysRoleGroups;
import tech.tystnad.works.model.PageEntity;
import tech.tystnad.works.model.dto.SysOrganizationDTO;
import tech.tystnad.works.model.dto.SysRoleDTO;
import tech.tystnad.works.model.dto.SysUserDTO;
import tech.tystnad.works.repository.mapper.SysOrganizationVOMapper;
import tech.tystnad.works.repository.mapper.SysRoleVOMapper;
import tech.tystnad.works.repository.mapper.SysUserVOMapper;
import tech.tystnad.works.util.IdWorker;
import tech.tystnad.works.util.TimeUtils;

import javax.annotation.Resource;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Date;
import java.util.Set;

@SpringBootTest
class WorksApplicationTests {

    private static final Logger logger = LoggerFactory.getLogger(WorksApplicationTests.class);

    @Resource
    private SysOrganizationVOMapper sysOrganizationVOMapper;
    @Resource
    private SysRoleVOMapper sysRoleVOMapper;
    @Resource
    private SysUserVOMapper sysUserVOMapper;
    @Resource
    private IdWorker idWorker;

    @Test
    public void testValidator() {
        Validator validator = Validation.byProvider(HibernateValidator.class).configure().buildValidatorFactory().getValidator();
        logger.debug("--SysOrganizationDTO--");
        SysOrganizationDTO sysOrganizationDTO = new SysOrganizationDTO();
        sysOrganizationDTO.setOrgId(idWorker.nextId());
        sysOrganizationDTO.setTopId(idWorker.nextId());
        sysOrganizationDTO.setParentId(idWorker.nextId());
        sysOrganizationDTO.setOrgName("   华尔兹街道布伦茨大夏十四楼305号坎迪拉恩小店  ");
        sysOrganizationDTO.setOrgLevel((byte) 7);
        Set<ConstraintViolation<SysOrganizationDTO>> orgConstraintViolation = validator.validate(sysOrganizationDTO, SysOrganizationGroups.updateGroup.class);
        orgConstraintViolation.forEach(e -> logger.debug(e.toString()));
        sysOrganizationDTO.setOrgName("     ");
        orgConstraintViolation = validator.validate(sysOrganizationDTO, queryGroup.class);
        orgConstraintViolation.forEach(e -> logger.debug(e.toString()));
        logger.debug("--SysOrganizationDTO--END--");
        logger.debug("--SysRoleDTO--");
        SysRoleDTO sysRoleDTO = new SysRoleDTO();
        sysRoleDTO.setRoleId(idWorker.nextId());
        sysRoleDTO.setOrgId(null);
        sysRoleDTO.setTopId(idWorker.nextId());
        sysRoleDTO.setRoleName("  华尔兹街道布伦茨大夏十四楼305号坎迪拉恩小店   ");
        Set<ConstraintViolation<SysRoleDTO>> roleConstraintViolation = validator.validate(sysRoleDTO, SysRoleGroups.updateGroup.class);
        roleConstraintViolation.forEach(e -> logger.debug(e.toString()));
        sysRoleDTO.setRoleName("   ");
        roleConstraintViolation = validator.validate(sysRoleDTO, SysRoleGroups.queryGroup.class);
        roleConstraintViolation.forEach(e -> logger.debug(e.toString()));
        logger.debug("--SysRoleDTO--END--");
    }

    @Test
    public void testSysOrganization() {
        SysOrganizationDTO sysOrganizationDTO = new SysOrganizationDTO();
        sysOrganizationDTO.setOrgLevel((byte) 3);
        sysOrganizationDTO.setOrgName("xxx的传统的艺能");
        sysOrganizationDTO.setCreatorName("xxx的传统的艺能");
        sysOrganizationDTO.setUpdaterName("xxx的传统的艺能");
        sysOrganizationDTO.setEnabled(Boolean.TRUE);
        Date c = new Date(System.currentTimeMillis());
        sysOrganizationDTO.setCreateTimeStart(TimeUtils.get(c, 0, 0, 0, 0));
        sysOrganizationDTO.setCreateTimeEnd(TimeUtils.get(c, 23, 59, 59, 999));
        PageEntity pageEntity = new PageEntity(3, 15);
        logger.info("haseCode={}", sysOrganizationVOMapper.findByDTO(sysOrganizationDTO, pageEntity).hashCode());
    }

    @Test
    public void testSysRole() {
        SysRoleDTO sysRoleDTO = new SysRoleDTO();
        sysRoleDTO.setOrgName("我带你们打");
        sysRoleDTO.setCreatorName("我带你们打");
        sysRoleDTO.setUpdaterName("我带你们打");
        Date c = new Date(System.currentTimeMillis());
        sysRoleDTO.setCreateTimeStart(TimeUtils.get(c, 0, 0, 0, 0));
        sysRoleDTO.setCreateTimeEnd(TimeUtils.get(c, 23, 59, 59, 999));
        PageEntity pageEntity = new PageEntity(3, 15);
        logger.info("haseCode={}", sysRoleVOMapper.findByDTO(sysRoleDTO, pageEntity).hashCode());
    }

    @Test
    public void testSysUser() {
        SysUserDTO sysUserDTO = new SysUserDTO();
        sysUserDTO.setUserName("伞兵一号lbw准备就绪");
        sysUserDTO.setOrgName("伞兵一号lbw准备就绪");
        sysUserDTO.setUpdaterName("伞兵一号lbw准备就绪");
        sysUserDTO.setCreatorName("伞兵一号lbw准备就绪");
        sysUserDTO.setEnabled(Boolean.TRUE);
        Date c = new Date(System.currentTimeMillis());
        sysUserDTO.setCreateTimeStart(TimeUtils.get(c, 0, 0, 0, 0));
        sysUserDTO.setCreateTimeEnd(TimeUtils.get(c, 23, 59, 59, 999));
        PageEntity pageEntity = new PageEntity(3, 15);
        logger.info("haseCode={}", sysUserVOMapper.findByDTO(sysUserDTO, pageEntity).hashCode());
    }
}
