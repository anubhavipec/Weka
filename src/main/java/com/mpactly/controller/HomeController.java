package com.mpactly.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mpactly.service.WekaService;
import com.mpactly.vo.GraphVO;
import com.mpactly.vo.RequestVO;


@RestController

public class HomeController 
{
	@Autowired
	WekaService wekaService;
	@RequestMapping(value="/file/path" ,method = RequestMethod.POST, consumes="application/json", produces = "application/json" )
	public String getFilePath(@RequestBody RequestVO reqVo)
	{
		String response = null;
		String filePath = reqVo.getFilePath();
		System.err.println("FilePath1 : "+filePath);
		GraphVO gvo = wekaService.analyseData(filePath);
		
		try {
			
			ObjectMapper mapper = new ObjectMapper();
			response = mapper.writeValueAsString(gvo);
			System.err.println("GVO: "+response.toString());
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			
		}
		return response;
	}
	

}
