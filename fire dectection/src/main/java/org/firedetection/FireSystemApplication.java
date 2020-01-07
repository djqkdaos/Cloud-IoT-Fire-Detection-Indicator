package org.firedetection;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ImportResource("classpath:applicationContext.xml")
@EnableConfigurationProperties({
	FileStorageProperties.class
})
public class FireSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(FireSystemApplication.class, args);
	}
}
