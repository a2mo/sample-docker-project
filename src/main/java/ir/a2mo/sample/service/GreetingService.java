package ir.a2mo.sample.service;

import ir.a2mo.sample.config.AppConfig;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author Ali Alimohammadi
 * @since 7/31/2022
 */
@Component
@RequiredArgsConstructor
public class GreetingService {
    private final AppConfig appConfig;

    public String hello(String name) {
        return "Hello " + name;
    }

    public String hello() {
        return "Hello Stranger";
    }

    public String getAuthor() {
        return appConfig.getAuthorName();
    }
}
