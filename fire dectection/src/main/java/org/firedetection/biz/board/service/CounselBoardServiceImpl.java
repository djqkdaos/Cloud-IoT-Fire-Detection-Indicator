package org.firedetection.biz.board.service;

import javax.annotation.Resource;

import org.firedetection.biz.board.dao.CounselBoardDAO;
import org.springframework.stereotype.Service;

@Service("CounselBoardService")
public class CounselBoardServiceImpl implements CounselBoardService{
	@Resource(name="CounselBoardDAO")
	CounselBoardDAO dao;
}
