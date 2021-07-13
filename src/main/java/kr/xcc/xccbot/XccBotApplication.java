package kr.xcc.xccbot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties
public class XccBotApplication {

	public static void main(String[] args) {
		SpringApplication.run(XccBotApplication.class, args);
	}

}
