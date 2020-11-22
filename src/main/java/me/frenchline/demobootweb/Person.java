package me.frenchline.demobootweb;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 도메인 클래스 맵핑
 * - 해당 ID(PK)에 해당하는 엔티티(도메인 클래스)를 맵핑하는 경우
 * - 도메인 클래스용 포매터 or 컨버터를 직접 만들 필요 X, 스프링 데이터 JPA의 기능을 제공받을 수 있다
 * - 스프링 데이터 JPA는 스프링 MVC용 도메인 클래스 컨버터를 자동으로 등록해서 제공
 */

@XmlRootElement //XML
@Entity
public class Person {

    @Id @GeneratedValue
    private Long id;

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
