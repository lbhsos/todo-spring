package sv.programmers.planner.dao;

public class PlanDaoSqls {
	public static final String SELECT_TODO = "SELECT id, title, priority, deadline FROM plan WHERE DATE(deadline) > DATE(now()) AND type = :type ORDER BY priority";
	public static final String SELECT_OVERDUE = "SELECT id, title, priority, deadline FROM plan WHERE DATE(deadline) <= DATE(now()) AND type<>'DONE' ORDER BY priority";
	public static final String DELETE_BY_ID = "DELETE FROM plan WHERE id = :id";
	public static final String COMPLETE_BY_ID = "UPDATE PLAN SET type = 'DONE' WHERE id = :id";
	public static final String UPDATE_PLAN = "UPDATE PLAN SET title = :title, priority = :priority, deadline = :deadline WHERE id = :id";
}
