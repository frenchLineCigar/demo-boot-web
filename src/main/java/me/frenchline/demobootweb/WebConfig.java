package me.frenchline.demobootweb;

import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.http.CacheControl;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.concurrent.TimeUnit;

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
                //.excludePathPatterns("/hello/**")
                .order(-1);
    }

    /* 리소스 핸들러 커스텀 설정 */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/mobile/**") //어떤 요청 패턴을 지원할 것인가
                .addResourceLocations("classpath:/mobile/", "file:/Users/frenchline/files/") //어디서 리소스를 찾을 것인가
                .setCacheControl(CacheControl.maxAge(10, TimeUnit.MINUTES));
                /* setCacheControl() : 캐시 제어 설정 */
                /* - 여기서 리턴하는 리소스들은 기본적으로 캐시 컨트롤과 관련된 정보가 응답 헤더에 추가된다 */
                /* - 만약 리소스가 변경되지 않았다면 이 응답은 10분 동안 캐싱한다 (status: 304 not modified) */
                /* - 리소스가 변경됐다면 10분이 안 지났더라도 변경된 리소스로 갱신한다 */
    }
}
