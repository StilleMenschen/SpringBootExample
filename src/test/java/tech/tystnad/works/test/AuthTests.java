package tech.tystnad.works.test;

import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import tech.tystnad.works.model.ResponseObjectEntity;
import tech.tystnad.works.repository.domain.SysUserDO;
import tech.tystnad.works.service.BaseService;
import tech.tystnad.works.util.IdWorker;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.UUID;

public class AuthTests {

    class TestService extends BaseService {
        public ResponseObjectEntity<SysUserDO> show() {
            return fail(301, "");
        }
    }

    @Test
    public void example() {

    }


    @Test
    public void password() {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String[] credentials = new String[]{
                "123456",
                "654321",
                "712587623457652798365",
                "beautiful"
        };
        LinkedList<String> cipherText = new LinkedList<>();
        for (String s : credentials) {
            cipherText.add(encoder.encode(s));
        }

        cipherText.forEach(e -> {
            System.out.println(e.length());
            System.out.println(e);
        });
    }

    @Test
    public void id() {
        final int end = 100;
        IdWorker idWorker = new IdWorker(0, 0);
        for (int i = 0; i < end; i++) {
            long id = idWorker.nextId();
            System.out.println(id);
            System.out.println(Long.toString(id, Character.MAX_RADIX));
        }
        System.out.println(1592530867244L);
        System.out.println(System.currentTimeMillis());
    }

    @Test
    public void uuid() {
        final int end = 100;
        Map<String, Long> map = new HashMap<>();
        long count = 0;
        String temp;
        for (int i = 0; i < end; i++) {
            temp = UUID.randomUUID().toString();
            if (map.containsKey(temp)) {
//                System.out.println(i + " - " + temp + " exists!");
                count++;
            } else {
                map.put(temp, System.currentTimeMillis());
            }
        }
        System.out.println(count);
    }
}
