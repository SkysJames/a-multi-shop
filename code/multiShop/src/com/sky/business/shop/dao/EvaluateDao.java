package com.sky.business.shop.dao;

import java.util.List;
import java.util.Map;

import com.sky.business.common.dao.BaseDao;

/**
 * 评价Dao接口
 * @author Sky James
 *
 */
public interface EvaluateDao extends BaseDao {
	
	/**
	 * 判断某一用户今天是否已经评价该对象
	 * @param userId
	 * @param objId
	 * @param tableName
	 * @return
	 */
	public boolean isEvaluated(String userId, String objId, String tableName);
	
}
