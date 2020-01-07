package org.firedetection.biz.board.vo;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data(staticConstructor="of")
@NoArgsConstructor
@ToString
public class FileNameVo {
	private String hash;
	private String old_name;
	private String new_name;
	private String user_id;
}
