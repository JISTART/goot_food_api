package boot.set.test;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

// SpringBootServletInitializer -> springboot에서 war로 배포하기위해서 상속이 필요함
public class ServletInitializer extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(BootSetApplication.class);
	}

}
