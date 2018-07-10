package com.test.TestAssignment1.Controller;

import java.util.Map;
import java.util.concurrent.ExecutionException;









import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
/*import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;*/
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.web.WebMvcProperties.View;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;








import org.springframework.web.servlet.ModelAndView;

import com.test.TestAssignment1.Listeners.Publisher;
import com.test.TestAssignment1.Pojo.SampleRequestMessage;
import com.test.TestAssignment1.Pojo.SampleResponseMessage;
import com.test.TestAssignment1.Services.EmpRegistrationService;

@RestController
@EnableAutoConfiguration
public class ApplicationController {

	@Autowired
	public Publisher publisher;
	@Autowired
	public EmpRegistrationService empRegService;
	
	private final Logger app_logger =LogManager.getLogger(this.getClass());
	
	@RequestMapping(value="/processmsg",method=RequestMethod.GET,produces="text/plain")
	public String ProcessMessages(@RequestParam("msgs") String[] msgs) {
		SampleResponseMessage respMsg = null;
		
		for(String msg : msgs){
			/*System.out.println("Received msg is:" + msg);*/
			app_logger.debug("Received msg is:" + msg);
			SampleRequestMessage reqMsg = new SampleRequestMessage(msg);
			
			app_logger.debug("About to call the publisher");
				try {
					publisher.publishToDirectExchangeRPCStyle(reqMsg);
				} catch (InterruptedException | ExecutionException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					app_logger.error("Error while processing queue",e);
					
					
				}
				
				app_logger.debug("Pushing the msgs to quee got completed sucessfully");	
			
		}
		return "Sucess";
	}
	
	/*@RequestMapping(value="/sample",method=RequestMethod.GET)
	public ModelAndView Sample(Map<String, Object> model){
		ModelAndView mv = new ModelAndView();
		mv.setViewName("Sample");
		mv.addObject("msg", "hello welcome");
		 
		return mv;
	}*/
	
	@RequestMapping(value="/empregistration",method=RequestMethod.POST,produces="text/plain")
	public int empRegistration(HttpServletRequest  req){
		
		int age = 0;
		double salary = 0.0;
		int experience = 0;
		String fname="";
		String lname="";
		int count=0;
		app_logger.debug("Inside employee registration service");
		try{
		if(req.getParameter("empfname")!=null){
			fname= req.getParameter("empfname");
		}
		if(req.getParameter("emplname")!=null){
			lname =req.getParameter("emplname");
		}
		if(req.getParameter("empage")!=null){
			age =Integer.parseInt(req.getParameter("empage"));
		}
		if(req.getParameter("empsalary")!=null){
			salary =Double.parseDouble(req.getParameter("empsalary"));
		}
		if(req.getParameter("empexperience")!=null){
			experience = Integer.parseInt(req.getParameter("empexperience"));
		}
		
		 count=empRegService.saveEmpDetails(fname, lname, age, salary, experience);
		}catch(Exception e){
			app_logger.error("Error from service of processing empregestration is:",e);
		}
		return count;
	}
	
}
