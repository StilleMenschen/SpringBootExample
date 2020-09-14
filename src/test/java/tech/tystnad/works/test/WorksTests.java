package tech.tystnad.works.test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.client.RestTemplate;
import tech.tystnad.works.model.dto.SysUserDTO;
import tech.tystnad.works.util.IdWorker;

import java.time.Duration;
import java.util.LinkedList;

public class WorksTests {

    @Test
    public void example() {
        System.out.println(chinese2encoding("用户类型不存在"));
        System.out.println(Duration.ofHours(24).getSeconds());
    }

    @Test
    public void convert2json() {
        ObjectMapper mapper = new ObjectMapper();
        SysUserDTO dto = new SysUserDTO();
        dto.setUserId(123L);
        dto.setOrgId(123L);
        dto.setOrgName("aaa");
        dto.setUserName("aaa");
        dto.setRoleId(123L);
        dto.setNickname("aaa");
        dto.setEmail("aa@aa.com");
        dto.setTelephoneNumber("123123");
        dto.setUserType((byte) 2);
        try {
            System.out.println(mapper.writeValueAsString(dto));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    public String chinese2encoding(final String gbString) {
        char[] utfBytes = gbString.toCharArray();
        String unicodeBytes = "";
        int charCode = 0;
        for (int i = 0; i < utfBytes.length; i++) {
            /* 只转换中文*/
            charCode = (int) utfBytes[i];
            if (charCode >= 0x4e00 && charCode <= 0x9fef) {
                String hexB = Integer.toHexString(utfBytes[i]);
                if (hexB.length() <= 2) {
                    hexB = "00" + hexB;
                }
                unicodeBytes = unicodeBytes + "\\u" + hexB;
            }
            /* 其它字符保留*/
            else {
                unicodeBytes = unicodeBytes + utfBytes[i];
            }

        }
        return unicodeBytes;
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
