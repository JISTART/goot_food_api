package boot.set.test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableScheduling;

//@EnableAspectJAutoProxy // AOP를 찾을 수 있게 해주는 어너테이션
//@EnableScheduling // spring boot에서  spring의 스케줄러를 간편하게 작성
@SpringBootApplication
public class BootSetApplication {

	public static void main(String[] args) {
		SpringApplication.run(BootSetApplication.class, args);
	}

}
