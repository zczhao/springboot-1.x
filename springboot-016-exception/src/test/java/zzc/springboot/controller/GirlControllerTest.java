package zzc.springboot.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

/**
 * 测试API
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class GirlControllerTest {
    @Autowired
    private MockMvc mockMvc;

    // mvn clean package -Dmaven.test.skip=true 跳过测试
    @Test
    public void girlList() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/girls1"))
        .andExpect(MockMvcResultMatchers.status().isOk());
    }
}
