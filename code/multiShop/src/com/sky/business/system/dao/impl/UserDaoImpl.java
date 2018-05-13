package com.sky.business.system.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.sky.business.common.dao.impl.BaseDaoImpl;
import com.sky.business.common.vo.Pager;
import com.sky.business.system.dao.UserDao;
import com.sky.contants.EntityContants.UserContants;
import com.sky.util.CommonMethodUtil;
import com.sky.util.DateUtil;

/**
 * 用户Dao实现类
 * @author Sky James
 *
 */
@Service("userDao")
public class UserDaoImpl extends BaseDaoImpl implements UserDao {

	@Override
	protected StringBuffer getPackageHql(StringBuffer hqlBuffer, List<Object> values, Map<String, Object> condition) {
		String sort = "createTime asc";
		int userStatus = UserContants.UserStatus.USING;
		
		//排序
		if(condition.containsKey("sort") && StringUtils.isNotBlank((String)condition.get("sort"))){
			sort = (String)condition.get("sort");
		}
		
		//用户状态
		if(condition.containsKey("userStatus") && null!=condition.get("userStatus")){
			userStatus = CommonMethodUtil.getIntegerByObject(condition.get("userStatus"));
		}
		hqlBuffer.append(" and userStatus = ? ");
		values.add(userStatus);
		
		//关键字
		if(condition.containsKey("keywords") && StringUtils.isNotBlank((String)condition.get("keywords"))){
			String keywords = (String)condition.get("keywords");
			hqlBuffer.append(" and (name like ? or remark like ?) ");
			values.add("%" + keywords + "%");
			values.add("%" + keywords + "%");
		}
		
		//登录时间
		if(condition.containsKey("loginTimeA") && StringUtils.isNotBlank((String)condition.get("loginTimeA"))
				&& condition.containsKey("loginTimeZ") && StringUtils.isNotBlank((String)condition.get("loginTimeZ"))){
			Date loginTimeA = DateUtil.convertStr2Date((String)condition.get("loginTimeA"));
			Date loginTimeZ = DateUtil.convertStr2Date((String)condition.get("loginTimeZ"));
			hqlBuffer.append(" and loginTime between ? and ? ");
			values.add(loginTimeA);
			values.add(loginTimeZ);
		}
		
		hqlBuffer.append(" order by ").append(sort);
		
		return hqlBuffer;
	}
	
	@Override
	public Pager pagedList(final Map<String, Object> condition, int pageNo, int pageSize) {
		StringBuffer hqlBuffer = new StringBuffer("from User where 1=1");
		List<Object> values = new ArrayList<Object>();
		
		//封装hql语句
		hqlBuffer = this.getPackageHql(hqlBuffer, values, condition);
		
		log.info("用户信息，分页查询hql：" + hqlBuffer.toString() + ", 条件：" + values);
		
		Pager pager = this.getBaseHibernateDao().pagedQuery(hqlBuffer.toString(), pageNo, pageSize, values.toArray());
		
		return pager;
	}

}
