package com.common.service;

import java.util.Map;

public interface CommonService {
	
	public Map<String,Object> selectFileInfo(Map<String,Object> map) throws Exception;
	
	public void fileDelete(Map<String,Object> map) throws Exception;

}
