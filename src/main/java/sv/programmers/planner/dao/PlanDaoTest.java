package sv.programmers.planner.dao;


import java.util.Date;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import sv.programmers.planner.config.ApplicationConfig;
import sv.programmers.planner.dto.Plan;
import sv.programmers.planner.service.PlanService;

public class PlanDaoTest {
	public static void main(String[] args) {
		ApplicationContext ac = new AnnotationConfigApplicationContext(ApplicationConfig.class); 
		PlanDao planDao = ac.getBean(PlanDao.class);
		Plan p = new Plan();
		p.setId(1);
		p.setTitle("programmers list");
		//p.setDeadline(new Date(2019,9,7));
		p.setPriority(1);
		int count = planDao.update(p);
		System.out.print(count);
	}
}
