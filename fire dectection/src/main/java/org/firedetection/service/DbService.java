package org.firedetection.service;

import org.firedetection.dao.DbMapper;
import org.firedetection.dao.TestMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DbService {
 
    //@Autowired
    DbMapper dbMapper;
 
    /* select dual */
    public String getDual() throws Exception{
        return dbMapper.getDual();
    }
 
}


