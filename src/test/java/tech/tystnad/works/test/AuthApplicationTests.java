package tech.tystnad.works.test;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import tech.tystnad.works.model.dto.SysUserDTO;
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
import java.util.Calendar;
import java.util.List;

@SpringBootTest
class AuthApplicationTests {

    @Resource
    private SysOrganizationDOMapper sysOrganizationDOMapper;

    @Resource
    private SysOrgUserDOMapper sysOrgUserDOMapper;

    @Resource
    private SysUserVOMapper sysUserVOMapper;

    @Resource
    private IdWorker idWorker;

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
