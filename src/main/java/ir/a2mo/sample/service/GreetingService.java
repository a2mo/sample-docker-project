package ir.a2mo.sample.service;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author Ali Alimohammadi
 * @since 7/31/2022
 */
@Component
public class GreetingService {

    @Value("${author}")
    private String author;

    public String hello(String name) {
        return "Hello " + name;
    }

    public String hello() {
        return "Hello Stranger";
    }

    public String getAuthor() {
        return author;
    }
}
