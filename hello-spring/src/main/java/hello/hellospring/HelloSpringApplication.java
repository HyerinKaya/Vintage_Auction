package hello.hellospring;

import com.fasterxml.jackson.datatype.hibernate5.Hibernate5Module;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing //BaseTimeEntity에서 생성날짜, 수정날짜 자동으로 적용하기 위해서 추가함
@EnableCaching
@SpringBootApplication
public class HelloSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(HelloSpringApplication.class, args);
	}


	@Bean
	Hibernate5Module hibernate5Module() {
		return new Hibernate5Module();
	}


}
