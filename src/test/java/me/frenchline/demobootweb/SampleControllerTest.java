package me.frenchline.demobootweb;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
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
@SpringBootTest //스프링 부트 통합 테스트, 이 애노테이션을 사용하면 모든 Bean들을 테스트에서 사용할 수 있다
@AutoConfigureMockMvc //이 애노테이션을 사용하면 MockMvc 객체를 자동주입 받을 수 있다.
public class SampleControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    PersonRepository personRepository;

    @Test
    public void hello() throws Exception {

        Person person = new Person();
        person.setName("frenchline");
        Person savedPerson = personRepository.save(person);

        this.mockMvc.perform(get("/hello")
                            .param("id", savedPerson.getId().toString())) //parameter mocking
                .andDo(print())
                .andExpect(content().string("hello frenchline"));
    }
}