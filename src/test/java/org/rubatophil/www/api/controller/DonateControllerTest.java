package org.rubatophil.www.api.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;

import static org.junit.jupiter.api.Assertions.*;

@WebMvcTest(DonateController.class)
class DonateControllerTest {

    @Autowired
    MockMvc mockMvc;

    @BeforeEach
    public void setup() {

    }

    @Test
    void donateInfo() {
    }

    @Test
    void donate() {
    }
}