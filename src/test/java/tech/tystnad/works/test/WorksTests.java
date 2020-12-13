package tech.tystnad.works.test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import tech.tystnad.works.config.AuthorityCodeConfig;
import tech.tystnad.works.model.dto.SysUserDTO;
import tech.tystnad.works.util.IdWorker;

import java.io.File;
import java.util.*;

public class WorksTests {

    @Test
    public void example() {
        System.out.println(chinese2encoding("角色名称不能为空"));
    }

    @Test
    public void readAuthorityCode() {
        final String splitChar = ".";
        final Map<String, String> NameMap = new LinkedHashMap<>();
        final Map<String, String> OperatingMap = new LinkedHashMap<>();
        final Map<String, List<String>> AuthorityMap = new LinkedHashMap<>();
        Set<String> authoritySet = AuthorityCodeConfig.keySet();
        NameMap.put("MANAGER", "管理员");
        NameMap.put("ORGANIZATION", "机构");
        NameMap.put("ROLE", "角色");
        NameMap.put("USER", "用户");
        OperatingMap.put("CREATE", "新增");
        OperatingMap.put("READ", "查询");
        OperatingMap.put("UPDATE", "更新");
        OperatingMap.put("DELETE", "删除");
        authoritySet.stream().filter(e -> e.contains(splitChar)).forEach(key -> {
            final String[] splitKey = key.split("\\.");
            List<String> list = AuthorityMap.computeIfAbsent(splitKey[0], k -> new LinkedList<>());
            list.add(splitKey[1]);
        });
        AuthorityMap.forEach((k, v) -> {
            final String description = NameMap.get(k);
            System.out.printf("REPLACE INTO sys_authority (auth_id,auth_name,auth_description) VALUES (%s,'%s','%s');\n", AuthorityCodeConfig.getObject(k), k, description);
            v.forEach(e -> {
                final String subKey = k.concat(splitChar).concat(e);
                System.out.printf("REPLACE INTO sys_authority (auth_id,parent_id,auth_name,auth_description) VALUES (%s,%s,'%s','%s');\n", AuthorityCodeConfig.getObject(subKey), AuthorityCodeConfig.getObject(k), subKey, OperatingMap.get(e).concat(description));
            });
        });
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
        StringBuilder unicodeBytes = new StringBuilder();
        for (int charCode : utfBytes) {
            /* 只转换中文*/
            if (charCode >= 0x4e00 && charCode <= 0x9fef) {
                String hexB = Integer.toHexString(charCode);
                if (hexB.length() <= 2) {
                    hexB = "00" + hexB;
                }
                unicodeBytes.append("\\u".concat(hexB));
            }
            /* 其它字符保留*/
            else {
                unicodeBytes.append((char) charCode);
            }
        }
        return unicodeBytes.toString();
    }

    @Test
    public void rest() {
        final RestTemplate restTemplate = new RestTemplate();
        final MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        final MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        final HttpHeaders headers = new HttpHeaders();
        final File file = new File("e:/tmp.txt");
        final FileSystemResource resource = new FileSystemResource(file);
        params.set("size", "1");
        final String url = UriComponentsBuilder.newInstance()
                .scheme("http").host("localhost").port("8080")
                .path("/test/template").queryParams(params)
                .encode().build().toUriString();
        headers.set("Platform", "MOBILE");
        body.set("id", 1);
        body.set("name", "圣彼得堡");
        body.set("countryCode", "45889");
        body.set("district", "俄罗斯");
        body.set("population", 19865);
        body.add("file", resource);
        body.add("file", resource);

        final HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);
        try {
            ResponseEntity<String> responseEntity = restTemplate.postForEntity(url, requestEntity, String.class);
            ObjectMapper mapper = new ObjectMapper();
            JsonNode node = mapper.readTree(responseEntity.getBody());
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
        final int end = 10;
        IdWorker idWorker = new IdWorker(0, 0);
        for (int i = 0; i < end; i++) {
            long id = idWorker.nextId();
            System.out.println(id);
            System.out.println(Long.toString(id, Character.MAX_RADIX));
        }
        System.out.println(System.currentTimeMillis());
    }

}
