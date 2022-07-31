package ir.a2mo.sample.service;

import ir.a2mo.sample.controller.GreetingController;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

/**
 * @author Ali Alimohammadi
 * @since 7/31/2022
 */
public class GreetingServiceUTest {
    private GreetingService greetingService;
    public static final String HELLO_WITH_NAME = "Hello AliMohammadi";
    public static final String HELLO_WITHOUT_NAME = "Hello Stranger";
    public static final String AUTHOR = "Ali Alimohammadi";

    @BeforeEach
    public void setup() {
        greetingService = new GreetingService();
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
    public void testGetAuthor_normalServiceCall() {
        String result = greetingService.getAuthor();
        assertEquals(result, AUTHOR);
    }
}
