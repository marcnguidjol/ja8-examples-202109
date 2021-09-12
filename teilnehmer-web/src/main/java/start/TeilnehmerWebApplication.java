package start;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(
		scanBasePackages = {
				"teilnehmer", 
				"teilnehmer.service", 
				"teilnehmer.data", 
				"teilnehmer.model"
})
public class TeilnehmerWebApplication {

	public static void main(String[] args) {
		SpringApplication.run(TeilnehmerWebApplication.class, args);
	}

}
