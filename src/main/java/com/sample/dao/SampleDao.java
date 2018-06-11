package com.sample.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.reflection.invoker.GetFieldInvoker;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.common.dao.AbstractDao;

@Repository("sampleDao")
public class SampleDao extends AbstractDao{
	
	Logger log = Logger.getLogger(this.getClass());
	
	public Map<String, Object> selectBoardList(Map<String, Object> map) throws Exception {
		return (Map<String, Object>)selectPagingList("sample.selectBoardList",map);
	}
	
	public void insertBoard(Map<String, Object> map) throws Exception {
		insert("sample.insertBoard",map);
	}
	
	public Map<String,Object> boardView(Map<String,Object> map) throws Exception {
		return (Map<String, Object>) selectOne("sample.boardView", map);
	}
	
	public void updateHitCnt(Map<String,Object> map) throws Exception {
		update("sample.updateHitCnt",map);
	}
	
	public void updateBoard(Map<String,Object> map) throws Exception {
		update("sample.updateBoard",map);
	}
	
	public void deleteBoard(Map<String,Object> map) throws Exception {
		delete("sample.deleteBoard",map);
	}
	
	public void insertFile(Map<String,Object> map) throws Exception {
		insert("sample.insertFile",map);
	}
	
	public List<Map<String,Object>> selectFileList(Map<String,Object> map) throws Exception {
		return (List<Map<String, Object>>)selectList("sample.selectFileList",map);
	}

}
