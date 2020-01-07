package org.firedetection.biz.board.vo;

import java.sql.Date;
import java.sql.Timestamp;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data(staticConstructor="of")
@NoArgsConstructor
@ToString
public class Greeting {
	private Timestamp firedate;
	private String s_uuid;
	private String address;
	private String status;
	private String floor;
	private String new_name;
	private String hash;
	private String old_name;
}
