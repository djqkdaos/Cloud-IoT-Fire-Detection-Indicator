package org.firedetection.biz.raspberry.vo;

import java.sql.Date;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data(staticConstructor="of")
@NoArgsConstructor
@ToString
public class MonitorVo {
	private Date date;
	private String volt;
	private String s_uuid;
}
