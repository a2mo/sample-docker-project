package ir.a2mo.sample;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.ApplicationPidFileWriter;
import org.springframework.core.io.FileSystemResource;

import java.util.Arrays;

@Slf4j
@SpringBootApplication
public class SampleApplication {

	public static void main(String[] args) {
		if (new FileSystemResource("logback.xml").getFile().exists()) {
			System.setProperty("logging.config", "file:logback.xml");
		} else {
			System.setProperty("logging.config", "classpath:logback.xml");
		}
		SpringApplicationBuilder app = new SpringApplicationBuilder(SampleApplication.class)
				.web(WebApplicationType.SERVLET);
		app.run();
	}

}
