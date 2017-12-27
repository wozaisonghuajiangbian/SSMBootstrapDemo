package com.slcf.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.slcf.pojo.BillNote;

@Controller
@RequestMapping("/activiti")
public class ActivitiController {

	
	@RequestMapping("/goQuest.action")
	public String goQuest(){
		return "quest";
	}
	
	@RequestMapping("/goTask.action")
	public String goTask(){
		return "task";
	}
	
	@RequestMapping("/goProcess.action")
	public String goProcess(){
		return "process";
	}
	
	@ResponseBody
	@RequestMapping("/addNote.action")
	public boolean seveBillNote(BillNote note){
		
		String str1=note.getStratTime().substring(0, 10);
		String str2=note.getEndTime().substring(0, 10);
		Date date1=null,date2=null;
		try {
			date1 = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss").parse(str1);
			date2 = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss").parse(str2);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		//日期相减得到相差的日期
		long day = (date1.getTime()-date2.getTime())/(24*60*60*1000)>0 ? (date1.getTime()-date2.getTime())/(24*60*60*1000):(date2.getTime()-date1.getTime())/(24*60*60*1000);
		System.out.println("相差的日期: " +day);
		return true;
	}
}
