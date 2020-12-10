package com.cerner.pran;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;

@SpringBootApplication
@ComponentScan("com.cerner.pran")
@EnableEncryptableProperties
public class PranApplication {

	public static void main(String[] args) {
		SpringApplication.run(PranApplication.class, args);
	}

}
