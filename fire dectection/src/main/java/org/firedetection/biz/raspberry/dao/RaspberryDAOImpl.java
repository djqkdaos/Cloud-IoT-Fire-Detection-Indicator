package org.firedetection.biz.raspberry.dao;

import java.util.HashMap;
import java.util.List;

import org.firedetection.biz.board.vo.FileNameVo;
import org.firedetection.biz.board.vo.Greeting;
import org.firedetection.biz.board.vo.ReplyVO;
import org.firedetection.biz.raspberry.vo.DetectionListVo;
import org.firedetection.biz.raspberry.vo.FireDataVo;
import org.firedetection.biz.raspberry.vo.FireLogDataVo;
import org.firedetection.biz.raspberry.vo.FireLogVo;
import org.firedetection.biz.raspberry.vo.MonitorVo;
import org.firedetection.biz.raspberry.vo.RaspbVo;
import org.firedetection.biz.raspberry.vo.SensorVo;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("RaspberryDAO")
public class RaspberryDAOImpl implements RaspberryDAO{
	@Autowired
	SqlSessionTemplate mybatis;

	@Override
	public int insertFireData(FireLogVo vo) {
		return mybatis.insert("RaspberryMapper.insertFireData",vo);
	}

	@Override
	public int insertRaspberry(RaspbVo vo) {
		return mybatis.insert("RaspberryMapper.insertRaspberry",vo);
	}
	
	@Override
	public int insertSensor(SensorVo vo) {
		return mybatis.insert("RaspberryMapper.insertRaspberrySensor",vo);
	}

	@Override
	public List<String> selectS_uuid(String id) {
		return mybatis.selectList("RaspberryMapper.selectS_uuid",id);
	}

	@Override
	public List<FileNameVo> selectFileNames(String id) {
		return mybatis.selectList("RaspberryMapper.selectFileNames",id);
	}

	@Override
	public int updateSensorPosition(SensorVo vo) {
		return mybatis.update("RaspberryMapper.updateSensorPosition",vo);
	}

	@Override
	public int selectSensorSubnum(SensorVo vo) {
		// TODO Auto-generated method stub
		return mybatis.selectOne("RaspberryMapper.selectSensorSubnum",vo);
	}

	@Override
	public int insertRaspberrySensorSubnum(SensorVo vo) {
		// TODO Auto-generated method stub
		return mybatis.insert("RaspberryMapper.insertRaspberrySensorSubnum", vo);
	}

	@Override
	public SensorVo selectSensor(String s_uuid) {
		return mybatis.selectOne("RaspberryMapper.selectSensor", s_uuid);
	}

	@Override
	public List<SensorVo> selectFloor(String floor) {
		return mybatis.selectList("RaspberryMapper.selectFloor", floor);
	}

	@Override
	public List<FireLogDataVo> selectfirelog() {
		return mybatis.selectList("RaspberryMapper.selectfirelog");
	}

	@Override
	public List<FireLogDataVo> selectmyfirelog(String user_id) {
		// TODO Auto-generated method stub
		return mybatis.selectList("RaspberryMapper.selectmyfirelog", user_id);
	}

	@Override
	public int insertfirelog(FireLogDataVo vo) {
		// TODO Auto-generated method stub
		return mybatis.insert("RaspberryMapper.insertfirelog",vo);
	}

	@Override
	public List<FireLogDataVo> selectnewfirelog() {
		return mybatis.selectList("RaspberryMapper.selectnewfirelog");
	}

	@Override
	public Greeting selectnoticeFire(Greeting gr) {
		return mybatis.selectOne("RaspberryMapper.selectnoticeFire", gr);
	}

	@Override
	public FireLogDataVo selectfirelogOne() {
		return mybatis.selectOne("RaspberryMapper.selectfirelogOne");
	}

	@Override
	public SensorVo selectDetectionSuuid(String s_uuid) {
		// TODO Auto-generated method stub
		return mybatis.selectOne("RaspberryMapper.selectDetectionSuuid", s_uuid);
	}

	@Override
	public int selectfirelogDate(FireLogDataVo vo) {
		// TODO Auto-generated method stub
		return mybatis.selectOne("RaspberryMapper.selectfirelogDate", vo);
	}

	@Override
	public List<MonitorVo> selectMonitorMonthVolt() {
		// TODO Auto-generated method stub
		return mybatis.selectList("RaspberryMapper.selectMonitorMonthVolt");
	}

	@Override
	public List<MonitorVo> selectMonitorNowVolt() {
		return mybatis.selectList("RaspberryMapper.selectMonitorNowVolt");
	}

	
}
