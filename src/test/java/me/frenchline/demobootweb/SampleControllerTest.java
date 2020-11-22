package me.frenchline.demobootweb;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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

    @Autowired
    ObjectMapper objectMapper;

    /* JSON용 HTTP 메시지 컨버터가 기본으로 등록 : JacksonJSON 2가 제공하는 ObjectMapper 사용 */
    @Test
    public void jsonMessage() throws Exception {
        Person person = new Person();
        person.setId(2019l);
        person.setName("frenchline");

        /* ObjectMapper를 사용해서 객체를 json 문자열로 변환 가능 */
        String jsonString = objectMapper.writeValueAsString(person);

        this.mockMvc.perform(get("/jsonMessage") /* perform() 메서드의 인자에는 요청에 대한 정보가 들어간다 */
                    .contentType(MediaType.APPLICATION_JSON_UTF8) //Content-Type은 요청 본문(Request Body)에 보내는 정보가 JSON 타입임을 서버에 알림
                    .accept(MediaType.APPLICATION_JSON_UTF8) //Accept는 이 요청에 대한 응답을 JSON 타입으로 보내달라
                    .content(jsonString))
                .andDo(print())
                .andExpect(status().isOk());
    }
    
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

    /* 스프링 부트는 기본적으로 정적 리소스 핸들러와 캐싱 제공 */
    @Test
    public void helloStatic() throws Exception {
        this.mockMvc.perform(get("/index.html"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(Matchers.containsString("hello index")));
    }

    /* 리소스 핸들러 커스텀 설정 */
    @Test
    public void helloStaticCustom() throws Exception {
        this.mockMvc.perform(get("/mobile/index.html"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(Matchers.containsStringIgnoringCase("hello mobile")))
                .andExpect(header().exists(HttpHeaders.CACHE_CONTROL)); //응답 헤더에 Cache-Control 과 관련된 정보가 존재해야 한다
    }

    /* 기본 HTTP 메시지 컨버터 : 문자열 컨버터 */
    @Test
    public void stringMessage() throws Exception {
        this.mockMvc.perform(get("/message")
                .content("hello"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string("hello"));
    }

}