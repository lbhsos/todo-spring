package sv.programmers.planner.service.impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import sv.programmers.planner.dao.PlanDao;
import sv.programmers.planner.dto.Plan;
import sv.programmers.planner.service.PlanService;

@Service
public class PlanServiceImpl implements PlanService{
	@Autowired
	PlanDao planDao;
	
	@Override
	@Transactional
	public List<Plan> getTodo(){
		List<Plan> todoList = planDao.select("TODO");
		return todoList;
	}
	
	@Override
	@Transactional
	public List<Plan> getDone(){
		List<Plan> doneList = planDao.select("DONE");
		return doneList;
	}
	@Override
	@Transactional
	public List<Plan> getOverdue(){
		List<Plan> overdueList = planDao.selectOverdue("OVERDUE");
		return overdueList;
	}
	@Override
	@Transactional(readOnly=false)
	public int updatePlan(Plan plan){
		int updateCount = planDao.update(plan);
		return updateCount;
	}
	@Override
	@Transactional(readOnly=false)
	public int deletePlan(int id){
		int deleteCount = planDao.deleteById(id);
		return deleteCount;
	}
	
	@Override
	@Transactional(readOnly=false)
	public int completePlan(int id){
		int completeCount = planDao.completeById(id);
		return completeCount;
	}
	
	@Override
	@Transactional
	public Plan addPlan(Plan plan) {
		TimeZone timeZone;
        Date date = new Date();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        timeZone = TimeZone.getTimeZone("Asia/Seoul");
        df.setTimeZone(timeZone);
        String cur_time = df.format(date);
		plan.setRegdate(cur_time);
		plan.setType("TODO");
		int id = planDao.insert(plan);
		plan.setId(id);
        
        
		return plan;
	}
}
