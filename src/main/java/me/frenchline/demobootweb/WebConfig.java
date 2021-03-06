package me.frenchline.demobootweb;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.http.CacheControl;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    /* 뷰 컨트롤러 : 단순하게 요청 URL을 (컨트롤러를 작성할 필요 없이) 특정 뷰로 연결하고 싶을 때 사용 */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("hi").setViewName("hi");
    }

    /* 추상화된 Marshaller 인터페이스를 구현하는 빈 등록*/
    @Bean
    public Jaxb2Marshaller jaxb2Marshaller() {
        Jaxb2Marshaller jaxb2Marshaller = new Jaxb2Marshaller();
        jaxb2Marshaller.setPackagesToScan(Person.class.getPackageName());
        return jaxb2Marshaller;
    }

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


    /* HTTP 메시지 컨버터 커스텀 설정 : 기본으로 등록해주는 컨버터를 다 무시하고 새로 컨버터 설정하기 */
    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        /**
         * 1. If no converters are added, a default list of converters is registered.
         * 2. Note that adding converters to the list, turns off default converter registration.
         */
    }

    /* HTTP 메시지 컨버터 커스텀 설정 : 기본으로 등록해주는 컨버터에 새로운 컨버터만 추가하기 */
    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        /**
         * 1. A hook for extending or modifying the list of converters after it has been configured.
         * 2. This may be useful for example to allow default converters to be registered
         * and then insert a custom converter through this method.
         */
    }
}
