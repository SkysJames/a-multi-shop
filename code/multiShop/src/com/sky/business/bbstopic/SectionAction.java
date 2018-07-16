package com.sky.business.bbstopic;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.InterceptorRefs;

import com.sky.business.bbstopic.dao.BbsSectionDao;
import com.sky.business.bbstopic.entity.Bbssection;
import com.sky.business.bbstopic.service.BbsSectionService;
import com.sky.business.common.BaseAction;
import com.sky.business.common.vo.ServiceException;
import com.sky.contants.CodeMescContants;
import com.sky.contants.EntityContants;
import com.sky.util.JsonUtil;

@InterceptorRefs({ @InterceptorRef("serverLoginStack") })
public class SectionAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Resource(name = "bbsSectionDao")
	private BbsSectionDao bbsSectionDao;

	@Resource(name = "bbsSectionService")
	private BbsSectionService bbsSectionService;

	/**
	 * 分页获取论坛版块列表
	 * 
	 * @return
	 * @throws Exception
	 */
	public String paged() throws Exception {
		try {
			@SuppressWarnings("unchecked")
			Map<String, Object> condition = JsonUtil.getJsonToMap(conditionJson);

			pager = bbsSectionService.pagedList(bbsSectionDao, Bbssection.class, condition);

			resultMap.put("pager", pager);
			resultMap.put(EntityContants.ResultMapContants.STATUS_CODE, "200");
			resultMap.put(EntityContants.ResultMapContants.MESSAGE, "论坛版块分页数据获取成功");
		} catch (Exception e) {
			logger.error(ExceptionUtils.getStackTrace(e));
			resultMap.put(EntityContants.ResultMapContants.STATUS_CODE, CodeMescContants.CodeContants.ERROR_COMMON);
			resultMap.put(EntityContants.ResultMapContants.MESSAGE, CodeMescContants.MessageContants.ERROR_COMMON);
		}
		return RESULT_MAP;
	}

	/**
	 * 根据版块ID删除版块
	 * 
	 * @return
	 */
	public String delete() {
		try {
			Map<String, Object> message = JsonUtil.getJsonToMap(conditionJson);
			bbsSectionService.delete((String) message.get("id"));

			resultMap.put(EntityContants.ResultMapContants.STATUS_CODE, "200");
			resultMap.put(EntityContants.ResultMapContants.MESSAGE, "版块删除成功");
		} catch (ServiceException e) {
			logger.error(e);
			resultMap.put(EntityContants.ResultMapContants.STATUS_CODE, e.getErrorCode());
			resultMap.put(EntityContants.ResultMapContants.MESSAGE, e.getErrorMsg());
		} catch (Exception e) {
			logger.error(ExceptionUtils.getStackTrace(e));
			resultMap.put(EntityContants.ResultMapContants.STATUS_CODE, CodeMescContants.CodeContants.ERROR_COMMON);
			resultMap.put(EntityContants.ResultMapContants.MESSAGE, CodeMescContants.MessageContants.ERROR_COMMON);
		}
		return RESULT_MAP;
	}

	/**
	 * 编辑版块
	 * 
	 * @return
	 */
	public String edit() {
		try {
			Map<String, Object> message = JsonUtil.getJsonToMap(conditionJson);
			bbsSectionService.edit(message);

			resultMap.put(EntityContants.ResultMapContants.STATUS_CODE, "200");
			resultMap.put(EntityContants.ResultMapContants.MESSAGE, "版块编辑成功");
		} catch (ServiceException e) {
			logger.error(e);
			resultMap.put(EntityContants.ResultMapContants.STATUS_CODE, e.getErrorCode());
			resultMap.put(EntityContants.ResultMapContants.MESSAGE, e.getErrorMsg());
		} catch (Exception e) {
			logger.error(ExceptionUtils.getStackTrace(e));
			resultMap.put(EntityContants.ResultMapContants.STATUS_CODE, CodeMescContants.CodeContants.ERROR_COMMON);
			resultMap.put(EntityContants.ResultMapContants.MESSAGE, CodeMescContants.MessageContants.ERROR_COMMON);
		}
		return RESULT_MAP;
	}

	/**
	 * 添加版块
	 * 
	 * @return
	 */
	public String save() {
		try {
			Map<String, Object> message = JsonUtil.getJsonToMap(conditionJson);
			bbsSectionService.add(message);
			System.out.println(message);
			resultMap.put(EntityContants.ResultMapContants.STATUS_CODE, "200");
			resultMap.put(EntityContants.ResultMapContants.MESSAGE, "新增版块成功");
		} catch (ServiceException e) {
			logger.error(e);
			resultMap.put(EntityContants.ResultMapContants.STATUS_CODE, e.getErrorCode());
			resultMap.put(EntityContants.ResultMapContants.MESSAGE, e.getErrorMsg());
		} catch (Exception e) {
			logger.error(ExceptionUtils.getStackTrace(e));
			resultMap.put(EntityContants.ResultMapContants.STATUS_CODE, CodeMescContants.CodeContants.ERROR_COMMON);
			resultMap.put(EntityContants.ResultMapContants.MESSAGE, CodeMescContants.MessageContants.ERROR_COMMON);
		}
		return RESULT_MAP;
	}

	/**
	 * 编辑版块
	 * 
	 * @return
	 */
	public String findAllSection() {
		try {
			List<Bbssection> list = bbsSectionService.findAll(Bbssection.class,"createTime", true);
			resultMap.put("resultList", list);
			resultMap.put(EntityContants.ResultMapContants.STATUS_CODE, "200");
			resultMap.put(EntityContants.ResultMapContants.MESSAGE, "版块编辑成功");
		} catch (Exception e) {
			logger.error(ExceptionUtils.getStackTrace(e));
			resultMap.put(EntityContants.ResultMapContants.STATUS_CODE, CodeMescContants.CodeContants.ERROR_COMMON);
			resultMap.put(EntityContants.ResultMapContants.MESSAGE, CodeMescContants.MessageContants.ERROR_COMMON);
		}
		return RESULT_MAP;
	}

	@Override
	public Map<String, Object> getResultMap() {
		return resultMap;
	}

	@Override
	public String getConditionJson() {
		return conditionJson;
	}

	@Override
	public void setConditionJson(String conditionJson) {
		this.conditionJson = conditionJson;
	}
}
