package dev.segar.redisclient.redisclient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.Instant;

@Service
public class MyRedisService {

    @Autowired
    private StringRedisTemplate redisTemplate;

    public void setValue(String key, String value) {
        redisTemplate.opsForValue().set(key, value);
    }

    public String getValue(String key) {
        return redisTemplate.opsForValue().get(key);
    }
    
    @EventListener(ApplicationReadyEvent.class)
    public void onload() {
        System.out.println("redisTemplate = " + redisTemplate);

        for(int i=0; i<10; i++) {
            if(i==0) {
                Instant start = Instant.now();
                String x = redisTemplate.opsForValue().get("SAMPLE");
                Instant end = Instant.now();
                System.out.println("timeTake(start, end) = " + String.format("%.3f", timeTake(start, end)/1000) + " SAMPLE= " + x);
            }

            if(i==1) {
                Instant start = Instant.now();
                String x = redisTemplate.opsForValue().get("SAMPLE1");
                Instant end = Instant.now();
                System.out.println("timeTake(start, end) = " + String.format("%.3f", timeTake(start, end)/1000) + " SAMPLE1= " + x);
            }

            if(i==2) {
                Instant start = Instant.now();
                String x = redisTemplate.opsForValue().get("SAMPLE2");
                Instant end = Instant.now();
                System.out.println("timeTake(start, end) = " + String.format("%.3f", timeTake(start, end)/1000) + " SAMPLE2= " + x);
            }

            if(i>2) {
                Instant start = Instant.now();
                System.out.println("timeTake(start, end) = " + String.format("%.3f", timeTake(start,  Instant.now())/1000) + " SAMPLE= " + redisTemplate.opsForValue().get("SAMPLE"));
                System.out.println("timeTake(start, end) = " + String.format("%.3f", timeTake(start,  Instant.now())/1000) + " SAMPLE= " + redisTemplate.opsForValue().get("SAMPLE1"));
                System.out.println("timeTake(start, end) = " + String.format("%.3f", timeTake(start,  Instant.now())/1000) + " SAMPLE= " + redisTemplate.opsForValue().get("SAMPLE2"));
            }
        }


    }

    static double timeTake(Instant start, Instant end) {

        // Calculate the difference between start and end
        Duration duration = Duration.between(start, end);

        // Output the difference in milliseconds
        double millis = duration.toMillis();
        return millis;
    }
}