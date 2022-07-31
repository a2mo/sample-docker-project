package ir.a2mo.sample.controller;

import ir.a2mo.sample.service.GreetingService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Ali Alimohammadi
 * @since 7/31/2022
 */

@RestController
@RequiredArgsConstructor
public class GreetingController {
    private final GreetingService service;

    @GetMapping("/helloworld")
    public String helloWorld(@RequestParam(required = false) String name) {
        if (StringUtils.isEmpty(name)) {
            return service.hello();
        } else {
            return service.hello(name);
        }
    }

    @GetMapping("/author")
    public String getAuthor() {
        return service.getAuthor();
    }
}
