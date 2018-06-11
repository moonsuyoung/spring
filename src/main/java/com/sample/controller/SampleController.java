package com.sample.controller;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.common.common.CommandMap;
import com.sample.service.SampleService;

import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

@Controller
public class SampleController {
	Logger log = Logger.getLogger(this.getClass());
	
	@Resource(name="sampleService")
	private SampleService sampleService;
	
	/*
	@RequestMapping(value="/sample/openSampleList.do")
	public ModelAndView openSampleList(Map<String,Object> commandMap) throws Exception {
		ModelAndView mv = new ModelAndView("/sample/boardList");
		
		List<Map<String,Object>> list = sampleService.selectBoardList(commandMap);
		mv.addObject("list",list);
		
		return mv;
	}
	*/
	
	@RequestMapping(value="/sample/testMapArgumentResolver.do")
	public ModelAndView testMapArgumentResolver(CommandMap commandMap) throws Exception {
		ModelAndView mv = new ModelAndView("");
		
		if(commandMap.isEmpty() == false){
	        Iterator<Entry<String,Object>> iterator = commandMap.getMap().entrySet().iterator();
	        Entry<String,Object> entry = null;
	        while(iterator.hasNext()){
	        	
	            entry = iterator.next();
	            log.debug("key : "+entry.getKey()+", value : "+entry.getValue());
	        }
	    }
		
		return mv;
	}
	
	@RequestMapping(value="/sample/openBoardList.do")
	public ModelAndView openBoadList(CommandMap commandMap) throws Exception {
		ModelAndView mv = new ModelAndView("/sample/boardList");
		
		Map<String,Object> resultMap = sampleService.selectBoardList(commandMap.getMap());
		
		mv.addObject("paginationInfo",(PaginationInfo)resultMap.get("paginationInfo"));
		
		mv.addObject("list",resultMap.get("result"));
		
		return mv;
	}
	
	@RequestMapping(value="/sample/openBoardWrite.do")
	public ModelAndView openBoardWrite(CommandMap commandMap) throws Exception {
		ModelAndView mv = new ModelAndView("/sample/boardWrite");
		
		if(commandMap.get("idx") != null) {		
			Map<String,Object> map = sampleService.boardView(commandMap.getMap());
			
			mv.addObject("data",map.get("data"));
			mv.addObject("fileList",map.get("fileList"));
		}

		return mv;
		
	}
	
	@RequestMapping(value="/sample/insertBoard.do")
	public ModelAndView insertBoard(CommandMap commandMap, HttpServletRequest request) throws Exception {
		ModelAndView mv = new ModelAndView("redirect:/sample/openBoardList.do");
		
		sampleService.insertBoard(commandMap.getMap(),request);
		
		return mv;
	}
	
	@RequestMapping(value="/sample/openBoardView.do")
	public ModelAndView openBoardView(CommandMap commandMap) throws Exception {
		ModelAndView mv = new ModelAndView("redirect:/sample/openBoardWrite.do?idx="+commandMap.get("idx"));
		
		return mv;
	}
	
	@RequestMapping(value="/sample/updateBoard.do")
	public ModelAndView updateBoard(CommandMap commandMap, HttpServletRequest request) throws Exception {
		ModelAndView mv = new ModelAndView("redirect:/sample/openBoardWrite.do?idx="+commandMap.get("idx"));
		
		sampleService.updateBoard(commandMap.getMap(),request);
		
		return mv;
	}
	
	@RequestMapping(value="/sample/deleteBoard.do")
	public ModelAndView deleteBoard(CommandMap commandMap) throws Exception {
		ModelAndView mv = new ModelAndView("redirect:/sample/openBoardList.do");
		
		sampleService.deleteBoard(commandMap.getMap());
		
		return mv;
	}
	
	@RequestMapping(value="/sample/requestTest.do")
	public ModelAndView requestTest(CommandMap commandMap) throws Exception {
		ModelAndView mv = new ModelAndView("/sample/requestTest");
		log.debug("==="+commandMap.get("test"));
		log.debug("==="+commandMap.get("test1"));
		return mv;
	}
	
}
