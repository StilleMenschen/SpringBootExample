package tech.tystnad.works.test;

import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.client.RestTemplate;
import tech.tystnad.works.util.IdWorker;

import java.util.LinkedList;

public class AuthTests {

    @Test
    public void example() {
        IdWorker worker = new IdWorker(0, 0);
        for (int i = 0; i < 10; i++)
            System.out.println(worker.nextId());
    }

    @Test
    public void rest() {
        RestTemplate restTemplate = new RestTemplate();
        try {
            ResponseEntity<String> entity = restTemplate.getForEntity("http://192.168.51.192:2160/v2/api-docs?group=OMS-API-MANAGE", String.class);
            System.out.println(entity.getBody());
        } catch (Exception e) {
            e.printStackTrace();
        }
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

}
