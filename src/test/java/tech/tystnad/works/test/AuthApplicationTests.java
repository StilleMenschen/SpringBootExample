package tech.tystnad.works.test;

import org.junit.jupiter.api.Test;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.boot.test.context.SpringBootTest;
import tech.tystnad.works.model.SysOrganization;
import tech.tystnad.works.repository.SysOrganizationRepository;
import tech.tystnad.works.util.IdWorker;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest
class AuthApplicationTests {

    @Resource
    private SqlSessionTemplate sqlSessionTemplate;

    @Resource
    private IdWorker idWorker;

    @Test
    void testSysOrganization() {
        SysOrganization organization = new SysOrganization();
        SysOrganizationRepository repository = sqlSessionTemplate.getMapper(SysOrganizationRepository.class);
        organization.setTop_id(0);
        organization.setParent_id(0);
        organization.setUpdater(1);
        organization.setCreator(1);
        for (int i = 10; i < 21; i++) {
            long id = idWorker.nextId();
            organization.setOrg_id(id);
            organization.setOrg_level(0);
            organization.setOrg_name("测试机构"+ i);
            repository.addOnce(organization);
        }
        System.out.println(repository.countAll(new SysOrganization()));
        List<SysOrganization> list = repository.findAll(new SysOrganization(),0,5);
        list.forEach(e->{
            System.out.println(e);
            repository.delById(e.getOrg_id());
        });
        System.out.println(repository.countAll(new SysOrganization()));
    }

}
