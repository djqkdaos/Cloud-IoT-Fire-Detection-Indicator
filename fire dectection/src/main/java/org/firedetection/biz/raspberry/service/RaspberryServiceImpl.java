package org.firedetection.biz.raspberry.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.firedetection.biz.board.vo.FileNameVo;
import org.firedetection.biz.board.vo.Greeting;
import org.firedetection.biz.raspberry.dao.RaspberryDAO;
import org.firedetection.biz.raspberry.vo.FireDataVo;
import org.firedetection.biz.raspberry.vo.FireDataVo.datas;
import org.firedetection.biz.raspberry.vo.FireLogDataVo;
import org.firedetection.biz.raspberry.vo.FireLogVo;
import org.firedetection.biz.raspberry.vo.MonitorVo;
import org.firedetection.biz.raspberry.vo.PiaddVo;
import org.firedetection.biz.raspberry.vo.RaspbVo;
import org.firedetection.biz.raspberry.vo.SensorVo;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@Service("RaspberryService")
public class RaspberryServiceImpl implements RaspberryService {

	@Resource(name = "RaspberryDAO")
	RaspberryDAO dao;

	@Override
	public int insertFireData(FireDataVo vo) {
		int cnt = 0;
		List<datas> list = vo.getData();
		FireLogVo lvo;
		java.util.Date udate = new java.util.Date();
		Date regDate = new Date(udate.getTime());
		for (datas data : list) {
			lvo = new FireLogVo(vo.getUuid(), data.getS_uuid(), regDate, data.getStatus(),data.getVolt());
			Map<String, Object> params = new LinkedHashMap<>(); // 파라미터 세팅
			FireLogDataVo vv = new FireLogDataVo();
			vv.setS_uuid(data.getS_uuid());
			vv.setStatus(data.getStatus());
			if (dao.selectfirelogDate(vv) == 0) {
				if (data.getStatus().equals("fire") || data.getStatus().equals("error")) {
					FireLogDataVo flvo = new FireLogDataVo();
					flvo.setS_uuid(data.getS_uuid());
					flvo.setR_uuid(vo.getUuid());
					flvo.setStatus(data.getStatus());
					dao.insertfirelog(flvo);
					FireLogDataVo flvo2 = dao.selectfirelogOne();
					Greeting gtt = new Greeting();
					gtt.setS_uuid(flvo2.getS_uuid());
					String tmp = dao.selectDetectionSuuid(flvo2.getS_uuid()).getFloor();
					String rtmp[] = tmp.split("/");
					gtt.setOld_name(rtmp[1]);
					gtt.setHash(rtmp[0]);

					Greeting gt = dao.selectnoticeFire(gtt);

					params.put("firedate", gt.getFiredate());
					params.put("address", gt.getAddress());
					params.put("floor", gt.getFloor());
					params.put("new_name", gt.getNew_name());
					params.put("status", gt.getStatus());
					params.put("hash", gt.getFloor().split("/")[0]);
					params.put("old_name", gt.getFloor().split("/")[1]);
					params.put("s_uuid", gt.getS_uuid());
					
					

					try {
						System.out.println(params);
						sendGet("http://127.0.0.1:8080/firewebpush", params);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
			cnt += dao.insertFireData(lvo);
		}
		return cnt;
	}

	private void sendGet(String targetUrl, Map<String, Object> map) throws Exception {
		URL url = new URL(targetUrl); // post 방식으로 받을 url 입력
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setDoOutput(true);
		conn.setRequestMethod("POST"); // 보내는 타입
		conn.setRequestProperty("Content-Type", "application/json");
		// 데이터
		Gson gson = new GsonBuilder().setDateFormat("EEE, dd MMM yyyy HH:mm:ss zzz").create();
		String param = gson.toJson(map);
		System.out.println("param : " + param);

		// 전송
		OutputStreamWriter osw = new OutputStreamWriter(conn.getOutputStream(), "UTF-8"); // 한글 깨짐 방지

		try {

			osw.write(param);
			osw.flush();

			// 응답
			BufferedReader br = null;
			br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8")); // 한글 깨짐 방지

			String line = null;
			while ((line = br.readLine()) != null) {
				System.out.println(line);
			}

			// 닫기
			osw.close();
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public int insertRaspberry(RaspbVo vo, PiaddVo pvo) {
		int cnt = dao.insertRaspberry(vo);
		for (int i = 0; i < pvo.getS_uuid().size(); i++) {
			cnt += dao.insertSensor(new SensorVo(vo.getUuid(), pvo.getS_uuid().get(i)));
		}
		return dao.insertRaspberry(vo);
	}

	@Override
	public List<String> selectS_uuid(String id) {
		return dao.selectS_uuid(id);
	}

	@Override
	public List<FileNameVo> selectFileNames(String id) {
		return dao.selectFileNames(id);
	}

	@Override
	public int updateSensorPosition(SensorVo vo) {
		return dao.updateSensorPosition(vo);
	}

	@Override
	public int selectSensorSubnum(SensorVo vo) {
		// TODO Auto-generated method stub
		return dao.selectSensorSubnum(vo);
	}

	@Override
	public int insertRaspberrySensorSubnum(SensorVo vo) {
		// TODO Auto-generated method stub
		return dao.insertRaspberrySensorSubnum(vo);
	}

	@Override
	public SensorVo selectSensor(String s_uuid) {
		// TODO Auto-generated method stub
		return dao.selectSensor(s_uuid);
	}

	@Override
	public List<SensorVo> selectFloor(String floor) {
		// TODO Auto-generated method stub
		return dao.selectFloor(floor);
	}

	@Override
	public List<FireLogDataVo> selectfirelog() {
		// TODO Auto-generated method stub
		return dao.selectfirelog();
	}

	@Override
	public List<FireLogDataVo> selectmyfirelog(String user_id) {
		// TODO Auto-generated method stub
		return dao.selectmyfirelog(user_id);
	}

	@Override
	public int insertfirelog(FireLogDataVo vo) {
		// TODO Auto-generated method stub
		return dao.insertfirelog(vo);
	}

	@Override
	public List<FireLogDataVo> selectnewfirelog() {
		// TODO Auto-generated method stub
		return dao.selectnewfirelog();
	}

	@Override
	public Greeting selectnoticeFire(Greeting gr) {
		// TODO Auto-generated method stub
		return dao.selectnoticeFire(gr);
	}

	@Override
	public FireLogDataVo selectfirelogOne(FireLogDataVo vo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<MonitorVo> selectMonitorMonthVolt() {
		return dao.selectMonitorMonthVolt();
	}

	@Override
	public List<MonitorVo> selectMonitorNowVolt() {
		return dao.selectMonitorNowVolt();
		
	}

}
