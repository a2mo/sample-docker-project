package ir.a2mo.sample.controller;

import ir.a2mo.sample.service.GreetingService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.mockito.Mockito.*;

/**
 * @author M.khoshnevisan
 * @since 4/26/2022
 */
public class GreetingControllerUTest {

    private GreetingService greetingService;
    private GreetingController greetingController;

    @BeforeEach
    public void setup() {
        greetingService = mock(GreetingService.class);
        greetingController = new GreetingController(greetingService);
    }

    @Test
    public void testHelloWorld_normalServiceCall_withName() {
        String name = "AliAlimohammadi";
        greetingController.helloWorld(name);
        verify(greetingService, times(1)).hello(eq(name));
        verify(greetingService, times(0)).hello();
    }

    @Test
    public void testHelloWorld_normalServiceCall_withoutName() {
        String name = null;
        greetingController.helloWorld(name);
        verify(greetingService, times(1)).hello();
        verify(greetingService, times(0)).hello(name);
    }

    @Test
    public void testGetAuthor_normalServiceCall() {
        greetingController.getAuthor();
        verify(greetingService, times(1)).getAuthor();
    }
}