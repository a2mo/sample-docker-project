package ir.a2mo.sample.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Ali Alimohammadi
 * @since 7/31/2022
 */

@RestController
public class GreetingController {

    @GetMapping("/helloworld")
    public String helloWorld(@RequestParam(required = false) String name) {
        if (StringUtils.isEmpty(name)) {
            return "Hello Stranger";
        } else {
            return "Hello " + name;
        }
    }

    @GetMapping("/author")
    public String helloWorld() {
        return "Ali Alimohammadi";
    }
}
