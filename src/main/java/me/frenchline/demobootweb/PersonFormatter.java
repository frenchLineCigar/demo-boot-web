package me.frenchline.demobootweb;

import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.Locale;

/**
 * 스프링 부트는 특정 Formatter가 Bean으로 등록되어 있다면 그 빈으로 포매터를 자동 등록해준다.
 */
@Component
public class PersonFormatter implements Formatter<Person> {

    //문자열 -> 객체
    @Override
    public Person parse(String text, Locale locale) throws ParseException {
        Person person = new Person();
        person.setName(text);
        return person;
    }

    //객체 -> 문자열
    @Override
    public String print(Person object, Locale locale) {
        return object.toString();
    }
}
