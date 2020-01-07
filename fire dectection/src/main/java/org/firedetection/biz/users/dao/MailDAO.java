package org.firedetection.biz.users.dao;

import org.firedetection.biz.users.vo.MailVO;

public interface MailDAO {
	public MailVO init(int port);
	public boolean sendEmail(String subject, String contents, String to, MailVO vo);
}
