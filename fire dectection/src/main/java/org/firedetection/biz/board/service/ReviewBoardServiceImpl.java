package org.firedetection.biz.board.service;

import javax.annotation.Resource;

import org.firedetection.biz.board.dao.ReviewBoardDAO;
import org.springframework.stereotype.Service;

@Service("ReviewBoardService")
public class ReviewBoardServiceImpl implements ReviewBoardService{
	@Resource(name="ReviewBoardDAO")
	ReviewBoardDAO dao;
}
