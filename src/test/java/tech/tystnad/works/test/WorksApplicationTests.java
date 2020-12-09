package tech.tystnad.works.test;

import org.hibernate.validator.HibernateValidator;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import tech.tystnad.works.core.validator.groups.SysOrganizationGroups;
import tech.tystnad.works.core.validator.groups.SysOrganizationGroups.queryGroup;
import tech.tystnad.works.core.validator.groups.SysRoleGroups;
import tech.tystnad.works.model.dto.SysOrganizationDTO;
import tech.tystnad.works.model.dto.SysRoleDTO;
import tech.tystnad.works.model.dto.SysUserDTO;
import tech.tystnad.works.repository.domain.SysOrganizationDO;
import tech.tystnad.works.repository.domain.SysOrganizationDOExample;
import tech.tystnad.works.repository.mapper.SysOrganizationDOMapper;
import tech.tystnad.works.repository.mapper.SysUserVOMapper;
import tech.tystnad.works.util.IdWorker;

import javax.annotation.Resource;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

@SpringBootTest
class WorksApplicationTests {

    private static final Logger logger = LoggerFactory.getLogger(WorksApplicationTests.class);

    @Resource
    private SysOrganizationDOMapper sysOrganizationDOMapper;

    @Resource
    private SysUserVOMapper sysUserVOMapper;

    @Resource
    private IdWorker idWorker;

    @Test
    public void testValidator() {
        Validator validator = Validation.byProvider(HibernateValidator.class).configure().buildValidatorFactory().getValidator();
        SysOrganizationDTO sysOrganizationDTO = new SysOrganizationDTO();
        sysOrganizationDTO.setOrgId(idWorker.nextId());
        sysOrganizationDTO.setTopId(idWorker.nextId());
        sysOrganizationDTO.setParentId(idWorker.nextId());
        sysOrganizationDTO.setOrgName("   华尔兹街道布伦茨大夏十四楼305号坎迪拉恩小店  ");
        sysOrganizationDTO.setOrgLevel((byte) 7);
        Set<ConstraintViolation<SysOrganizationDTO>> orgConstraintViolation = validator.validate(sysOrganizationDTO, SysOrganizationGroups.updateGroup.class);
        orgConstraintViolation.forEach(e -> logger.debug(e.toString()));
        logger.debug("----");
        sysOrganizationDTO.setOrgName("     ");
        orgConstraintViolation = validator.validate(sysOrganizationDTO, queryGroup.class);
        orgConstraintViolation.forEach(e -> logger.debug(e.toString()));
        logger.debug("----");
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
        logger.debug("----");
    }

    @Test
    public void testSysOrganization() {
        List<Long> organizationIds = new LinkedList<>();
        for (int i = 0; i < 10; i++) {
            SysOrganizationDO organization = new SysOrganizationDO();
            organizationIds.add(idWorker.nextId());
            organization.setOrgId(organizationIds.get(i));
            organization.setOrgLevel((byte) 0);
            organization.setOrgName("啊啊啊啊啊啊啊" + i);
            logger.debug("count {}", sysOrganizationDOMapper.insertSelective(organization));
        }
        SysOrganizationDOExample example = new SysOrganizationDOExample();
        example.setOrderByClause("create_time desc");
        List<SysOrganizationDO> list = sysOrganizationDOMapper.selectByExample(example);
        SimpleDateFormat format = new SimpleDateFormat("E yyyy年MM月dd日 a h:mm");
        list.forEach(e -> logger.debug(e.getOrgId() + ":" + e.getOrgName().length() + ":" + format.format(e.getCreateTime())));
        SysOrganizationDO organization = new SysOrganizationDO();
        organization.setDeleted(true);
        example.clear();
        example.createCriteria().andOrgIdIn(organizationIds.subList(0, 5)).andDeletedEqualTo(false);
        logger.debug("deleted " + sysOrganizationDOMapper.updateByExampleSelective(organization, example));
    }

    @Test
    public void example() {
        SysUserDTO sysUserDTO = new SysUserDTO();
        sysUserDTO.setUserName("伞兵一号lbw准备就绪");
        sysUserDTO.setOrgName("伞兵一号lbw准备就绪");
        sysUserDTO.setUpdaterName("伞兵一号lbw准备就绪");
        sysUserDTO.setCreatorName("伞兵一号lbw准备就绪");
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        sysUserDTO.setCreateTimeStart(calendar.getTime());
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 999);
        sysUserDTO.setCreateTimeEnd(calendar.getTime());
        sysUserVOMapper.findByExample(sysUserDTO);
    }
}
