package org.firedetection.biz.board.service;

import java.util.List;

import org.firedetection.biz.board.vo.FileGroupVO;
import org.firedetection.biz.board.vo.FileListVO;
import org.firedetection.biz.board.vo.FileNameVo;
import org.firedetection.biz.board.vo.FilesVO;

public interface FileService {
	public int findFileGroup(String flag);
	public int insertFiles(FilesVO vo);
	public int update_CB_FileGroup(FileGroupVO vo);
	public int update_FB_FileGroup(FileGroupVO vo);
	public int update_TB_FileGroup(FileGroupVO vo);
	public int insertBoard(String flag);
	public int delete_CB_FileGroup(int cb_num);
	public int delete_FB_FileGroup(int fb_num);
	public int delete_TB_FileGroup(int tb_num);
	public int deleteFileGroup(int fileGroupNum);
	public int findFile(FilesVO vo);
	public int deleteFile(int fileGroupNum);
	public int cancel_insertBoard(String flag);
	public int insertfilesname(FileListVO vo);
	public int updatefilesname(FileListVO vo);
	public List<FileNameVo> selectuserfiles(FileListVO vo);
	public int filedeelte(String hash);
}
