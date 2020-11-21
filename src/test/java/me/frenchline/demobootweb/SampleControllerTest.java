package me.frenchline.demobootweb;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

/**
 * @author swlee
 * @contact frenchline707@gmail.com
 * @since 2019-11-21
 */

@RunWith(SpringRunner.class)
@WebMvcTest //웹과 관련된 계층만 슬라이싱 테스트(웹과 관련된 계층의 Bean만 등록), 이 애노테이션을 사용하면 MockMvc라는 클래스를 자동주입 받을 수 있다.
public class SampleControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    public void hello() throws Exception {
        this.mockMvc.perform(get("/hello")
                            .param("name", "frenchline")) //parameter mocking
                .andDo(print())
                .andExpect(content().string("hello frenchline"));
    }
}