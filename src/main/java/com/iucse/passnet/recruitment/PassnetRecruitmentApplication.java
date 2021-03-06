package com.iucse.passnet.recruitment;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j(topic = "[PassnetRecruitmentApplication]")
@SpringBootApplication
public class PassnetRecruitmentApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(PassnetRecruitmentApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

    }

}
