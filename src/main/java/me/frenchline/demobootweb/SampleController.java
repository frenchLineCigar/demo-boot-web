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

    // preHandle 1
    // preHandle 2
    // 요청 처리
    // postHandle 2
    // postHandle 1
    // 뷰 렌더링
    // afterCompletion 2
    // afterCompletion 1


    @GetMapping("/hello")
    public String hello(@RequestParam("id") Person person) {
        System.out.println("SampleController.hello");
        return "hello " + person.getName();
    }
}
