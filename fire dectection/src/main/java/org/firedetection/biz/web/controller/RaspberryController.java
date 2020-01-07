package org.firedetection.biz.web.controller;

import java.security.SecureRandom;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.firedetection.biz.raspberry.service.RaspberryService;
import org.firedetection.biz.raspberry.vo.FireDataVo;
import org.firedetection.biz.raspberry.vo.MonitorVo;
import org.firedetection.biz.raspberry.vo.PiaddVo;
import org.firedetection.biz.raspberry.vo.RaspbVo;
import org.firedetection.biz.raspberry.vo.SensorVo;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@RestController
public class RaspberryController {

	@Resource(name = "RaspberryService")
	RaspberryService rasService;
	
	@RequestMapping(value = "/getuuid", method = RequestMethod.GET)
	public HashMap<String, Object> getuuid(@RequestParam(value = "num") int cnt) {
		HashMap<String, Object> map = new HashMap<>();
		UUID uuid = UUID.randomUUID();
		if (cnt == 1) {
			map.put("uuid1", uuid);
		}
		for (int i = 0; i < cnt; i++) {
			uuid = UUID.randomUUID();
			map.put("uuid" + (i + 1), uuid);
		}
		return map;
	}

	// RequestBody
	@RequestMapping(value = "/piadd", method = RequestMethod.POST)
	public String piadd(@RequestBody PiaddVo vo) {
		HttpServletRequest req = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes())
				.getRequest();
		String ip = req.getHeader("X-FORWARDED-FOR");
		if (ip == null)
			ip = req.getRemoteAddr();
		RaspbVo rvo = new RaspbVo();
		rvo.setIpaddr(ip);
		rvo.setUserid("nouser");
		rvo.setUuid(vo.getUuid());
		rvo.setDetection_cnt(vo.getDetection_cnt());
		System.out.println(vo);
		return "총"+rasService.insertRaspberry(rvo, vo)+"개함";
	}

	@RequestMapping(value="/setposition", method = RequestMethod.POST)
	public String setpos(@RequestBody SensorVo vo) {
		System.out.println(vo);
		if(vo.getS_uuid().contains("A")) {
			String tmp[] = vo.getS_uuid().split("A");
			vo.setSub_num(Integer.parseInt(tmp[1]));
			vo.setS_uuid(tmp[0]);
		}else {
			vo.setSub_num(0);
		}
		int result=0;
		if(rasService.selectSensorSubnum(vo)!=0) {
			result = rasService.updateSensorPosition(vo);
		}else {
			SensorVo svo = rasService.selectSensor(vo.getS_uuid());
			svo.setSub_num(vo.getSub_num());
			rasService.insertRaspberrySensorSubnum(svo);
			result = rasService.updateSensorPosition(vo);
		}
		return "suss"+result;
	}
	
	@RequestMapping(value = "/jsontest", method = RequestMethod.POST)
	public String jsontest(@RequestBody String tmp) {
		return tmp;
	}
	
	@RequestMapping(value = "/getposition", method = RequestMethod.POST)
	public Object getposition(@RequestBody SensorVo vo) {
		System.out.println(vo.getFloor());
		List<SensorVo> list = rasService.selectFloor(vo.getFloor());
		System.out.println("앙 호출띠");
		Map<String, Object> retVal = new HashMap<String, Object>();
		System.out.println("감지기 목록 전송 : "+list);
		retVal.put("list", list);
		return retVal;
	}
	
	@RequestMapping(value = "/raspbedata", method = RequestMethod.POST)
	public String rasdata(@RequestBody FireDataVo vo) {
		System.out.println(vo);
		
		return ""+rasService.insertFireData(vo)+"건 등록되었습니다.";
	}
	
	@RequestMapping(value = "/monitordata", method = RequestMethod.GET)
	public HashMap<String, Object> monitordatadata() {
		HashMap<String, Object> map = new HashMap<String, Object>();
		List<MonitorVo> list = rasService.selectMonitorMonthVolt();
		List<MonitorVo> volt = rasService.selectMonitorNowVolt();
		List<String> labels = new ArrayList<String>();
		List<String> data1 = new ArrayList<String>();
		List<String> data2 = new ArrayList<String>();
		List<String> data3 = new ArrayList<String>();
		for(MonitorVo tmp:list) {

			if(tmp.getS_uuid().equals("c6088193-2744-449e-a317-95b61127b781")) {
				labels.add(tmp.getDate().toString());
				data1.add(tmp.getVolt());
			}else if(tmp.getS_uuid().equals("abcdefgh-jklm-1234-1234-123123123123")) {
				data2.add(tmp.getVolt());
			}else {
				data3.add(tmp.getVolt());
			}
		}
		map.put("labels", labels);
		map.put("data1",data1);
		map.put("data2",data2);
		map.put("data3",data3);
		String status="nomal";
		for(MonitorVo tmp:volt) {
			if(Double.parseDouble(tmp.getVolt())>1 && 12>Double.parseDouble(tmp.getVolt())) {
				status="Fire!";
			}else if(Double.parseDouble(tmp.getVolt())<1 && 23<Double.parseDouble(tmp.getVolt())) {
				status="Error!";
			}
			if(tmp.getS_uuid().equals("c6088193-2744-449e-a317-95b61127b781")) {
				SecureRandom rand = new SecureRandom();
				map.put("no1",18.0 + rand.nextInt(10) * 0.1);
			}else if(tmp.getS_uuid().equals("abcdefgh-jklm-1234-1234-123123123123")) {
				map.put("no2",tmp.getVolt());
			}else {
				map.put("no3",tmp.getVolt());
			}
		}
		map.put("status", status);
		System.out.println("chartdata = "+map);
		return map;
	}
	
}
