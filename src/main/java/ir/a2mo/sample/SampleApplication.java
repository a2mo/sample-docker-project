package ir.a2mo.sample;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.DefaultApplicationArguments;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.core.io.FileSystemResource;

import java.util.Arrays;
import java.util.HashMap;

@Slf4j
@SpringBootApplication
public class SampleApplication {
    private static String port;
    private static String contextPath;
    private static String author;
    public static final String PORT = "port";
    public static final String CONTEXT_PATH = "context-path";
    public static final String AUTHOR = "author";
    public static final String DEFAULT_PORT = "8080";
    public static final String DEFAULT_CONTEXT_PATH = "";
    public static final String DEFAULT_AUTHOR = "Ali Alimohammadi";

    public static void main(String[] args) {
        if (new FileSystemResource("logback.xml").getFile().exists()) {
            System.setProperty("logging.config", "file:logback.xml");
        } else {
            System.setProperty("logging.config", "classpath:logback.xml");
        }
        SpringApplicationBuilder app = new SpringApplicationBuilder(SampleApplication.class)
                .web(WebApplicationType.SERVLET);
        SpringApplication springApplication = app.build();
        fillApplicationArgs(args);
        log.info(port);
        HashMap<String, Object> properties = new HashMap<>();
        properties.put("server.port", port);
        properties.put("server.servlet.context-path", contextPath);
        properties.put("author", author);
        springApplication
                .setDefaultProperties(properties);
        springApplication.run(args);
    }

    public static void fillApplicationArgs(String[] args) {
        DefaultApplicationArguments applicationArguments = new DefaultApplicationArguments(args);
        log.info("Application started with command-line arguments: {}", Arrays.toString(applicationArguments.getSourceArgs()));
        log.info("NonOptionArgs: {}", applicationArguments.getNonOptionArgs());
        log.info("OptionNames: {}", applicationArguments.getOptionNames());

        for (String name : applicationArguments.getOptionNames()) {
            log.info(name + "=" + applicationArguments.getOptionValues(name));
        }

        if (applicationArguments.containsOption(PORT)) {
            port = applicationArguments.getOptionValues(PORT)
                    .stream().findFirst().orElse(DEFAULT_PORT);
        } else {
            port = DEFAULT_PORT;
        }
        if (applicationArguments.containsOption(CONTEXT_PATH)) {
            contextPath = applicationArguments.getOptionValues(CONTEXT_PATH)
                    .stream().findFirst().orElse(DEFAULT_CONTEXT_PATH);
        } else {
            contextPath = DEFAULT_CONTEXT_PATH;
        }

        if (applicationArguments.containsOption(AUTHOR)) {
            author = applicationArguments.getOptionValues(AUTHOR)
                    .stream().findFirst().orElse(DEFAULT_AUTHOR);
        } else {
            author = DEFAULT_AUTHOR;
        }

    }

}
