package com.common.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.logging.Log;
import org.apache.ibatis.logging.LogFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;


public class AbstractDao {
	protected Log log = LogFactory.getLog(AbstractDao.class);
	
	@Autowired
	private SqlSessionTemplate sqlSession;
	
	protected void printQueryId(String queryId) {
		if(log.isDebugEnabled()) {
			log.debug("\t QueryId \t: "+queryId);
		}
	}
	public Object insert(String queryId, Object params) {
		printQueryId(queryId);
		return sqlSession.insert(queryId,params);
	}
	
	public Object update(String queryId, Object params) {
		printQueryId(queryId);
		return sqlSession.update(queryId,params);
	}
	
	public Object delete(String queryId, Object params) {
		printQueryId(queryId);
		return sqlSession.delete(queryId,params);
	}
	
	public Object selectOne(String queryId) {
		printQueryId(queryId);
		return sqlSession.delete(queryId);
	}
	
	public Object selectOne(String queryId, Object params) {
		printQueryId(queryId);
		return sqlSession.selectOne(queryId,params);
	}
	
	@SuppressWarnings("rawtypes")
	public List selectList(String queryId) {
		printQueryId(queryId);
		return sqlSession.selectList(queryId);
	}
	
	
	@SuppressWarnings("rawtypes")
	public List selectList(String queryId, Object params) {
		printQueryId(queryId);
		return sqlSession.selectList(queryId,params);
	}
	
	@SuppressWarnings({"rawtypes","unchecked"})
	public Map selectPagingList(String queryId, Object params) {
		printQueryId(queryId);
		
		Map<String,Object> map = (Map<String,Object>) params;
		PaginationInfo paginationInfo = null;
		
		if(map.containsKey("currentPageNo") == false || StringUtils.isEmpty(map.get("currentPageNo").toString()) == true) {
	        map.put("currentPageNo","1");
		}
	     
	    paginationInfo = new PaginationInfo();
	    paginationInfo.setCurrentPageNo(Integer.parseInt(map.get("currentPageNo").toString()));
		
		if(map.containsKey("currentPageNo") == false || StringUtils.isEmpty(map.get("currentPageNo").toString()) == true) {
			paginationInfo.setRecordCountPerPage(15);
		} else {
			//paginationInfo.setRecordCountPerPage(Integer.parseInt(map.get("PAGE_NOW").toString()));
			paginationInfo.setRecordCountPerPage(15);
		}
		
		paginationInfo.setPageSize(10);
		
		int start = paginationInfo.getFirstRecordIndex();
		int end = paginationInfo.getRecordCountPerPage();
		map.put("START",start);
		map.put("END",end*paginationInfo.getCurrentPageNo());
		
		params = map;
		
		Map<String,Object> returnMap = new HashMap<String,Object>();
		List<Map<String,Object>> list = sqlSession.selectList(queryId,params);
		
		if(list.size() == 0) {
			map = new HashMap<String, Object>();
			map.put("TOTAL_COUNT",0);
			list.add(map);
			
			if(paginationInfo != null) {
				paginationInfo.setTotalRecordCount(0);
				
				returnMap.put("paginationInfo",paginationInfo);
			}
		} else {
			if(paginationInfo != null) {
				paginationInfo.setTotalRecordCount(Integer.parseInt(list.get(0).get("TOTAL_COUNT").toString()));
				
				returnMap.put("paginationInfo",paginationInfo);
			}
		}
		
		
		returnMap.put("result",list);
		return returnMap;
	}
	
	
}
