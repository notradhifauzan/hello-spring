package com.learnthepart.hellospring;

import static org.junit.Assert.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/*
 * we need mockMvc in order to mock get/post request against controller handler method
 * why use mockMvc:
 *  1. test how app handle web request without running/deploy your app
 */


@SpringBootTest
@AutoConfigureMockMvc
public class GradeSubmissionApplicationTest {
    
    @Autowired
    private MockMvc mockMvc;
    
    @Test
    public void contextLoads(){
        assertNotNull(mockMvc);
    }

    @Test
    public void testShowGradeForm() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders.get("/?id=123");

        mockMvc.perform(request)
        .andExpect(status().is2xxSuccessful())
        .andExpect(view().name("forms"))
        .andExpect(model().attributeExists("grade"));
    }

    @Test
    public void testSuccessfulSubmission() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders.post("/handleSubmit")
            .param("name", "Harry99")
            .param("subject","potions")
            .param("score","90.99");

        mockMvc.perform(request)
            .andExpect(status().is3xxRedirection())
            .andExpect(redirectedUrl("/grades"));
    }

    @Test
    public void testUnsuccessfulSubmission() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders.post("/handleSubmit")
            .param("name", "munir")
            .param("subject", "computer programming")
            .param("score", "-10");

        mockMvc.perform(request)
        .andExpect(status().is2xxSuccessful())
        .andExpect(view().name("forms"));

    }

    @Test
    public void testGetGrades() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders.get("/grades");
        mockMvc.perform(request)
            .andExpect(status().is2xxSuccessful())
            .andExpect(view().name("grades"))
            .andExpect(model().attributeExists("grades"));
    }
}
