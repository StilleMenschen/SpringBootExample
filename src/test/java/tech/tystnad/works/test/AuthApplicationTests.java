package tech.tystnad.works.test;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import tech.tystnad.works.repository.domain.SysOrganizationDO;
import tech.tystnad.works.repository.domain.SysOrganizationDOExample;
import tech.tystnad.works.repository.mapper.SysOrganizationDOMapper;
import tech.tystnad.works.util.IdWorker;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.List;

@SpringBootTest
class AuthApplicationTests {

    @Resource
    private SysOrganizationDOMapper mapper;

    @Resource
    private IdWorker idWorker;

    @Test
    void testSysOrganization() {
        for (int i = 1; i <= 10; i++) {
            SysOrganizationDO organization = new SysOrganizationDO();
            organization.setOrgId(idWorker.nextId());
            organization.setOrgLevel((byte) 0);
            organization.setOrgName("org" + i);
            mapper.insert(organization);
        }
        SysOrganizationDOExample example = new SysOrganizationDOExample();
        example.setOrderByClause("create_time desc");
        List<SysOrganizationDO> list = mapper.selectByExample(example);
        SimpleDateFormat format = new SimpleDateFormat("E yyyy年MM月dd日 a h:mm");
        list.forEach(e -> System.out.println(e.getOrgId() + ":" + e.getOrgName() + ":" + format.format(e.getCreateTime())));
        SysOrganizationDO organization = new SysOrganizationDO();
        organization.setDeleted(true);
        System.out.println("deleted " + mapper.updateByExampleSelective(organization, example));
    }

}
