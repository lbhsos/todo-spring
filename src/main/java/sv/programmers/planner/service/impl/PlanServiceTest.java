package sv.programmers.planner.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import sv.programmers.planner.config.ApplicationConfig;
import sv.programmers.planner.dto.Plan;
import sv.programmers.planner.service.PlanService;

public class PlanServiceTest {
	public static void main(String args[]) {
		ApplicationContext ac = new AnnotationConfigApplicationContext(ApplicationConfig.class);
		PlanService planService = ac.getBean(PlanService.class);
//		
//		Plan p = new Plan();
//		p.setTitle("Hello");
//		p.setPriority(1);
//		p.setDeadline(new Date(2019,10,3));
//		Plan ret = planService.addPlan(p);
//		System.out.println(ret.getDeadline());
		List<Plan> list = planService.getTodo();
		for (int i=0; i<list.size(); i++) {
			System.out.println(list.get(i).getDeadline());
		}
	}
}
