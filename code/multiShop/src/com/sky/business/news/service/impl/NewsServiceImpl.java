package com.sky.business.news.service.impl;

import java.io.File;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sky.business.common.service.impl.BaseServiceImpl;
import com.sky.business.common.vo.LoginUser;
import com.sky.business.common.vo.Pager;
import com.sky.business.common.vo.ServiceException;
import com.sky.business.news.dao.NewsDao;
import com.sky.business.news.entity.News;
import com.sky.business.news.service.NewsService;
import com.sky.contants.CodeMescContants;
import com.sky.contants.FileContants;
import com.sky.util.CommonMethodUtil;

/**
 * 新闻Service类
 * @author Sky James
 *
 */
@Service("newsService")
public class NewsServiceImpl extends BaseServiceImpl implements NewsService {

	@Resource(name = "newsDao")
	private NewsDao newsDao;
	
	@Override
	public Pager pagedList(Map<String, Object> condition) throws Exception {
		Integer pageNo = Pager.DEFAULT_CURRENT_PAGE;
		Integer pageSize = Pager.DEFAULT_PAGE_SIZE;
		
		if(condition.containsKey("pageNo") && condition.containsKey("pageSize")) {
			pageNo = CommonMethodUtil.getIntegerByObject(condition.get("pageNo"));
			pageSize = CommonMethodUtil.getIntegerByObject(condition.get("pageSize"));
		}
		
		return newsDao.pagedList(condition, pageNo, pageSize);
	}

	@Override
	public News add(Map<String,Object> news, LoginUser loginUser) throws Exception {
		//查找数据库中是否名字相同的新闻
		News hasNews = this.findByUnique(News.class, "name", (String)news.get("name"));
		if(hasNews != null){
			throw new ServiceException(CodeMescContants.CodeContants.NEWS_EXIST, CodeMescContants.MessageContants.NEWS_EXIST);
		}
		
		//当前时间
		Date now = Calendar.getInstance().getTime();
		//图片存放的目录
		String picPath = FileContants.NEWS_FILE + File.separator + (String)news.get("name");
		//保存图片
		String picture = CommonMethodUtil.saveFiles((List<String>) news.get("picPathList"), picPath);
		
		News newsSave = new News();
		newsSave.setId(UUID.randomUUID().toString());
		newsSave.setCreateTime(new Timestamp(now.getTime()));
		newsSave.setUpdateTime(new Timestamp(now.getTime()));
		newsSave.setCreateUser(loginUser.getUserId());
		newsSave.setUpdateUser(loginUser.getUserId());
		newsSave.setNewsType(CommonMethodUtil.getIntegerByObject(news.get("newsType")));
		newsSave.setName((String) news.get("name"));
		newsSave.setContent((String) news.get("content"));
		newsSave.setPicture(picture);
		
		this.save(newsSave);
		return newsSave;
	}
	
	@Override
	public void delete(String newsId, LoginUser loginUser) throws Exception {
		//查找数据库中是否存在该新闻
		News news = this.findByID(News.class, newsId);
		if(news == null){
			throw new ServiceException(CodeMescContants.CodeContants.NEWS_NULL, CodeMescContants.MessageContants.NEWS_NULL);
		}
		
		this.delete(news);
		
	}

	@Override
	public void edit(Map<String,Object> editNews, LoginUser loginUser) throws Exception {
		//查找数据库中是否存在该新闻
		News news = this.findByID(News.class, (String) editNews.get("id"));
		if(news == null){
			throw new ServiceException(CodeMescContants.CodeContants.NEWS_NULL, CodeMescContants.MessageContants.NEWS_NULL);
		}
		
		//当前时间
		Date now = Calendar.getInstance().getTime();
		//图片存放的目录
		String picPath = FileContants.NEWS_FILE + File.separator + news.getName();
		//保存图片
		String picture = CommonMethodUtil.saveFiles((List<String>) editNews.get("picPathList"), picPath);
		
		if(editNews.containsKey("content")){
			news.setContent((String)editNews.get("content"));
		}
		if(editNews.containsKey("name")){
			news.setName((String)editNews.get("name"));
		}
		if(editNews.containsKey("newsType")){
			news.setNewsType(CommonMethodUtil.getIntegerByObject(editNews.get("newsType")));
		}
		
		news.setUpdateTime(new Timestamp(now.getTime()));
		news.setUpdateUser(loginUser.getUserId());
		news.setPicture(picture);
		
		this.update(news);
	}
	
}
