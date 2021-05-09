package com.iucse.passnet.recruitment;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy
@Slf4j(topic = "[PassnetRecruitmentApplication]")
public class PassnetRecruitmentApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(PassnetRecruitmentApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {}
}
