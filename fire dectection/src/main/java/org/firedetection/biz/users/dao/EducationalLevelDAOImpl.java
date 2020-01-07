package org.firedetection.biz.users.dao;

import java.util.List;

import org.firedetection.biz.users.vo.EducationalLevelVO;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("EducationalLevelDAO")
public class EducationalLevelDAOImpl implements EducationalLevelDAO{
	@Autowired
	SqlSessionTemplate mybatis;

	@Override
	public List<EducationalLevelVO> getUserEduLvl(String id) {
		return mybatis.selectList("EducationalLevelMapper.getUserEduLvl", id);
	}

	@Override
	public int insertEduLvl(EducationalLevelVO vo) {
		return mybatis.insert("EducationalLevelMapper.insertEduLvl", vo);
	}

	@Override
	public int modifyEduLvl(EducationalLevelVO vo) {
		return mybatis.update("EducationalLevelMapper.modifyEduLvl", vo);
	}

	@Override
	public int deleteEduLvl(int eduLevel_num) {
		return mybatis.delete("EducationalLevelMapper.deleteEduLvl", eduLevel_num);
	}

	@Override
	public EducationalLevelVO getEduLvl(int eduLevel_num) {
		return mybatis.selectOne("EducationalLevelMapper.getEduLvl", eduLevel_num);
	}
}
