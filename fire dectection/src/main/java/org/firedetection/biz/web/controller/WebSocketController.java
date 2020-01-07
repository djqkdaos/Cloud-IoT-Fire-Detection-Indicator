package org.firedetection.biz.web.controller;

import java.util.HashMap;
import java.util.UUID;

import javax.annotation.Resource;

import org.firedetection.biz.board.vo.Greeting;
import org.firedetection.biz.board.vo.WebSocketMessage;
import org.firedetection.biz.raspberry.service.RaspberryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WebSocketController {

	@Autowired
	private SimpMessagingTemplate template;

	@Resource(name = "RaspberryService")
	RaspberryService rasService;
	
	@MessageMapping("/hello")
	@SendTo("/topic/greetings")
	public Greeting greeting(String message) throws Exception {
		Thread.sleep(100); // simulated delay
		
		
		return null;
	}

	public void fireGreeting(Greeting gt) {
		System.out.println("Fire 호출");
		this.template.convertAndSend("/topic/greetings", gt);
	}
	
	@RequestMapping(value = "/firewebpush", method = RequestMethod.POST)
	public String getuuid(@RequestBody Greeting gt) {
		fireGreeting(gt);
		System.out.println("완료");
		return "suss";
	}
}
