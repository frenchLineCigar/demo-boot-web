package me.frenchline.demobootweb;

import org.springframework.format.Formatter;

import java.text.ParseException;
import java.util.Locale;

/**
 * @author swlee
 * @contact frenchline707@gmail.com
 * @since 2019-11-21
 */
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
