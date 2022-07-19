package org.rubatophil.www.api.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(ClubMemberController.class)
class ClubMemberControllerTest {

    @Autowired
    MockMvc mockMvc;

    @BeforeEach
    public void setup() {

    }


    @Test
    void searchMemberList() {
    }
}