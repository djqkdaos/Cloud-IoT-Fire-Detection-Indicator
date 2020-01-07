package org.firedetection.biz.users.service;

import javax.annotation.Resource;

import org.firedetection.biz.users.dao.IntroduceDAO;
import org.springframework.stereotype.Service;

@Service("IntroduceService")
public class IntroduceServiceImpl implements IntroduceService{
	@Resource(name="IntroduceDAO")
	IntroduceDAO dao;
}
