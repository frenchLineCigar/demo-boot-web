package me.frenchline.demobootweb;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author swlee
 * @contact frenchline707@gmail.com
 * @since 2019-11-21
 */
public interface PersonRepository extends JpaRepository<Person, Long> {
}
