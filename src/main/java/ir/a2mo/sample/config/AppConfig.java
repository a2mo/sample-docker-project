package ir.a2mo.sample.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author Ali Alimohammadi
 * @since 8/1/2022
 */
@Component
public class AppConfig {

    @Value("${author}")
    private String authorName;

    public String getAuthorName() {
        return authorName;
    }
}
