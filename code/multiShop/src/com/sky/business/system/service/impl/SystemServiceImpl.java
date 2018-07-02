package com.sky.business.system.service.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.sky.business.common.service.impl.BaseServiceImpl;
import com.sky.business.common.vo.ServiceException;
import com.sky.business.system.entity.SysParameter;
import com.sky.business.system.service.SysParameterService;
import com.sky.business.system.service.SystemService;
import com.sky.contants.CodeMescContants;
import com.sky.contants.FileContants;
import com.sky.util.CommonMethodUtil;

/**
 * 系统管理service服务层
 * @author xiefeiye
 *
 */
@Service("systemService")
public class SystemServiceImpl extends BaseServiceImpl implements SystemService {
	
	@Resource(name="sysParameterService")
	SysParameterService sysParameterService;
	
	@Override
	public Map<String, Object> getSystemInfo() throws Exception {
		Map<String, Object> systemInfo = new HashMap<String, Object>();
		List<String> systemIconList = new ArrayList<String>();
		List<String> systemLogoList = new ArrayList<String>();
		List<String> systemPictureList = new ArrayList<String>();
		List<String> systemPictureHrefList = new ArrayList<String>();
		List<String> wechatPictureList = new ArrayList<String>();
		
		List<SysParameter> sysList = sysParameterService.findBy(SysParameter.class, "type", "system");
		for(SysParameter sys : sysList) {
			systemInfo.put(sys.getName(), sys.getValue());
		}
		
		if(systemInfo.containsKey("system_icon") && StringUtils.isNotBlank((String)systemInfo.get("system_icon"))) {
			String[] system_icons = ((String)systemInfo.get("system_icon")).split(",");
			systemIconList = Arrays.asList(system_icons);
		}
		if(systemInfo.containsKey("system_logo") && StringUtils.isNotBlank((String)systemInfo.get("system_logo"))) {
			String[] system_logos = ((String)systemInfo.get("system_logo")).split(",");
			systemLogoList = Arrays.asList(system_logos);
		}
		if(systemInfo.containsKey("system_picture") && StringUtils.isNotBlank((String)systemInfo.get("system_picture"))) {
			String[] system_pictures = ((String)systemInfo.get("system_picture")).split(",");
			systemPictureList = Arrays.asList(system_pictures);
		}
		if(systemInfo.containsKey("system_picture_href") && StringUtils.isNotBlank((String)systemInfo.get("system_picture_href"))) {
			String[] system_picture_hrefs = ((String)systemInfo.get("system_picture_href")).split(",");
			systemPictureHrefList = Arrays.asList(system_picture_hrefs);
		}
		if(systemInfo.containsKey("wechat_pic") && StringUtils.isNotBlank((String)systemInfo.get("wechat_pic"))) {
			String[] wechat_pics = ((String)systemInfo.get("wechat_pic")).split(",");
			wechatPictureList = Arrays.asList(wechat_pics);
		}
		
		systemInfo.put("systemIconList", systemIconList);
		systemInfo.put("systemLogoList", systemLogoList);
		systemInfo.put("systemPictureList", systemPictureList);
		systemInfo.put("systemPictureHrefList", systemPictureHrefList);
		systemInfo.put("wechatPictureList", wechatPictureList);
		
		return systemInfo;
	}

	@Override
	public void saveSystemInfo(Map<String, Object> systemInfo) throws Exception {
		if(systemInfo==null || systemInfo.isEmpty()) {
			throw new ServiceException(CodeMescContants.CodeContants.ERROR_SPREAD_NULL,CodeMescContants.MessageContants.ERROR_SPREAD_NULL);
		}
		
		if(systemInfo.containsKey("system_name")) {
			sysParameterService.saveValue("system_name", (String)systemInfo.get("system_name"));
		}
		if(systemInfo.containsKey("system_column")) {
			sysParameterService.saveValue("system_column", (String)systemInfo.get("system_column"));
		}
		if(systemInfo.containsKey("company_name")) {
			sysParameterService.saveValue("company_name", (String)systemInfo.get("company_name"));
		}
		if(systemInfo.containsKey("company_address")) {
			sysParameterService.saveValue("company_address", (String)systemInfo.get("company_address"));
		}
		if(systemInfo.containsKey("company_record")) {
			sysParameterService.saveValue("company_record", (String)systemInfo.get("company_record"));
		}
		if(systemInfo.containsKey("copy_right")) {
			sysParameterService.saveValue("copy_right", (String)systemInfo.get("copy_right"));
		}
		if(systemInfo.containsKey("telephone")) {
			sysParameterService.saveValue("telephone", (String)systemInfo.get("telephone"));
		}
		if(systemInfo.containsKey("phone")) {
			sysParameterService.saveValue("phone", (String)systemInfo.get("phone"));
		}
		if(systemInfo.containsKey("email")) {
			sysParameterService.saveValue("email", (String)systemInfo.get("email"));
		}
		if(systemInfo.containsKey("qq")) {
			sysParameterService.saveValue("qq", (String)systemInfo.get("qq"));
		}
		if(systemInfo.containsKey("service_time")) {
			sysParameterService.saveValue("service_time", (String)systemInfo.get("service_time"));
		}
		
		if(systemInfo.containsKey("systemIconList")) {
			//图片存放的目录
			String picPath = FileContants.SYSTEM_FILE + File.separator + "system";
			//保存图片
			String picture = CommonMethodUtil.saveFiles((List<String>) systemInfo.get("systemIconList"), picPath);
			
			sysParameterService.saveValue("system_icon", picture);
		}
		
		if(systemInfo.containsKey("systemLogoList")) {
			//图片存放的目录
			String picPath = FileContants.SYSTEM_FILE + File.separator + "system";
			//保存图片
			String picture = CommonMethodUtil.saveFiles((List<String>) systemInfo.get("systemLogoList"), picPath);
			
			sysParameterService.saveValue("system_logo", picture);
		}
		
		if(systemInfo.containsKey("systemPictureList")) {
			//图片存放的目录
			String picPath = FileContants.SYSTEM_FILE + File.separator + "system";
			//保存图片
			String picture = CommonMethodUtil.saveFiles((List<String>) systemInfo.get("systemPictureList"), picPath);
			
			sysParameterService.saveValue("system_picture", picture);
		}
		
		if(systemInfo.containsKey("systemPictureHrefList")) {
			List<String> systemPictureHrefList = (List<String>) systemInfo.get("systemPictureHrefList");
			String pictureHref = CommonMethodUtil.packetListToStr(systemPictureHrefList);
			
			sysParameterService.saveValue("system_picture_href", pictureHref);
		}
		
		if(systemInfo.containsKey("wechatPictureList")) {
			//图片存放的目录
			String picPath = FileContants.SYSTEM_FILE + File.separator + "system";
			//保存图片
			String picture = CommonMethodUtil.saveFiles((List<String>) systemInfo.get("wechatPictureList"), picPath);
			
			sysParameterService.saveValue("wechat_pic", picture);
		}
		
	}

}
