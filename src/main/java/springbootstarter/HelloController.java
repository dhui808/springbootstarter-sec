package springbootstarter;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
public class HelloController {

    @Value("${demo.secretsPath}")
    private String secretsPath;
    
	@RequestMapping("/hello")
	public String index() {
		return "Greetings from Spring Boot! demo.secretsPath=" + secretsPath;
	}

}