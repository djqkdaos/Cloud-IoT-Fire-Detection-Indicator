package org.firedetection.biz.raspberry.vo;

import java.util.List;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Data(staticConstructor="of")
@NoArgsConstructor
@ToString
public class FireDataVo {
	private String uuid;
	private int detectcnt;
	private List<datas> data;
	
	@Data(staticConstructor="of")
	@NoArgsConstructor
	@ToString
	public static class datas{
		private String s_uuid;
		private String status;
		private float volt;
	}
}
