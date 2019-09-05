package sv.programmers.planner.service;

import java.util.List;

import sv.programmers.planner.dto.Plan;

public interface PlanService {
	public List<Plan> getTodo();
	public List<Plan> getDone();
	public List<Plan> getOverdue();
	public int updatePlan(Plan plan);
	public int deletePlan(int id);
	public int completePlan(int id);
	public Plan addPlan(Plan plan);
}
