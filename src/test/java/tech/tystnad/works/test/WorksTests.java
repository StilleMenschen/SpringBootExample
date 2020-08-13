package tech.tystnad.works.test;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.client.RestTemplate;
import tech.tystnad.works.util.IdWorker;

import java.util.LinkedList;

public class WorksTests {

    @Test
    public void example() {
        String s = "aaa";
        if (s!=null && !"aa".equals(s)){
            System.out.println(111);
        }else {
            System.out.println(222);
        }
    }

    @Test
    public void rest() {
        RestTemplate restTemplate = new RestTemplate();
        try {
            ResponseEntity<String> entity = restTemplate.getForEntity("http://localhost/test.json", String.class);
            ObjectMapper mapper = new ObjectMapper();
            JsonNode node = mapper.readTree(entity.getBody());
            System.out.println(node.toPrettyString());
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
                "beautiful",
                "!\"#$%&'()*+,-./0123456789:;<=>?@ABCDEFGHIJKLMNOPQRSTUVWXYZ[\\]^_`abcdefghijklmnopqrstuvwxyz{|}~"
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
