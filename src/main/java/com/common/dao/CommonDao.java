package com.common.dao;

import java.util.Map;

import org.springframework.stereotype.Repository;

@Repository("commonDao")
public class CommonDao extends AbstractDao{
	
	public Map<String,Object> selectFileInfo(Map<String,Object> map) throws Exception {
		return (Map<String,Object>)selectOne("common.selectFileInfo",map);
	}
	
	public void fileDelete(Map<String,Object> map) throws Exception {
		delete("common.fileDelete",map);
	}

}
