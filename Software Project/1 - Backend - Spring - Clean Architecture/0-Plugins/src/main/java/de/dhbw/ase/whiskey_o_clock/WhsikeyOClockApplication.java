package de.dhbw.ase.whiskey_o_clock;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan("de.dhbw.*")
@ComponentScan("de.dhbw.*")
@EnableJpaRepositories("de.dhbw.*")
public class WhsikeyOClockApplication {

    public static void main(String[] args) {
        SpringApplication.run(WhsikeyOClockApplication.class, args);
    }

}
