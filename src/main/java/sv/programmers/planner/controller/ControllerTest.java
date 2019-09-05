package sv.programmers.planner.controller;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import sv.programmers.planner.config.ApplicationConfig;

public class ControllerTest {
	public static void main(String args[]) {
		ApplicationContext ac = new AnnotationConfigApplicationContext(ApplicationConfig.class);
		PlanApiController pc = new PlanApiController();
		pc.list();
	}
}
