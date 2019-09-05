package sv.programmers.planner.dao;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import sv.programmers.planner.dto.Plan;
import static sv.programmers.planner.dao.PlanDaoSqls.*;

@Repository
public class PlanDao {
	private JdbcTemplate jdbctemplate;
	private NamedParameterJdbcTemplate jdbc;
	private SimpleJdbcInsert insertAction;
	private RowMapper<Plan> rowMap = BeanPropertyRowMapper.newInstance(Plan.class);
	
	public PlanDao(DataSource dataSource) {
		this.jdbctemplate = new JdbcTemplate(dataSource);
		this.jdbc = new NamedParameterJdbcTemplate(dataSource);
		this.insertAction = new SimpleJdbcInsert(dataSource)
				.withTableName("plan")
				.usingGeneratedKeyColumns("id");
	}
	
	public List<Plan> select(String type){
		Map<String, String> params = new HashMap<>();
		params.put("type", type);
		return jdbc.query(SELECT_TODO,params, rowMap);
	}
	
	public List<Plan> selectOverdue(String type){
		return jdbc.query(SELECT_OVERDUE,rowMap);
	}
	public int deleteById(int id) {
		Map<String, ?> params = Collections.singletonMap("id",id);
		return jdbc.update(DELETE_BY_ID, params);
	}
	
	public int completeById(int id) {
		Map<String, ?> params = Collections.singletonMap("id",id);
		return jdbc.update(COMPLETE_BY_ID, params);
	}
	
	public int insert(Plan plan) {
		SqlParameterSource params = new BeanPropertySqlParameterSource(plan);
		return insertAction.executeAndReturnKey(params).intValue();
	}
	
	public int update(Plan plan) {
		SqlParameterSource params = new BeanPropertySqlParameterSource(plan);
		return jdbc.update(UPDATE_PLAN, params);
	}
}
