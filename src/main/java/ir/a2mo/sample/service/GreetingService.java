package ir.a2mo.sample.service;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

/**
 * @author Ali Alimohammadi
 * @since 7/31/2022
 */
@Component
public class GreetingService {

    public String hello(String name) {
        return "Hello " + name;
    }

    public String hello() {
        return "Hello Stranger";
    }

    public String getAuthor() {
        return "Ali Alimohammadi";
    }
}
