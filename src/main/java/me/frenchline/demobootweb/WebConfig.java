package me.frenchline.demobootweb;

import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    /**
     * 기존 스프링 MVC가 제공하는 포매터를 등록하는 방법
     * (사실 스프링 부트를 사용할 경우 이렇게 직접 포매터를 등록할 필요가 없다.)
     * (스프링 부트는 Formatter가 Bean으로 등록이 되어 있다면 그 빈으로 포매터를 알아서 등록해주기 때문에)
     */
    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addFormatter(new PersonFormatter());
    }

}
