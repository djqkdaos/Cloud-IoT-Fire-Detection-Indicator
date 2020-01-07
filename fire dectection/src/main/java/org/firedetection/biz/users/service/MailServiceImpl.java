package org.firedetection.biz.users.service;

import javax.annotation.Resource;

import org.firedetection.biz.users.dao.MailDAO;
import org.firedetection.biz.users.vo.MailVO;
import org.springframework.stereotype.Service;

@Service("mailService")
public class MailServiceImpl implements MailService{
	
	@Resource(name="MailDAO")
	MailDAO dao;
	
	@Override
	public MailVO init(int port) {
		return dao.init(port);
	}

	@Override
	public boolean sendEmail(String subject, String contents, String to, MailVO vo) {
		return dao.sendEmail(subject, contents, to, vo);
	}


}
