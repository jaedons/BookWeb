
package com.example.demo;
import java.nio.charset.Charset;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.example.demo.domain.Book;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ComponentScan("com.example.demo")
public class WebControllerTest {
    @Autowired
    private MockMvc mockMvc;
    private Gson gson = new Gson();
    
    /**
     * 查询用户列表
     * @throws Exception
     */
    @Test
    public void userListTest() throws Exception {
        String url = "/book";
        MvcResult mvcResult = mockMvc.perform(
                MockMvcRequestBuilders.get(url)
                .accept(MediaType.APPLICATION_JSON)
                )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
        MockHttpServletResponse response = mvcResult.getResponse();
        System.out.println(response.getStatus()); // 获取响应状态码
        String result =  response.getContentAsString(Charset.forName("UTF-8"));
        System.out.println(result); // 获取响应内容
    }
    /**
     * 创建测试用户
     * @throws Exception
     */
    @Test
    public void bookAddTest() throws Exception {
        Book book = new Book();
        book.setName("《钢铁是怎么炼成的》");
        book.setId(1L);
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/book/create")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(gson.toJson(book))
                .accept(MediaType.APPLICATION_JSON))
                .andReturn();
        MockHttpServletResponse response = mvcResult.getResponse();
        System.out.println(response.getStatus()); // 获取响应状态码
        System.out.println(response.getContentAsString(Charset.forName("UTF-8"))); // 获取响应内容
    }
    /**
     * 根据id删除用户测试
     * @throws Exception
     */
    @Test
    public void deleteUserByIdTest() throws Exception {
        Integer id = 11;
        MvcResult mvcResult =mockMvc.perform(MockMvcRequestBuilders.delete("/book/delete/{id}" ,id))
                .andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
        MockHttpServletResponse response = mvcResult.getResponse();
        System.out.println(response.getStatus()); // 获取响应状态码
        System.out.println(response.getContentAsString(Charset.forName("UTF-8"))); // 获取响应内容
    }
}