package com.restaurant.stoke.stoke.config;

import java.time.Instant;
import java.util.Arrays;
import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.restaurant.stoke.stoke.entities.Stoke;
import com.restaurant.stoke.stoke.repository.StokeRepository;

@Configuration
@Profile("test")
public class testConfig implements CommandLineRunner{
    
    @Autowired
    private StokeRepository stokeRepository;

    @Override
    public void run(String... args) throws Exception {
        Calendar c = Calendar.getInstance();
        Instant instant = Instant.now();
        Long productId1 = (long) 1;
        Long productId2 = (long) 2;
        Stoke stoke1 = new Stoke(null, productId1, 10, c.getTime(),instant,instant);
        Stoke stoke2 = new Stoke(null, productId2, 20, c.getTime(),instant,instant);

        stokeRepository.saveAll(Arrays.asList(stoke1,stoke2));
        
    }

    
}
