package org.firedetection.biz.web.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.firedetection.biz.board.service.FileService;
import org.firedetection.biz.board.service.FreeBoardService;
import org.firedetection.biz.board.service.QnaBoardService;
import org.firedetection.biz.board.vo.FilesVO;
import org.firedetection.biz.board.vo.FreeBoardVO;
import org.firedetection.biz.board.vo.QnaBoardVO;
import org.firedetection.biz.board.vo.QnaBoardVO;
import org.firedetection.biz.users.vo.UserVO;
import org.firedetection.biz.web.common.SHA256;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 
 * @author 박현호
 * @version 1.0
 * @since 2018.05.14
 * @see 게시판 관련 메소드 Controller
 */
@Controller
public class QnaBoardController {
	@Resource(name = "FreeBoardService")
	FreeBoardService fb_service;
	@Resource(name = "FileService")
	FileService file_service;

	/**
	 * 검색된 게시글을 수정하기 위해 수정페이지로 이동 
	 * 이동 이전페이지, 다음페이지, 삭제, 수정등
	 * 다시 검색된 목록에 해당하는 페이지를 띄워주어야 하므로 다 따로 만들어짐
	 * 
	 * @param condition
	 *            검색하고자 하는 아이디나, 게시판 제목
	 * @param search
	 *            검색하고자 하는 것의 내용
	 * @param fb_num
	 *            현재 게시글 번호
	 * @param thisPage
	 *            수정 취소나 수정 시 다시 돌아갈 페이지 번호
	 * @param request
	 *            현제 수정하고자 하는 게시글 정보와, 수정 취소나 수정 시 다시 돌아갈 페이지 번호를 넣기 위해 사용
	 * @return modifySCQnaBoard.jsp
	 */
	@RequestMapping(value = "/modifySCQnaBoard.do", method = RequestMethod.GET)
	public String modifyQnaBoard(String condition, String search, int fb_num, int thisPage,
			HttpServletRequest request) {
		HashMap<String, Object> boardInfo = fb_service.viewBoard(fb_num);
		request.setAttribute("board", (QnaBoardVO) boardInfo.get("board"));
		request.setAttribute("thisPage", thisPage);
		request.setAttribute("condition", condition);
		request.setAttribute("search", search);
		return "index.jsp?content=board/modifySCQnaBoard";
	}

	/**
	 * 게시글을 수정하기 위해 수정페이지로 이동
	 * 
	 * @param fb_num
	 *            현재 게시글 번호
	 * @param thisPage
	 *            수정 취소나 수정 시 다시 돌아갈 페이지 번호
	 * @param request
	 *            현제 수정하고자 하는 게시글 정보와, 수정 취소나 수정 시 다시 돌아갈 페이지 번호를 넣기 위해 사용
	 * @return modifyQnaBoard.jsp
	 */
	@RequestMapping(value = "/modifyQnaBoard.do", method = RequestMethod.GET)
	public String modifyQnaBoard(int fb_num, int thisPage, HttpServletRequest request) {
		HashMap<String, Object> boardInfo = fb_service.viewBoard(fb_num);
		request.setAttribute("board", (QnaBoardVO) boardInfo.get("board"));
		request.setAttribute("thisPage", thisPage);
		return "index.jsp?content=board/modifyQnaBoard";
	}

	/**
	 * 게시글 수정 DB에 저장하기 Service단에서 transaction을 걸어 기존 파일을 지우고 저장했을 경우등 파일을 수정했을때의 모든
	 * 경우 수정
	 * 
	 * @param fb_num
	 *            게시판 번호
	 * @param title
	 *            게시판 제목
	 * @param id
	 *            글쓴이
	 * @param thisPage
	 *            게시판을 보고있던 사람이 게시글을 들어오기 전 리스트 번호
	 * @param editor
	 *            게시글 내용
	 * @return viewQnaBoard.jsp
	 */
	/**
	 * 원하는 게시글을 띄워주는 함수
	 * 
	 * @param fb_num
	 *            게시판 번호
	 * @param thisPage
	 *            페이징 처리된 현재 페이지 번호
	 * @param request
	 *            게시글의 정보(board), 이전 게시글의 번호(prevfb_num), 다음 게시글의 번호(nextfb_num), 현재
	 *            페이지 번호(thisPage)를 담아서 보내준다.
	 * @return viewQnaBoard.jsp
	 */
	@RequestMapping(value = "/viewQnaBoard.do")
	public String viewQnaBoard(int fb_num, int thisPage, HttpServletRequest request) {
		HashMap<String, Object> boardInfo = fb_service.viewBoard(fb_num);
		QnaBoardVO board = (QnaBoardVO) boardInfo.get("board");
		int prevfb_num = (int) boardInfo.get("prevfb_num");
		int nextfb_num = (int) boardInfo.get("nextfb_num");
		request.setAttribute("board", board);
		request.setAttribute("prevfb_num", prevfb_num);
		request.setAttribute("nextfb_num", nextfb_num);
		request.setAttribute("thisPage", thisPage);
		return "index.jsp?content=board/viewQnaBoard";
	}

	/**
	 * 게시판 리스트를 보여주는 함수
	 * 
	 * @param page_num
	 *            현재 페이징 처리된 페이지 번호
	 * @param request
	 *            해당 페이지에서의 띄워줄 게시글목록(list), 전체 페이징처리 개수가 몇개인지(page), 해당 페이지에서 마지막
	 *            게시글 번호(정확히는 게시글 수)(limit), 현재 페이지 목록 번호(thisPage)
	 * @return QnaBoard.jsp
	 */
	@RequestMapping(value = "/qnaBoard.do", method = RequestMethod.GET)
	public String QnaBoardDo(int page_num, HttpServletRequest request) {
		HashMap<String, Object> boardInfo = fb_service.boardList(page_num);
		List<FreeBoardVO> list = (List<FreeBoardVO>) boardInfo.get("list");
		int page = (int) boardInfo.get("count_page");
		int limit = (int) boardInfo.get("limit");
		int thisPage = (int) boardInfo.get("thisPage");
		request.setAttribute("list", list);
		request.setAttribute("page", page);
		request.setAttribute("limit", limit);
		request.setAttribute("thisPage", thisPage);
		return "index.jsp?content=board/qnaBoard";
	}

	/**
	 * @return 게시판 입력 페이지 이동 설정 insertQnaBoard.jsp
	 */
	@RequestMapping(value = "/insertQnaBoard.do", method = RequestMethod.GET)
	public String insertBoard() {
		return "index.jsp?content=board/insertQnaBoard";
	}

	/**
	 * 게시글을 입력하는 함수로, File처리까지 한다. service 단에서 transaction 처리되어 입력된다.
	 * 
	 * @param title
	 *            게시글 제목
	 * @param id
	 *            게시글 작성자
	 * @param editor
	 *            게시글 내용
	 * @return QnaBoard.do 게시글 목록으로 이동
	 */

	
}
