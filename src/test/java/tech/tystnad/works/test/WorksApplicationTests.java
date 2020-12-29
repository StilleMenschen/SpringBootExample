package tech.tystnad.works.test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hibernate.validator.HibernateValidator;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.TestingAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import tech.tystnad.works.config.AuthorityCodeConfig;
import tech.tystnad.works.core.validator.groups.SysOrganizationGroups;
import tech.tystnad.works.core.validator.groups.SysOrganizationGroups.queryGroup;
import tech.tystnad.works.core.validator.groups.SysRoleGroups;
import tech.tystnad.works.model.JwtUser;
import tech.tystnad.works.model.PageEntity;
import tech.tystnad.works.model.dto.SysOrganizationDTO;
import tech.tystnad.works.model.dto.SysRoleDTO;
import tech.tystnad.works.model.dto.SysUserDTO;
import tech.tystnad.works.model.vo.SysAuthorityTreeVO;
import tech.tystnad.works.repository.domain.SysRoleDO;
import tech.tystnad.works.repository.mapper.SysOrganizationVOMapper;
import tech.tystnad.works.repository.mapper.SysRoleDOMapper;
import tech.tystnad.works.repository.mapper.SysRoleVOMapper;
import tech.tystnad.works.repository.mapper.SysUserVOMapper;
import tech.tystnad.works.service.RoleAuthorityRelationshipService;
import tech.tystnad.works.service.SysAuthorityService;
import tech.tystnad.works.service.SysRoleService;
import tech.tystnad.works.util.IdWorker;
import tech.tystnad.works.util.TimeUtils;

import javax.annotation.Resource;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.*;

@SpringBootTest
class WorksApplicationTests {

    private static final Logger logger = LoggerFactory.getLogger(WorksApplicationTests.class);

    private static final Map<Long, Object> tempMap = new HashMap<>();

    @Resource
    private SysOrganizationVOMapper sysOrganizationVOMapper;
    @Resource
    private SysRoleVOMapper sysRoleVOMapper;
    @Resource
    private SysRoleDOMapper sysRoleDOMapper;
    @Resource
    private SysRoleService sysRoleService;
    @Resource
    private SysAuthorityService sysAuthorityService;
    @Resource
    private RoleAuthorityRelationshipService roleAuthorityRelationshipService;
    @Resource
    private SysUserVOMapper sysUserVOMapper;
    @Resource
    private IdWorker idWorker;

    @BeforeEach
    public void on() {
        final long id = idWorker.nextId();
        UserDetails userDetails = new JwtUser(idWorker.nextId(), 0L, id, "", "",
                Boolean.TRUE, "", Collections.emptyList(), new Date());
        TestingAuthenticationToken authentication = new TestingAuthenticationToken(userDetails, null);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        SysRoleDO sysRoleDO = new SysRoleDO();
        sysRoleDO.setRoleId(idWorker.nextId());
        sysRoleDO.setTopId(0L);
        sysRoleDO.setOrgId(id);
        sysRoleDO.setCreator(idWorker.nextId());
        sysRoleDOMapper.insertSelective(sysRoleDO);
        final List<Short> authorityIds = new LinkedList<>();
        AuthorityCodeConfig.keySet().forEach(e -> authorityIds.add(Short.valueOf(AuthorityCodeConfig.getString(e))));
        roleAuthorityRelationshipService.save(sysRoleDO.getRoleId(), authorityIds);
        tempMap.put(1L, sysRoleDO);
    }

    @AfterEach
    public void off() {
        SysRoleDO sysRoleDO = new SysRoleDO();
        sysRoleDO = (SysRoleDO) tempMap.get(1L);
        sysRoleDOMapper.deleteByPrimaryKey(sysRoleDO.getRoleId());
        roleAuthorityRelationshipService.delete(sysRoleDO.getRoleId());
    }

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
        logger.info("count={}", sysOrganizationVOMapper.countByDTO(sysOrganizationDTO));
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
        logger.info("count={}", sysRoleVOMapper.countByDTO(sysRoleDTO));
        logger.info("haseCode={}", sysRoleVOMapper.findByDTO(sysRoleDTO, pageEntity).hashCode());
    }


    @Test
    public void testSysAuthority() {
        List<SysAuthorityTreeVO> results = sysRoleService.authorityTree().getValues();
        SysRoleDO sysRoleDO = new SysRoleDO();
        sysRoleDO = (SysRoleDO) tempMap.get(1L);
        ObjectMapper mapper = new ObjectMapper();
        try {
            logger.info(mapper.writeValueAsString(results));
            results = sysRoleService.authorityTree(sysRoleDO.getRoleId()).getValues();
            logger.info(mapper.writeValueAsString(results));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testRoleAuthorityRelationship() {
        final long roleId = idWorker.nextId();
        final List<Short> list = new LinkedList<>();
        final SysRoleDO sysRoleDO = new SysRoleDO();
        list.add((short) 10000);
        list.add((short) 10001);
        list.add((short) 10002);
        list.add((short) 10003);
        sysRoleDO.setRoleId(roleId);
        sysRoleDO.setRoleName("我带你们打2");
        sysRoleDO.setTopId(0L);
        sysRoleDO.setOrgId(idWorker.nextId());
        sysRoleDO.setCreator(idWorker.nextId());
        sysRoleDOMapper.insertSelective(sysRoleDO);
        roleAuthorityRelationshipService.save(roleId, list);
        roleAuthorityRelationshipService.search(roleId).getValues().forEach(e -> logger.info("RoleId={}, AuthId={}", e.getRoleId(), e.getAuthId()));
        sysRoleDOMapper.deleteByPrimaryKey(roleId);
        roleAuthorityRelationshipService.delete(roleId);
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
        logger.info("count={}", sysUserVOMapper.countByDTO(sysUserDTO));
        logger.info("haseCode={}", sysUserVOMapper.findByDTO(sysUserDTO, pageEntity).hashCode());
    }
}
