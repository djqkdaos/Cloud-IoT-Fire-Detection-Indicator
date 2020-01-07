package org.firedetection.biz.board.service;

import java.util.HashMap;

import org.firedetection.biz.board.vo.QnaBoardVO;

public interface QnaBoardService {
	public HashMap<String, Object> boardList(int page_num);
	public HashMap<String, Object> scBoardList(String condition, String search, int page_num);
	public HashMap<String, Object> viewBoard(int fb_num);
	public HashMap<String, Object> scViewBoard(String condition, String search, int fb_num);
	public int countBoards();
	public int findBoard(QnaBoardVO vo);
	public int insertQnaBoard(QnaBoardVO vo);
	public int modifyQnaBoard(QnaBoardVO vo);
	public int deleteQnaBoard(int fb_num);
}
