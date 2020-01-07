package org.firedetection.biz.raspberry.vo;

import java.sql.Date;
import java.util.List;

import org.firedetection.biz.raspberry.vo.FireDataVo.datas;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data(staticConstructor="of")
@NoArgsConstructor
@ToString
public class FireLogVo {
	private String r_uuid;
	private String s_uuid;
	private Date date;
	private String status;
	private float volt;
	public FireLogVo(String r_uuid, String s_uuid, Date date, String status, float volt) {
		super();
		this.r_uuid = r_uuid;
		this.s_uuid = s_uuid;
		this.date = date;
		this.status = status;
		this.volt = volt;
	}
}
