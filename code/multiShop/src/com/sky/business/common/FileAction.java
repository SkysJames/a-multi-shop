package com.sky.business.common;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FileUtils;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.InterceptorRefs;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.json.simple.JSONObject;

import com.sky.contants.FileContants;
import com.sky.util.StorageLocationUtil;

/**
 * 文件操作
 * @author xiefeiye
 *
 */
@Results({
	@Result(name="fileMap",type="json",params={"includeProperties","fileMap.*,success,msg"})
})
@InterceptorRefs({@InterceptorRef("serverLoginStack")})
public class FileAction extends BaseAction {
	private static final long serialVersionUID = 1L;
	private boolean success = true;
	private String msg;
	private File file;
	private Map<String,String> fileMap = new HashMap<String, String>();;
	private File imgFile;
	
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
	
	/**
	 * 上传kindeditor的文件
	 * @return
	 */
	public void uploadKindEditorFile(){
		logger.info("上传kindeditor文件");
		
		try{
			//对页面的输出对象
			PrintWriter out = resp.getWriter();
			//目录名称
			String dirName = req.getParameter("dir");
			//文件名
			String fileName = "kindeditor_" + UUID.randomUUID().toString();
			
			//文件上传的绝对路径
			String uploadFilePath = StorageLocationUtil.getDirectory(FileContants.KINDEDITOR_FILE) + File.separator;
			
			//定义允许上传的文件扩展名
			HashMap<String, String> extMap = new HashMap<String, String>();
			extMap.put("image", "gif,jpg,jpeg,png,bmp");
			extMap.put("flash", "swf,flv");
			extMap.put("media", "swf,flv,mp3,wav,wma,wmv,mid,avi,mpg,asf,rm,rmvb");
			extMap.put("file", "doc,docx,xls,xlsx,ppt,htm,html,txt,zip,rar,gz,bz2");
			
			resp.setContentType("text/html; charset=UTF-8");
			
			if(!ServletFileUpload.isMultipartContent(req)){
				out.println(getError("请选择文件。"));
				return;
			}
			
			if (dirName == null) {
				dirName = "image";
			}
			if(!extMap.containsKey(dirName)){
				out.println(getError("目录名不正确。"));
				return;
			}
			
			//创建文件夹
			uploadFilePath += dirName + "/";
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
			String ymd = sdf.format(new Date());
			uploadFilePath += ymd + "/";
			File saveDirFile = new File(uploadFilePath);
			if (!saveDirFile.exists()) {
				saveDirFile.mkdirs();
			}
			
			//文件上传的完整路径
			uploadFilePath += fileName;
			//文件保存文件URL
			String savePath = uploadFilePath.substring(uploadFilePath.indexOf(StorageLocationUtil.dataDir) - 1);
			if(imgFile==null || !imgFile.exists()){
				throw new FileNotFoundException("未找到文件");
			}
			File destFile=new File(uploadFilePath);
			FileUtils.copyFile(imgFile, destFile);
			
			JSONObject obj = new JSONObject();
			obj.put("error", 0);
			obj.put("url", savePath);
			out.println(obj.toJSONString());
		}catch (Exception e) {
			logger.error("上传kindeditor文件失败:"+e.getMessage(),e);
		}
	}
	
	/**
	 * 获取错误信息
	 * @param message
	 * @return
	 */
	private String getError(String message) {
		JSONObject obj = new JSONObject();
		obj.put("error", 1);
		obj.put("message", message);
		return obj.toJSONString();
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

	public File getImgFile() {
		return imgFile;
	}

	public void setImgFile(File imgFile) {
		this.imgFile = imgFile;
	}
}
