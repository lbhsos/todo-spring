package sv.programmers.planner.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan(basePackages = {"sv.programmers.planner.dao","sv.programmers.planner.service"})
@Import({DBConfig.class})
public class ApplicationConfig {

}
