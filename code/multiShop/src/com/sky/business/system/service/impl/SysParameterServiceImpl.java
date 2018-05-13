package com.sky.business.system.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.sky.business.common.service.impl.BaseServiceImpl;
import com.sky.business.system.entity.SysParameter;
import com.sky.business.system.service.SysParameterService;

/**
 * 系统参数Service类
 * @author Sky James
 *
 */
@Service("sysParameterService")
public class SysParameterServiceImpl extends BaseServiceImpl implements SysParameterService {

	@Override
	public String getStringValue(String name, String defaultValue) {
		String returnValue = defaultValue;
		try {
			SysParameter sysParameter = this.findByUnique(SysParameter.class, "name", name);
			if(null != sysParameter){
				returnValue = sysParameter.getValue();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return returnValue;
	}

	@Override
	public Integer getIntValue(String name, Integer defaultValue) {
		Integer returnValue = defaultValue;
		try {
			SysParameter sysParameter = this.findByUnique(SysParameter.class, "name", name);
			if(null != sysParameter){
				String value = sysParameter.getValue();
				if(StringUtils.isNotBlank(value)){
					returnValue = Integer.parseInt(value);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return returnValue;
	}

	@Override
	public Boolean getBooleanValue(String name, Boolean defaultValue) {
		Boolean returnValue = defaultValue;
		try {
			SysParameter sysParameter = this.findByUnique(SysParameter.class, "name", name);
			if(null != sysParameter){
				String value = sysParameter.getValue();
				if(StringUtils.isNotBlank(value)){
					returnValue = "true".equals(value);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return returnValue;
	}

	@Override
	public void saveValue(String name, String value) {
		try {
			SysParameter sysParameter = this.findByUnique(SysParameter.class, "name", name);
			if(null != sysParameter){
				sysParameter.setValue(value);
				this.save(sysParameter);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	
	
}
