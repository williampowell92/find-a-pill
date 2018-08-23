package com.makersacademy.find_a_pill.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@WebMvcTest(HomeController.class)
public class HomeControllerTest {

    @MockBean
    private HomeController controller;

    @Before
    public void before() {
        MockitoAnnotations.initMocks(this);
    }

//    @Test
//    public void indexRootReturnsGreeting() {
//        when(controller.index()).thenCallRealMethod();
//        assertEquals("Hello World", controller.index());
//    }

}