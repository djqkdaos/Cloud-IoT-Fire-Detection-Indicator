package org.firedetection.biz.users.service;

import javax.annotation.Resource;

import org.firedetection.biz.users.dao.CounselStatusDAO;
import org.springframework.stereotype.Service;

@Service("CounselStatusService")
public class CounselStatusServiceImpl implements CounselStatusService{
	@Resource(name="CounselStatusDAO")
	CounselStatusDAO dao;
}
