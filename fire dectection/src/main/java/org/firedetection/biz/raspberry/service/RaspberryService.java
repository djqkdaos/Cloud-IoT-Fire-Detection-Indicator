package org.firedetection.biz.raspberry.service;

import java.util.List;

import org.firedetection.biz.board.vo.FileNameVo;
import org.firedetection.biz.board.vo.Greeting;
import org.firedetection.biz.raspberry.vo.FireDataVo;
import org.firedetection.biz.raspberry.vo.FireLogDataVo;
import org.firedetection.biz.raspberry.vo.MonitorVo;
import org.firedetection.biz.raspberry.vo.PiaddVo;
import org.firedetection.biz.raspberry.vo.RaspbVo;
import org.firedetection.biz.raspberry.vo.SensorVo;

public interface RaspberryService {
	public int insertFireData(FireDataVo vo);
	public int insertRaspberry(RaspbVo vo, PiaddVo pvo);
	public List<String> selectS_uuid(String id);
	public List<FileNameVo> selectFileNames(String id);
	public int updateSensorPosition(SensorVo vo);
	public int selectSensorSubnum(SensorVo vo);
	public int insertRaspberrySensorSubnum(SensorVo vo);
	public SensorVo selectSensor(String s_uuid);
	public List<SensorVo> selectFloor(String floor);
	public List<FireLogDataVo> selectfirelog();
	public List<FireLogDataVo> selectmyfirelog(String user_id);
	public int insertfirelog(FireLogDataVo vo);
	public List<FireLogDataVo> selectnewfirelog();
	public Greeting selectnoticeFire(Greeting gr);
	public FireLogDataVo selectfirelogOne(FireLogDataVo vo);
	public List<MonitorVo> selectMonitorMonthVolt();
	public List<MonitorVo> selectMonitorNowVolt();
}
