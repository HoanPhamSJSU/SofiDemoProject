package com.sofi.sofidemoproject;
import com.sofi.sofidemoproject.Controllers.GiphyController;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import java.io.IOException;

@SpringBootTest
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
class SofiDemoProjectApplicationTests {

    @Autowired
    private GiphyController controller;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void contextLoads() throws IOException {
        assertThat(controller).isNotNull();
    }

    @Test
    public void performSearch() throws Exception {
        mockMvc.perform(get("/search/1"))
                .andDo(print())
                .andExpect(status().isOk());
    }
}
