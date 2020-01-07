package org.firedetection.biz.raspberry.vo;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data(staticConstructor="of")
@NoArgsConstructor
public class SensorVo {
	private int idx;
	private String r_uuid;
	private String s_uuid;
	private double x;
	private double y;
	private String s_name;
	private String floor;
	private int sub_num;
	public SensorVo(String r_uuid, String s_uuid) {
		super();
		this.r_uuid = r_uuid;
		this.s_uuid = s_uuid;
	}
}