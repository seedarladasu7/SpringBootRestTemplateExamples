package com.hcl.feign.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class FeignUserApplication {

	public static void main(String[] args) {
		SpringApplication.run(FeignUserApplication.class, args);
	}

}
