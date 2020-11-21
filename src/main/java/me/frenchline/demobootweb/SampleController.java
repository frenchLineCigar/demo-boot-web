package me.frenchline.demobootweb;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author swlee
 * @contact frenchline707@gmail.com
 * @since 2019-11-21
 */

@RestController
public class SampleController {

    /* 포매터 사용 : 요청 경로의 문자열 -> Person 객체 */
    @GetMapping("/hello/{name}")
    public String hello(@PathVariable("name") Person person) {
        return "hello " + person.getName();
    }

    /* 포매터 사용 : 요청 파라미터 문자열 -> Person 객체 */
    @GetMapping("/hello")
    public String hello2(@RequestParam("name") Person person) {
        return "hello " + person.getName();
    }
}
