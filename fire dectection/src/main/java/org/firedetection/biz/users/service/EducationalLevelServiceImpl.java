package org.firedetection.biz.users.service;

import java.util.List;

import javax.annotation.Resource;

import org.firedetection.biz.users.dao.EducationalLevelDAO;
import org.firedetection.biz.users.vo.EducationalLevelVO;
import org.springframework.stereotype.Service;

@Service("EducationalLevelService")
public class EducationalLevelServiceImpl implements EducationalLevelService{
	
	@Resource(name="EducationalLevelDAO")
	EducationalLevelDAO dao;

	@Override
	public List<EducationalLevelVO> getUserEduLvl(String id) {
		return dao.getUserEduLvl(id);
	}

	@Override
	public int insertEduLvl(EducationalLevelVO vo) {
		return dao.insertEduLvl(vo);
	}

	@Override
	public int modifyEduLvl(EducationalLevelVO vo) {
		return dao.modifyEduLvl(vo);
	}

	@Override
	public int deleteEduLvl(int eduLevel_num) {
		return dao.deleteEduLvl(eduLevel_num);
	}

	@Override
	public EducationalLevelVO getEduLvl(int eduLevel_num) {
		return dao.getEduLvl(eduLevel_num);
	}
}
