package com.sky.business.common.dao.impl;

import java.sql.CallableStatement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.jdbc.object.StoredProcedure;

import com.sky.business.common.dao.BaseJdbcDao;
import com.sky.business.common.vo.Pager;
import com.sky.util.TdrStoredProcedure;

/**
 * jdbc底层dao实现层
 * @author xiefeiye
 *
 */
public class BaseJdbcDaoImpl extends JdbcDaoSupport implements BaseJdbcDao {
	
	private static final Log log = LogFactory.getLog(BaseJdbcDaoImpl.class);
	
	/**
	 * 是否显示sql语句
	 */
	private boolean showSql; 
	
	@Override
	public Map<String, Object> findUniqueById(String tableName, String idName, String id, String select) {
		if(select==null){
			select = "*";
		}
		
		String sql = "select " + select + " from " + tableName + " where " + idName + "=?";
		Object[] vals = new Object[]{id};
		
		outputSql(sql);
		
		Map<String, Object> result = null;
		try {
			result = getJdbcTemplate().queryForMap(sql, vals);
		} catch (DataAccessException e) {
			log.error(e.getMessage());
		}
		return result;
	}
	
	@Override
	public void ExecuteByJdbc(String sql) {
		outputSql(sql);
		getJdbcTemplate().execute(sql);
	}
	
	@Override
	public void update(String sql, Object[] bindedVariables){
		outputSql(sql);
		getJdbcTemplate().update(sql, bindedVariables);
	}
	
	@Override
	public int queryForInt(String sql, Object[] bindedVariables){
		outputSql(sql);
		return getJdbcTemplate().queryForObject(sql, bindedVariables, Integer.class);
	}
	
	@SuppressWarnings("unchecked")
	public Object queryForObject(String sql, Class requiredType){
		return getJdbcTemplate().queryForObject(sql, requiredType);
	}
	
	@SuppressWarnings("unchecked")
	public Object queryForObject(String sql, Class requiredType, Object...args){
		return getJdbcTemplate().queryForObject(sql, requiredType, args);
	}
	
	@Override
	public boolean batchExecuteByJdbc(List<String> sqlList) {
		boolean result = true;
		if(sqlList!=null){
			getJdbcTemplate().batchUpdate(sqlList.toArray(new String[sqlList.size()]));
		}else {
			result=false;
		}
		return result;
	}

