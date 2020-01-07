package org.firedetection.biz.board.dao;

import java.util.HashMap;
import java.util.List;

import org.firedetection.biz.board.vo.QnaBoardVO;

public interface QnaBoardDAO {
	public List<QnaBoardVO> boardList(HashMap<String, Integer> map);
	public List<QnaBoardVO> scBoardList(HashMap<String, Object> map);
	public QnaBoardVO viewBoard(int fb_num);
	public int countBoards();
	public int scCountBoards(String condition, String search);
	public int findBoard(QnaBoardVO vo);
	public int insertQnaBoard(QnaBoardVO vo);
	public int modifyQnaBoard(QnaBoardVO vo);
	public int deleteQnaBoard(int fb_num);
	public int countUp(int fb_num);
	public int prevfb_num(int fb_num);
	public int nextfb_num(int fb_num);
	public int scPrevfb_num(String condition, String search, int fb_num);
	public int scNextfb_num(String condition, String search, int fb_num);
}
