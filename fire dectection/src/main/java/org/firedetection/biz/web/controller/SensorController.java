package org.firedetection.biz.web.controller;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.firedetection.biz.board.service.FileService;
import org.firedetection.biz.board.vo.FileListVO;
import org.firedetection.biz.board.vo.FileNameVo;
import org.firedetection.biz.raspberry.service.RaspberryService;
import org.firedetection.biz.raspberry.vo.PiaddVo;
import org.firedetection.biz.raspberry.vo.SensorVo;
import org.firedetection.biz.users.vo.EducationalLevelVO;
import org.firedetection.biz.users.vo.UserVO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class SensorController {
	@Resource(name = "RaspberryService")
	RaspberryService rasService;

	@Resource(name = "FileService")
	FileService file_service;
	
	@RequestMapping(value = "/setposition.do")
	public ModelAndView viewsetpos(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		List<String> list = rasService.selectS_uuid("test");
		List<FileNameVo> flist = rasService.selectFileNames("test");
		HashMap<String, String> map = new HashMap<String,String>();
		for (FileNameVo vo : flist) {
			map.put(vo.getHash()+"/"+vo.getOld_name(),vo.getNew_name());
		}
		mav.setViewName("/index.jsp?content=detection/dedectionposset");
		mav.addObject("selectlist",list);
		mav.addObject("filelist",map);
		
		/*
		 * request.setAttribute("prevfb_num", prevfb_num);
		 * request.setAttribute("nextfb_num", nextfb_num);
		 * request.setAttribute("thisPage", thisPage);
		 */
		return mav;
	}
	
	@RequestMapping(value = "/mymap.do")
	public ModelAndView mymap(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		List<FileNameVo> flist = rasService.selectFileNames("test");
		HashMap<String, String> map = new HashMap<String,String>();
		for (FileNameVo vo : flist) {
			map.put(vo.getHash()+"/"+vo.getOld_name(),vo.getNew_name());
		}
		mav.setViewName("/index.jsp?content=detection/mymap");
		mav.addObject("filelist",map);
		return mav;
	}
	
	@RequestMapping(value = "/viewmap.do")
	public ModelAndView fileuploadFrom(HttpSession session) {
		ModelAndView mav = new ModelAndView();
		UserVO user = (UserVO)session.getAttribute("login");
		if(user!=null) {
			mav.addObject("id", user.getId());
		} else {
			mav.setViewName("redirect:loginForm.do");
			return mav;
		}
		FileListVO fvo = new FileListVO();
		fvo.setUser_id(user.getId());
		mav.addObject("filelist",file_service.selectuserfiles(fvo));
		mav.setViewName("/index.jsp?content=detection/fileupload");
		return mav;
	}
	
	@RequestMapping(value = "/deleteMap.do")
	public ModelAndView deleteMap(String hash) {
		ModelAndView mav = new ModelAndView();
		FileListVO fvo = new FileListVO();
		file_service.filedeelte(hash);
		mav.addObject("filelist",file_service.selectuserfiles(fvo));
		mav.setViewName("redirect:/viewmap.do");
		return mav;
	}
	
	
	@RequestMapping(value = "/fireMYhistory.do")
	public ModelAndView firehistory(HttpSession session) {
		ModelAndView mav = new ModelAndView();
		UserVO user = (UserVO)session.getAttribute("login");
		if(user!=null) {
			
		} else {
			mav.setViewName("redirect:loginForm.do");
			return mav;
		}
		mav.addObject("firehisotry",rasService.selectmyfirelog(user.getId()));
		mav.setViewName("/index.jsp?content=detection/myhistory");
		return mav;
	}
	
	
	@RequestMapping(value = "/firemonitor.do")
	public ModelAndView firemonitor(String hash, String val, String old_name) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/index.jsp?content=detection/firenotice");
		return mav;
	}
	
	@RequestMapping(value = "/monitor.do")
	public ModelAndView firemonitor(HttpSession session) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/index.jsp?content=detection/monitor");
		return mav;
	}
	
	@RequestMapping(value = "/updatename.do")
	public ModelAndView updateName(String hash, String val, String old_name) {
		ModelAndView mav = new ModelAndView();
		FileListVO vo =new FileListVO();
		vo.setHash(hash);
		vo.setNew_name(val);
		vo.setOld_name(old_name);
		System.out.println(vo);
		file_service.updatefilesname(vo);
		mav.setViewName("redirect:/viewmap.do");
		return mav;
	}
	
	@RequestMapping(value = "/mobile.do")
	public ModelAndView movileView() {
		ModelAndView mav = new ModelAndView();
		FileListVO vo =new FileListVO();
		mav.setViewName("detection/mobilenotice");
		return mav;
	}
	
}
