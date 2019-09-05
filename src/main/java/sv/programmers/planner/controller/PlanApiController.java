package sv.programmers.planner.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import sv.programmers.planner.dto.Plan;
import sv.programmers.planner.service.PlanService;

@RestController
@RequestMapping(path="/api")
public class PlanApiController {
	@Autowired
	PlanService planService;
	
	@GetMapping
	public Map<String, Object> list(){
		List<Plan> todo = planService.getTodo();
		List<Plan> done = planService.getDone();
		List<Plan> overdue = planService.getOverdue();
		
		Map<String, Object> map = new HashMap<>();
		map.put("todoList", todo);
		map.put("doneList", done);
		map.put("overdueList", overdue);
		return map;
	}
	
	@PostMapping(path = "/todo", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public Plan write(@RequestBody Plan p, HttpServletRequest request) {	
		Plan result = planService.addPlan(p); 
		return result;
	}

	@DeleteMapping("/{id}")
	public Map<String, Integer> delete(@PathVariable(name="id") int id, HttpServletRequest request){
		int deleteCount = planService.deletePlan(id);
		return Collections.singletonMap("success", deleteCount);
	}
	
	@PutMapping("/{id}")
	public Map<String, Integer> complete(@PathVariable(name="id") int id, HttpServletRequest request){
		int completeCount = planService.completePlan(id);
		return Collections.singletonMap("success", completeCount);
	}
	
}
