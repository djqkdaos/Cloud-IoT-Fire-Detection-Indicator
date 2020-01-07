package org.firedetection.biz.users.dao;

import java.util.List;

import org.firedetection.biz.users.vo.CareerVO;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("CareerDAO")
public class CareerDAOImpl implements CareerDAO{
	@Autowired
	SqlSessionTemplate mybatis;

	@Override
	public List<CareerVO> getUserCareer(String id) {
		return mybatis.selectList("ProfileMapper.getUserCareer",id);
	}

	@Override
	public int insertCareer(CareerVO vo) {
		int row = mybatis.insert("ProfileMapper.insertCareer", vo);
		return row;
	}

	@Override
	public int modifyCareer(CareerVO vo) {
		int row = mybatis.update("ProfileMapper.modifyCareer", vo);
		return row;
	}

	@Override
	public int deleteCareer(int num) {
		int row = mybatis.delete("ProfileMapper.deleteCareer", num);
		return row;
	}

	@Override
	public CareerVO getCareer(int num) {
		return mybatis.selectOne("ProfileMapper.getCareer", num);
	}
}
