package org.firedetection.biz.board.vo;



import java.sql.Date;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data(staticConstructor="of")
@NoArgsConstructor
@ToString
public class FileListVO {
	private String new_name;
	private String hash;
	private String filesize;
	private Date regdate;
	private String user_id;
	private String old_name;
	public FileListVO(String hash, String old_name) {
		super();
		this.hash = hash;
		this.old_name = old_name;
	}
}

