package com.sky.util;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class StorageLocationUtil {
private static final Log log = LogFactory.getLog(StorageLocationUtil.class);
	
	private static String rootDirectory;
	
	public static String dataDir = "data";
	
	/**
	 * 返回文件存储根目录
	 * @return
	 */
	public static String getRootDirectory() {

		if (rootDirectory == null) {
			ResourcesDirectorLocator rdl = BeanDefinedLocator.getInstance().getBean(ResourcesDirectorLocator.class);
			if (rdl == null || rdl.getRoot() == null) {
				throw new NullPointerException("class ResourcesDirectorLocator is not found!");
			}
			String appDir = rdl.getRoot();
			File tmpfile = new File(appDir);
			// 与tdr目录平级
			rootDirectory = tmpfile.getParent() + File.separator + dataDir;
			File datafile = new File(rootDirectory);
			// 不存在则创建
			if (!datafile.exists()) {
				datafile.mkdir();
			}
		}
		log.debug("rootDirectory=" + rootDirectory);
		return rootDirectory;
	}
	
	/**
	 * 获取指定目录
	 * @param dirName
	 * @return
	 */
	public static String getDirectory(String dirName) {
		String dirPath = getRootDirectory() + File.separator + dirName;
		File dirFile = new File(dirPath);
		// 不存在则创建
		if (!dirFile.exists()) {
			dirFile.mkdirs();
		}

		return dirPath;
	}
	
	/**
	 * 获取系统临时目录
	 * @return
	 */
	public static String getTempDirectory() {
		String tempDirPath = getRootDirectory() + File.separator + "temp";
		File tempDirFile = new File(tempDirPath);
		// 不存在则创建
		if (!tempDirFile.exists()) {
			tempDirFile.mkdirs();
		}

		return tempDirPath;
	}
	
	/**
	 * 返回存储http基础路径，例如：
	 * http://192.168.13.11:80/data
	 * <br/>
	 * <br/>
	 * 业务模块追加子路径使用，如下载文件：
	 * http://192.168.13.11/data/export/402889a41f4a97b4011f4a99292c0001/20131126163313.csv
	 * @param request
	 * @return
	 */
	public static String getBaseUrl(HttpServletRequest request){
		String baseUrl = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+ "/" +dataDir;
//		log.debug("baseUrl=" + baseUrl);
		return baseUrl;
	}
	
	/**
	 * 获取系统临时目录HTTP地址
	 * @return
	 */
	public static String getTempHttpUrl(HttpServletRequest request) {
		return getHttpUrl(request, "temp");
	}
	
	/**
	 * 返回HTTP访问地址
	 * @param request
	 * @param path
	 * @return
	 */
	public static String getHttpUrl(HttpServletRequest request, String path){
		return getBaseUrl(request) + "/" + path;
	}
	
	/**
	 * 清空指定目录
	 */
	public static void clearFile(String dirName) {
		String dirPath = getRootDirectory() + File.separator + dirName;
		File dir = new File(dirPath);
		
		if (dir.exists()) {
			try {
				FileUtils.cleanDirectory(dir);
				log.info("清空目录：" + dirPath);
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			log.info("清空目录(无文件)：" + dirPath);
		}
	}
	
	/**
	 * 清空临时目录
	 */
	public static void clearTemp() {
		// 需要清空的根目录
		String[] needDelBasePaths = new String[] {
				StorageLocationUtil.getTempDirectory() // 系统临时目录
			};
		
		File tempDirectory = null;
		
		for (String path : needDelBasePaths) {
			tempDirectory = new File(path);
			if (tempDirectory.exists()) {
				try {
					FileUtils.cleanDirectory(tempDirectory);
					log.info("清空目录：" + path);
				} catch (IOException e) {
					e.printStackTrace();
				}
			} else {
				log.info("清空目录(无文件)：" + path);
			}
		}
	}
}
