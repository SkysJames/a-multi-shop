package com.sky.business.common.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 分页类
 * @author Sky James
 *
 */
public class Pager implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private static final Log log = LogFactory.getLog(Pager.class);
	
	public static final Integer MAX_PAGE_SIZE = 500;//每一页的最大数据量
	public static final Integer DEFAULT_PAGE_SIZE = 30;//每一页的默认数据量
	public static final Integer DEFAULT_CURRENT_PAGE = 1;//当前的默认页码

	/**
	 * 当前页码
	 */
	private Integer currentPage = DEFAULT_CURRENT_PAGE;
	/**
	 * 每一页的数据量
	 */
	private Integer pageSize = DEFAULT_PAGE_SIZE;
	/**
	 * 总共的数据量
	 */
	private Integer totalCount = 0;
	/**
	 * 总共的页码数
	 */
	private Integer pageCount = 0;
	/**
	 * 起始行数（不包括该行）
	 */
	private Integer startIndex = -1;
	/**
	 * 结束行数（包括该行）
	 */
	private Integer lastIndex = 0;
	/**
	 * 结果集列表
	 */
	private List resultList = new ArrayList();
	
	public Pager() {
	}
	
	public Pager(int currentPage, int pageSize, int totalCount) {
		this.setCurrentPage(currentPage);
		this.setPageSize(pageSize);
		this.setTotalCount(totalCount);
		this.calculate();
	}
	
	public Pager(int currentPage, int pageSize, int totalCount, List resultList) {
		this(currentPage, pageSize, totalCount);
		this.setResultList(resultList);
	}

	/**
	 * 根据已有的集合和分页类，将该集合进行分页的返回
	 * @param list
	 * @param page
	 * @return
	 */
	public static <T> List<T> splitPageWithAllRecords(List<T> list, Pager page) {
		page.setTotalCount(list.size());
		Integer end = (Integer)page.getCurrentPage()* page.getPageSize();
		Integer begin = end - page.getPageSize() < 0 ? 0 : end - page.getPageSize() ;
		list = list.subList(begin, end > list.size() ? list.size() : end);
		page.setResultList(list);
		return list;
	}
	
	/**
	 * 计算页数、记录起始、结束位置<br>
	 * 调用calculate()之前，必须设置totalCount、pageSize、currentPage属性
	 */
	private void calculate() {
		// 计算总计有多少页
		int pageCount = 0;
		int lastIndex = 0;
		int startIndex = 0;

		if (totalCount % this.pageSize == 0) {
			pageCount = totalCount / this.pageSize;
		} else {
			pageCount = (totalCount / this.pageSize) + 1;
		}

		// 如果已经设置了startIndex，那么不再通过currentPage计算startIndex
		if (this.startIndex > -1) {
			startIndex = this.startIndex;
			lastIndex = startIndex + this.pageSize;

		} else {
			// 计算记录起始位置是多少
			startIndex = (currentPage - 1) * this.pageSize;

			// 计算记录结束位置是多少
			if (totalCount < this.pageSize) {
				lastIndex = totalCount;
			} else if ((totalCount % this.pageSize == 0)
					|| (totalCount % this.pageSize != 0 && currentPage < pageCount)) {
				lastIndex = currentPage * this.pageSize;
			} else {
				lastIndex = totalCount;
			}
		}

		this.setPageCount(pageCount);
		this.setStartIndex(startIndex);
		this.setLastIndex(lastIndex);
	}
	
	
	public Integer getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(Integer currentPage) {
		if (currentPage < 1) {
			currentPage = 1;
		}
		this.currentPage = currentPage;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		if (pageSize < 1) {
			pageSize = 1;
		} else if(pageSize > MAX_PAGE_SIZE) {
			pageSize = MAX_PAGE_SIZE;
		}
		this.pageSize = pageSize;
	}
	
	public Integer getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}

	public Integer getPageCount() {
		pageCount = totalCount / pageSize;
		if (totalCount % pageSize > 0) {
			pageCount ++;
		}
		return pageCount;
	}

	public void setPageCount(Integer pageCount) {
		this.pageCount = pageCount;
	}

	public List getResultList() {
		return resultList;
	}

	public void setResultList(List resultList) {
		this.resultList = resultList;
	}

	public Integer getStartIndex() {
		return startIndex;
	}

	public void setStartIndex(Integer startIndex) {
		this.startIndex = startIndex;
	}

	public Integer getLastIndex() {
		return lastIndex;
	}

	public void setLastIndex(Integer lastIndex) {
		this.lastIndex = lastIndex;
	}
	
//	public static void main(String[] args){
//		Pager p = new Pager();
//		System.out.println(p.getOrderType().equals(Pager.OrderType.desc));
//	}

}