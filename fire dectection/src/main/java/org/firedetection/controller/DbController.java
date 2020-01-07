package org.firedetection.controller;

import org.firedetection.service.DbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
 
 
@RestController
public class DbController {
    //@Autowired
    DbService dbService;
     
   /* @RequestMapping("/")
    public @ResponseBody String root_test() throws Exception{  
        return "Hello World";
    }
 */
    /*
    @RequestMapping("/now")
    public @ResponseBody String now() throws Exception{
        return dbService.getDual();
    }*/
}


