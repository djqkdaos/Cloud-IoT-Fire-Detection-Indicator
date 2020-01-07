package org.firedetection.biz.raspberry.vo;

import java.sql.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data(staticConstructor="of")
@NoArgsConstructor
public class PiaddVo {
	private String uuid;
	private int detection_cnt;
	private List<String> s_uuid;
}
