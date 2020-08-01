package tech.tystnad.works.test;

import org.junit.jupiter.api.Test;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.boot.test.context.SpringBootTest;
import tech.tystnad.works.model.vo.SysUserVO;
import tech.tystnad.works.repository.domain.SysOrgUserDO;
import tech.tystnad.works.repository.domain.SysOrgUserDOExample;
import tech.tystnad.works.repository.domain.SysOrganizationDO;
import tech.tystnad.works.repository.domain.SysOrganizationDOExample;
import tech.tystnad.works.repository.mapper.SysOrgUserDOMapper;
import tech.tystnad.works.repository.mapper.SysOrganizationDOMapper;
import tech.tystnad.works.repository.mapper.SysUserVOMapper;
import tech.tystnad.works.util.IdWorker;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.List;

@SpringBootTest
class AuthApplicationTests {

    @Resource
    private SysOrganizationDOMapper sysOrganizationDOMapper;

    @Resource
    private SysOrgUserDOMapper sysOrgUserDOMapper;

    @Resource
    private IdWorker idWorker;

    @Resource
    private SqlSessionTemplate sqlSessionTemplate;

    @Test
    public void testSysOrganization() {
        for (int i = 1; i <= 10; i++) {
            SysOrganizationDO organization = new SysOrganizationDO();
            organization.setOrgId(idWorker.nextId());
            organization.setOrgLevel((byte) 0);
            organization.setOrgName("org" + i);
            sysOrganizationDOMapper.insert(organization);
        }
        SysOrganizationDOExample example = new SysOrganizationDOExample();
        example.setOrderByClause("create_time desc");
        List<SysOrganizationDO> list = sysOrganizationDOMapper.selectByExample(example);
        SimpleDateFormat format = new SimpleDateFormat("E yyyy年MM月dd日 a h:mm");
        list.forEach(e -> System.out.println(e.getOrgId() + ":" + e.getOrgName() + ":" + format.format(e.getCreateTime())));
        SysOrganizationDO organization = new SysOrganizationDO();
        organization.setDeleted(true);
        System.out.println("deleted " + sysOrganizationDOMapper.updateByExampleSelective(organization, example));
    }

    @Test
    public void testSysUser() {
        String username = "aaa";
        SysOrgUserDOExample example = new SysOrgUserDOExample();
        example.createCriteria().andDeletedEqualTo(false).andUserNameEqualTo(username);
        example.or().andDeletedEqualTo(false).andEmailEqualTo(username);
        List<SysOrgUserDO> sysOrgUserDOList = sysOrgUserDOMapper.selectByExample(example);
        System.out.println(sysOrgUserDOList);
    }

    @Test
    public void testSysUserVO() {
        SysUserVOMapper mapper = sqlSessionTemplate.getMapper(SysUserVOMapper.class);
        SysUserVO example = new SysUserVO();
        example.setUserName("UserName");
        example.setOrgName("OrgName");
        example.setNickname("Nickname");
        example.setRoleName("RoleName");
        List<SysUserVO> list = mapper.findSysUserByExample(example);
        System.out.println(list.size());
    }
}
