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
public class FireLogDataVo {
	private int idx;
	private Date firedate;
	private String r_uuid;
	private String s_uuid;
	private String status;
	private String address;
}
