package org.firedetection.service;

import java.util.List;

import org.firedetection.dao.TestMapper;
import org.firedetection.vo.TestVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TestService {
	
	//@Autowired
    TestMapper testMapper;
 
    /* select dual */
    public String getTestList() throws Exception{
        return testMapper.getTestList();
    }


}
