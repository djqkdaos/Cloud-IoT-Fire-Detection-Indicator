package org.firedetection.biz.board.dao;

import java.util.HashMap;
import java.util.List;

import org.firedetection.biz.board.vo.QnaBoardVO;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("QnaBoardDAO")
public class QnaBoardDAOImpl implements QnaBoardDAO{
	@Autowired
	SqlSessionTemplate mybatis;

	@Override
	public int insertQnaBoard(QnaBoardVO vo) {
		return mybatis.insert("QnaBoardMapper.insertQnaBoard", vo);
	}

	@Override
	public int modifyQnaBoard(QnaBoardVO vo) {
		return mybatis.update("QnaBoardMapper.modifyQnaBoard", vo);
	}

	@Override
	public int deleteQnaBoard(int fb_num) {
		return mybatis.delete("QnaBoardMapper.deleteQnaBoard", fb_num);
	}

	@Override
	public int findBoard(QnaBoardVO vo) {
		return mybatis.selectOne("QnaBoardMapper.findBoard", vo);
	}

	@Override
	public List<QnaBoardVO> boardList(HashMap<String, Integer> map) {
		return mybatis.selectList("QnaBoardMapper.boardList", map);
	}

	@Override
	public int countBoards() {
		return mybatis.selectOne("QnaBoardMapper.countBoards");
	}

	@Override
	public QnaBoardVO viewBoard(int fb_num) {
		return mybatis.selectOne("QnaBoardMapper.viewBoard", fb_num);
	}

	@Override
	public int countUp(int fb_num) {
		return mybatis.update("QnaBoardMapper.countUp", fb_num);
	}

	@Override
	public int prevfb_num(int fb_num) {
		return mybatis.selectOne("QnaBoardMapper.prevfb_num", fb_num);
	}

	@Override
	public int nextfb_num(int fb_num) {
		return mybatis.selectOne("QnaBoardMapper.nextfb_num", fb_num);
	}

	@Override
	public List<QnaBoardVO> scBoardList(HashMap<String, Object> map) {
		return mybatis.selectList("QnaBoardMapper.scBoardList", map);
	}

	@Override
	public int scCountBoards(String condition, String search) {
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("condition", condition);
		map.put("search",search);
		return mybatis.selectOne("QnaBoardMapper.scCountBoards", map);
	}

	@Override
	public int scPrevfb_num(String condition, String search, int fb_num) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("condition", condition);
		map.put("search",search);
		map.put("fb_num",fb_num);
		return mybatis.selectOne("QnaBoardMapper.scPrevfb_num", map);
	}

	@Override
	public int scNextfb_num(String condition, String search, int fb_num) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("condition", condition);
		map.put("search",search);
		map.put("fb_num",fb_num);
		return mybatis.selectOne("QnaBoardMapper.scNextfb_num", map);
	}
}
