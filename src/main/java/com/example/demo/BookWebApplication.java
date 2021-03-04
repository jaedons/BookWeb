package com.example.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@ServletComponentScan
@EnableCaching
public class BookWebApplication {

    private static Logger logger = LoggerFactory.getLogger(BookWebApplication.class);
	public static void main(String[] args) {
		SpringApplication.run(BookWebApplication.class, args);
		logger.info("启动项目");
	}

}
