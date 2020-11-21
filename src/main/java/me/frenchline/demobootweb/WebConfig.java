package me.frenchline.demobootweb;

import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        /* 1. 딱히 별다른 순서(order)를 설정하지 않으면 코드 등록 순서대로 먼저 적용된다 */
        /* 2. 명시적으로 순서(order)를 등록하고 싶으면 order()를 사용해 지정할 수 있다(낮을 수록 우선순위가 높음)*/
        /* 3. 인터셉터를 원하는 특정 패턴에만 적용 또는 제외 할 수 있다 */
        registry.addInterceptor(new GreetingInterceptor()).order(0);
        registry.addInterceptor(new AnotherInterceptor())
                .addPathPatterns("/hi")
                .excludePathPatterns("/hello/**")
                .order(-1);
    }
}
