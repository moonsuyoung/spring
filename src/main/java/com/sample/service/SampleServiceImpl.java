package com.sample.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.common.util.FileUtils;
import com.sample.dao.SampleDao;

@Service("sampleService")
public class SampleServiceImpl implements SampleService{
	Logger log = Logger.getLogger(this.getClass());
	
	@Resource(name="fileUtils")
	private FileUtils fileUtils;
	
	@Resource(name="sampleDao")
	private SampleDao sampleDao;

	@Override
	public Map<String, Object> selectBoardList(Map<String, Object> map) throws Exception {
		return sampleDao.selectBoardList(map);
	}

	@Override
	public void insertBoard(Map<String, Object> map,HttpServletRequest request) throws Exception {
				
		sampleDao.insertBoard(map);
		
		List<Map<String,Object>> list = fileUtils.parseInsertFileInfo(map, request);
		for(int i=0 ,size = list.size(); i<size; i++) {
			sampleDao.insertFile(list.get(i));
		}
	}

	@Override
	public Map<String, Object> boardView(Map<String, Object> map) throws Exception {
		sampleDao.updateHitCnt(map);
		
		Map<String,Object> resultMap = new HashMap<String,Object>();
		Map<String,Object> tempMap = sampleDao.boardView(map);
		resultMap.put("data",tempMap);
		
		List<Map<String,Object>> fileList = sampleDao.selectFileList(map);
		resultMap.put("fileList",fileList);
		
		return resultMap;
	}

	@Override
	public void updateBoard(Map<String, Object> map, HttpServletRequest request) throws Exception {
		sampleDao.updateBoard(map);
		
		List<Map<String,Object>> list = fileUtils.parseInsertFileInfo(map, request);
		for(int i=0 ,size = list.size(); i<size; i++) {
			sampleDao.insertFile(list.get(i));
		}
	}

	@Override
	public void deleteBoard(Map<String, Object> map) throws Exception {
		sampleDao.deleteBoard(map);
		
	}
	
}
