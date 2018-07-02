package com.sky.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

/**
 * 公共方法工具
 * @author xiefeiye
 *
 */
public class CommonMethodUtil {
	
	protected static Logger logger = Logger.getLogger(CommonMethodUtil.class);

	/**
	 * 根据url获取json类型的Map结果集
	 * @param url
	 * @return
	 */
	public static Map<String, Object> getJsonMapByUrl(String url) {
		Map<String, Object> resultMap = null;
		try {
			DefaultHttpClient client = new DefaultHttpClient();
			HttpGet httpGet = new HttpGet(url);
			HttpResponse response;
			response = client.execute(httpGet);
			HttpEntity entity = response.getEntity();
			
			if(entity != null){
				String result = EntityUtils.toString(entity,"utf-8");
				resultMap = JsonUtil.getJsonToMap(result);
			}
			httpGet.releaseConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultMap;
	}
	
	/**
     * 获取随机字母数字组合
     * @param length		字符串长度
     * @return
     */
    public static String getRandomCharAndNumr(Integer length) {
        String str = "";
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            boolean b = random.nextBoolean();
            if (b) { // 字符串
                // int choice = random.nextBoolean() ? 65 : 97; 取得65大写字母还是97小写字母
                str += (char) (97 + random.nextInt(26));// 取得大写字母
            } else { // 数字
                str += String.valueOf(random.nextInt(10));
            }
        }
        return str;
    }
	
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
	 * 将object转为Double类型
	 * @param object
	 * @return
	 */
	public static Double getDoubleByObject(Object object){
		Double in = null;
		
		if(object!=null){
			if(object instanceof Double){
				in = (Double)object;
			}else if(object instanceof String){
				in = new Double((String)object);
			}else if(object instanceof Float){
				in = new Double((Float)object);
			}
		}
		return in;
	}
	
	/**
	 * 将object转为Timestamp类型
	 * @param object
	 * @return
	 */
	public static Timestamp getTimestampByObject(Object object){
		Timestamp timestamp = null;
		
		if(object!=null){
			
			if(object instanceof String){
				if(StringUtils.isNotBlank((String)object)) {
					timestamp = new Timestamp(DateUtil.convertStr2MilliTime((String)object));
				}
			}
		}
		
		return timestamp;
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
	
	/**
	 * 将字符串列表封装为字符串，以,分隔
	 * @param list
	 * @return
	 */
	public static String packetListToStr(List<String> list) {
		String str = "";
		
		if(list != null) {
			for(String s : list) {
				if(StringUtils.isBlank(s)) {
					s = "";
				}
				
				str += s + ",";
			}
			if(str.length() > 0) {
				str = str.substring(0, str.length()-1);
			}
		}
		
		return str;
	}
	
	public static void main(String[] args) {
		String a = getRandomCharAndNumr(1);
		System.out.println(a);
	}
	
}