	@Override
	public List getResultByJdbc(String sql) {
		outputSql(sql);
		List list = null;
		try {
			list = getJdbcTemplate().queryForList(sql);
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	
	@Override
	public List getResultByJdbc(String sql, Object[] args) {
		outputSql(sql);
		List list = null;
		try {
			list = getJdbcTemplate().queryForList(sql, args);
		} catch (DataAccessException e) {
			log.error(e.getMessage());
		}
		return list;
	}

	@Override
	public List getResultByJdbcWithMaxRows(String sql, int maxRows) {
		Pager pager = this.getPagedResultByJdbc(sql, 1, maxRows);

		List list = new ArrayList();
		if (pager != null)
			list = pager.getResultList();
		return list;
	}
	
	@Override
	public StoredProcedure getStoredProcedure(String procName) {
		TdrStoredProcedure tdrStoredProcedure = new TdrStoredProcedure(getDataSource(), procName);
		return tdrStoredProcedure;
	}

	@Override
	public Map executeStoredProcedure(String procName, List SqlParameters,
			List SqlOutParameters, Map param) {
		StoredProcedure storedProcedure = this.getStoredProcedure(procName);
		for (int i = 0; i < SqlParameters.size(); i++) {
			storedProcedure.declareParameter((SqlParameter) SqlParameters
					.get(i));
		}
		for (int i = 0; i < SqlOutParameters.size(); i++) {
			storedProcedure.declareParameter((SqlOutParameter) SqlOutParameters
					.get(i));
		}
		Map results = storedProcedure.execute(param);
		return results;

	}
	
	@Override
	public String executeFunction(String procName,  List params) {
		String sql = getSqlStr(procName, params.size());
		String str = "";
		try {
			CallableStatement call = this.getDataSource().getConnection().prepareCall(sql);
			call.registerOutParameter(1, 12);

			for (int i = 0, j = 2; i < params.size(); j++) {
				call.setObject(j, params.get(i));
				i++;
			}
			if (!call.execute()) {
				str = String.valueOf(call.getObject(1));
			}
			call.close();
		}catch(Exception e){
			e.printStackTrace();
		}

		return str;

	}
	
	@Override
	public <T> List<T> query(String sql,RowMapper<T> rowMapper) {
		return getJdbcTemplate().query(sql, rowMapper);
	}

	@Override
	public <T> List<T> query(String sql, Object[] params, RowMapper<T> rowMapper) {
		return getJdbcTemplate().query(sql, params, rowMapper);
	}
	
	@Override
	public Pager getPagedResultWithStartRowIndexByJdbc(String sql, int startRowIndex, int pageSize) {
		return getPagedResultWithStartRowIndexByJdbc(sql, startRowIndex, pageSize, null);
	}
	
	@Override
	public Pager getPagedResultWithStartRowIndexByJdbc(String sql,
			int startRowIndex, int pageSize, List<Object> bindedVariables) {
		if (sql == null || sql.equals("")) {
			throw new IllegalArgumentException("sql 为空,请先初始化！");
		}

		//变量数组
		Object[] args = null;
		if (bindedVariables != null) {
			args = bindedVariables.toArray();
		}

		//计算总记录数
		int totalCount = 0;
		StringBuffer totalSQL = new StringBuffer(" Select count(*) FROM ( ");
		totalSQL.append(sql);
		totalSQL.append(" ) totalTable ");
		log.info("count sql:" + totalSQL.toString());
		totalCount = getJdbcTemplate().queryForObject(totalSQL.toString(), args, Integer.class);
		
		//计算结束的行数(包括)
		int lastRowIndex = 0;
		if(totalCount >= (startRowIndex + pageSize)) {
			lastRowIndex = startRowIndex + pageSize;
		} else if(totalCount < (startRowIndex + pageSize) && totalCount > startRowIndex) {
			lastRowIndex = totalCount - 1;
		} else {
			throw new IllegalArgumentException("startRowIndex大于或等于总数量！");
		}

		//分页对象
		Pager pager = new Pager();
		pager.setStartIndex(startRowIndex);
		pager.setLastIndex(lastRowIndex);
		pager.setTotalCount(totalCount);

		// 构造oracle数据库的分页语句
		String fullSql = this.buildPageSql(sql, pager.getStartIndex(), pager.getLastIndex());

		//查询获取结果集
		long start = Calendar.getInstance().getTimeInMillis();
		log.info("executing sql : " + fullSql);
		List list = getJdbcTemplate().queryForList(fullSql, args);
		long end = Calendar.getInstance().getTimeInMillis();
		log.info("cost " + (end - start) + " ms to execute sql ! ");
		
		// 装入结果集
		pager.setResultList(list);
		return pager;
	}
	
	@Override
	public Pager getPagedResultByJdbc(String sql, int currentPage, int pageSize) {
		return this.getPagedResultByJdbc(sql, currentPage, pageSize, null);
	}

	@Override
	public Pager getPagedResultByJdbc(String sql, int currentPage, int pageSize, List<Object> bindedVariables) {
		if (sql == null || sql.equals("")) {
			throw new IllegalArgumentException("sql 为空,请先初始化！");
		}

		//变量数组
		Object[] args = null;
		if (bindedVariables != null) {
			args = bindedVariables.toArray();
		}

		//计算总记录数
		int totalCount = 0;
		StringBuffer totalSQL = new StringBuffer(" Select count(*) FROM ( ");
		totalSQL.append(sql);
		totalSQL.append(" ) totalTable ");
		log.info("count sql:" + totalSQL.toString());
		totalCount = getJdbcTemplate().queryForObject(totalSQL.toString(), args, Integer.class);

		//分页对象
		Pager pager = new Pager(currentPage, pageSize, totalCount);

		// 构造oracle数据库的分页语句
		String fullSql = this.buildPageSql(sql, pager.getStartIndex(), pager.getLastIndex());

		//查询获取结果集
		long start = Calendar.getInstance().getTimeInMillis();
		log.info("executing sql : " + fullSql);
		List list = getJdbcTemplate().queryForList(fullSql, args);
		long end = Calendar.getInstance().getTimeInMillis();
		log.info("cost " + (end - start) + " ms to execute sql ! ");
		
		// 装入结果集
		pager.setResultList(list);
		return pager;
	};
	
	@Override
	public <T> List<T> query(String sql, Object[] params, RowMapper<T> rowMapper, int startIdx, int endIdx) {
		return getJdbcTemplate().query(buildPageSql(sql, startIdx, endIdx), params, rowMapper);
	}
	/**
	 * 封装分页sql语句
	 * @param sql
	 * @param startIdx 开始行数，不包括该行
	 * @param endIdx 结束行数，包括该行
	 * @return
	 */
	private String buildPageSql(String sql, int startIdx, int endIdx){
		// 构造oracle数据库的分页语句
		StringBuffer fullSql = new StringBuffer();
		
		// 组装完整查询sql语句
		fullSql.append("select b.* FROM ( ");
		fullSql.append(" select a.* ,ROWNUM row_num FROM ( ");
		fullSql.append(sql);
		fullSql.append("  ) a where ROWNUM <= " + endIdx);
		fullSql.append(" ) b Where row_num > " + startIdx);
		
		return fullSql.toString();
	}
	
	/**
	 * 输出sql到日志文件或控制台
	 * @param sql
	 */
	private void outputSql(String sql){
		if(showSql){
			log.info(sql);
		}
	}
	
	private String getSqlStr(String fname, int size) {
		StringBuffer sql = new StringBuffer();
		//String call="{? call pro(?,?,?)}";
		sql.append("{ ? = call ");
		sql.append(fname);
		sql.append("(");
		for (int i = 0; i < size; i++) {
			sql.append("?");
			if (i < size - 1) {
				sql.append(",");
			}
		}
		sql.append(") }");

		return sql.toString();
	}
	
	public boolean isShowSql() {
		return showSql;
	}

	public void setShowSql(boolean showSql) {
		log.info("showSql is " + showSql);
		this.showSql = showSql;
	}
	
}
