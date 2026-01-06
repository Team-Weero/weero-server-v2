package team.weero.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan
public class WeeRoApplication {

	public static void main(String[] args) {
		SpringApplication.run(WeeRoApplication.class, args);
	}

}
