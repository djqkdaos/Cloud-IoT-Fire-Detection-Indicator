package org.firedetection.biz.raspberry.vo;

import java.sql.Date;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data(staticConstructor="of")
@NoArgsConstructor
public class RaspbVo {
	private int raspb_num;
	private String ipaddr;
	private String uuid;
	private int detection_cnt;
	private String userid;
	public RaspbVo(String ipaddr, String uuid, int detection_cnt, String userid) {
		super();
		this.ipaddr = ipaddr;
		this.uuid = uuid;
		this.detection_cnt = detection_cnt;
		this.userid = userid;
	}
}
