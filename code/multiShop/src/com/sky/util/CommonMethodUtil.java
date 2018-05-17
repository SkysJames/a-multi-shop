package com.sky.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;

/**
 * 公共方法工具
 * @author xiefeiye
 *
 */
public class CommonMethodUtil {

	/**
	 * 将object转为Integer类型
	 * @param object
	 * @return
	 */
	public static Integer getIntegerByObject(Object object){
		Integer in = null;
		
		if(object!=null){
			if(object instanceof Integer){
				in = (Integer)object;
			}else if(object instanceof String){
				in = Integer.parseInt((String)object);
			}else if(object instanceof Double){
				in = (int)((double)object);
			}else if(object instanceof Float){
				in = (int)((float)object);
			}else if(object instanceof BigDecimal){
				in = ((BigDecimal)object).intValue();
			}
		}
		
		return in;
	}
	
	/**
	 * 将object转为BigDecimal类型
	 * @param object
	 * @return
	 */
	public static BigDecimal getBigDecimalByObject(Object object){
		BigDecimal in = null;
		
		if(object!=null){
			if(object instanceof BigDecimal){
				in = (BigDecimal)object;
			}else if(object instanceof String){
				in = new BigDecimal((String)object);
			}else if(object instanceof Double){
				in = new BigDecimal((Double)object);
			}else if(object instanceof Float){
				in = new BigDecimal((Float)object);
			}
		}
		
		return in;
	}
	
	/**
	 * 将临时文件保存起来，且返回文件url，多个以,分隔
	 * @param fileList	临时文件的url列表
	 * @param filePath	需要保存的文件所在目录，data为根目录
	 * @return
	 * @throws Exception
	 */
	public static String saveFiles(List<String> fileList, String filePath) throws Exception {
		String filesUrl = "";
		
		//临时文件的url列表是否为空
		if(fileList==null || fileList.size()==0) {
			return filesUrl;
		}
		
		for(String file : fileList) {
			//需要被保存的临时文件
			file = StorageLocationUtil.getDirectory(file.substring(file.indexOf(StorageLocationUtil.dataDir) + StorageLocationUtil.dataDir.length(), file.length()));
			File fileOld = new File(file);
			if(fileOld==null || !fileOld.exists()){
				throw new FileNotFoundException("未找到文件");
			}
			
			//需要保存的文件
			String fileUrl = File.separator + StorageLocationUtil.dataDir + File.separator + filePath + File.separator + fileOld.getName();
			String uploadFilePath = StorageLocationUtil.getDirectory(filePath) + File.separator + fileOld.getName();
			File fileNew=new File(uploadFilePath);
			
			if(!fileNew.exists()) {
				FileUtils.copyFile(fileOld, fileNew);
			}
			
			filesUrl += fileUrl + ",";
		}
		
		if(StringUtils.isNotBlank(filesUrl)) {
			filesUrl = filesUrl.substring(0, filesUrl.length()-1);
		}
		
		return filesUrl;
	}
	
}
