package ir.a2mo.sample.service;

import ir.a2mo.sample.config.AppConfig;
import ir.a2mo.sample.controller.GreetingController;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

/**
 * @author Ali Alimohammadi
 * @since 7/31/2022
 */
public class GreetingServiceUTest {
    private GreetingService greetingService;
    private AppConfig appConfig;
    public static final String HELLO_WITH_NAME = "Hello AliMohammadi";
    public static final String HELLO_WITHOUT_NAME = "Hello Stranger";
    public static final String AUTHOR = "Ali Alimohammadi";
    public static final String ARG_AUTHOR = "Ali Ahmadi";

    @BeforeEach
    public void setup() {
        appConfig = mock(AppConfig.class);
        greetingService = new GreetingService(appConfig);
    }

    @Test
    public void testHelloWorld_normalServiceCall_withName() {
        String name = "AliMohammadi";
        String result = greetingService.hello(name);
        assertEquals(result, HELLO_WITH_NAME);
    }

    @Test
    public void testHelloWorld_normalServiceCall_withoutName() {
        String result = greetingService.hello();
        assertEquals(result, HELLO_WITHOUT_NAME);
    }

    @Test
    public void testGetAuthor_normalServiceCall_withoutArgs() {
        when(appConfig.getAuthorName()).thenReturn("Ali Alimohammadi");
        String result = greetingService.getAuthor();
        assertEquals(result, AUTHOR);
    }

    @Test
    public void testGetAuthor_normalServiceCall() {
        when(appConfig.getAuthorName()).thenReturn("Ali Ahmadi");
        String result = greetingService.getAuthor();
        assertEquals(result, ARG_AUTHOR);
    }
}
