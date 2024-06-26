package backend.satc.saudeebemestar_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories
@SpringBootApplication
public class SaudeebemestarServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SaudeebemestarServiceApplication.class, args);
	}

}
