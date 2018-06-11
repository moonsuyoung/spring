package com.common.controller;

import java.io.File;
import java.net.URLEncoder;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.common.common.CommandMap;
import com.common.service.CommonService;

@Controller
public class CommonController {
	Logger log = Logger.getLogger(this.getClass());
	
	private static final String filePath = "E:\\git\\study\\study\\hello\\src\\main\\webapp\\upload\\";
	
	@Resource(name="commonService")
	private CommonService commonService;
	
	@RequestMapping(value="/common/downloadFile.do")
	public void downLoadFile(CommandMap commandMap, HttpServletResponse response) throws Exception {
		Map<String,Object> map = commonService.selectFileInfo(commandMap.getMap());
		String stored__file_name = (String) map.get("STORED_FILE_NAME");	
		String original__file_name = (String) map.get("ORIGINAL_FILE_NAME");
		
		byte fileByte[] = FileUtils.readFileToByteArray(new File(filePath+stored__file_name));
		
		response.setContentType("application/octet-stream");
		response.setContentLength(fileByte.length);
		response.setHeader("Content-Disposition","attachment; fileName="+URLEncoder.encode(original__file_name,"UTF-8")+";");
		//response.setHeader("Content-Disposition","attachment; fileName=test.jpg;");
		response.setHeader("Content-Transfer-Encoding","binary");
		response.getOutputStream().write(fileByte);
		
		response.getOutputStream().flush();
		response.getOutputStream().close();	
	}
	
	@RequestMapping(value="/common/fileDelete.do")
	@ResponseBody
	public String fileDelete(CommandMap commandMap) throws Exception {
		commonService.fileDelete(commandMap.getMap());
		
		return "success";
	}
}
		