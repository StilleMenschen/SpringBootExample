package tech.tystnad.joblog.auth;

import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.*;

public class AuthTests {

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
    public void id() throws InterruptedException {
        final int end = 100;
        final int bound = Character.MAX_RADIX * Character.MAX_RADIX;
        final Random random = new Random();
        Map<String, Long> map = new HashMap<>();
        long seed, count = 0;
        String temp;
        for (int i = 0; i < end; i++) {
            seed = System.currentTimeMillis();
            seed = seed + random.nextInt(bound);
            temp = Long.toString(seed, Character.MAX_RADIX);
            if (map.containsKey(temp)) {
//                System.out.println(i + " - " + temp + " exists!");
                count++;
            } else {
                map.put(temp, seed);
            }
            Thread.sleep(100);
        }
        System.out.println(count);
    }

    @Test
    public void uuid() {
        final int end = 100;
        Map<String, Long> map = new HashMap<>();
        long  count = 0;
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
