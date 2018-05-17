package com.sky.business.common;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import com.sky.util.StorageLocationUtil;

/**
 * 文件操作
 * @author xiefeiye
 *
 */
@Results({
	@Result(name="fileMap",type="json",params={"includeProperties","fileMap.*,success,msg"})
})
public class FileAction extends BaseAction {
	private static final long serialVersionUID = 1L;
	private boolean success = true;
	private String msg;
	private File file;
	private Map<String,String> fileMap = new HashMap<String, String>();;
	
	/**
	 * 上传临时文件
	 * @return
	 */
	public String uploadTempFile(){
		logger.info("上传文件");
		try{
			String fileName = "uploadfile_" + UUID.randomUUID().toString();
			String uploadFilePath = StorageLocationUtil.getTempDirectory() + File.separator + fileName;
			String savePath = uploadFilePath.substring(uploadFilePath.indexOf(StorageLocationUtil.dataDir) - 1);
			if(file==null || !file.exists()){
				throw new FileNotFoundException("未找到文件");
			}
			File destFile=new File(uploadFilePath);
			FileUtils.copyFile(file, destFile);
			
			fileMap.put("name", destFile.getName());
			fileMap.put("savePath", savePath);
		}catch (Exception e) {
			logger.error("上传文件失败:"+e.getMessage(),e);
			success=false;
		}
		return "fileMap";
	}
	
	public File getFile() {
		return file;
	}
	public void setFile(File file) {
		this.file = file;
	}
	public Map<String, String> getFileMap() {
		return fileMap;
	}
	public void setFileMap(Map<String, String> fileMap) {
		this.fileMap = fileMap;
	}
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
}
