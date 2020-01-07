package org.firedetection.biz.raspberry.vo;

import java.util.List;

import org.firedetection.biz.raspberry.vo.FireDataVo.datas;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data(staticConstructor="of")
@NoArgsConstructor
@ToString
public class DetectionListVo {
	private String s_uuid;
}
