package com.infy.isa.controller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.infy.isa.entity.SimDetailsEntity;
import com.infy.isa.entity.SimOffersEntity;
import com.infy.isa.exception.InvalidDetails;
import com.infy.isa.exception.InvalidSimOrServiceNumber;
import com.infy.isa.exception.SimAlreadyActiveException;
import com.infy.isa.model.ServiceDetails;
import com.infy.isa.repository.SimSummary;
import com.infy.isa.service.SimService;

@RestController
@RequestMapping("sim")
@SessionAttributes({"simDetails","offerDetails"})
public class SimController {
	@Autowired
	private SimService simService;
	
	@RequestMapping(value = "/validation", method = RequestMethod.POST,headers="Accept=application/json")
	public ResponseEntity<SimDetailsEntity> verifySimDetails(@Valid @RequestBody ServiceDetails serviceDetails,BindingResult result,HttpSession session)
			throws JsonProcessingException, InvalidSimOrServiceNumber,SimAlreadyActiveException {
		System.out.println("in controller body");
		if(result.hasErrors()) {
			StringBuilder errorMessage=new StringBuilder();
			for(ObjectError x: result.getAllErrors()) {
				errorMessage.append(x.getDefaultMessage()+"\n");
			}
			throw new InvalidSimOrServiceNumber(errorMessage.toString());
		}
		else {
			SimDetailsEntity simDetails=simService.findSimDetails(serviceDetails.getSimNumber(), serviceDetails.getServiceNumber());
		if(simDetails==null) {
			throw new InvalidSimOrServiceNumber("Invalid Sim or Service Number");
		}
		else if(simDetails.getSimStatus().equalsIgnoreCase("active")) {
			throw new SimAlreadyActiveException("Sim Already Active");
		}
		else {
			System.out.println("inside success");
			session.setAttribute("simDetails", simDetails);
			return new ResponseEntity<SimDetailsEntity>(simDetails,HttpStatus.OK);
		}}
		
		
	}
	
	@RequestMapping(value = "/offer/{simNum}", method = RequestMethod.GET,headers="Accept=application/json")
	public ResponseEntity<SimOffersEntity> displayOffer(@PathVariable Long simNum,HttpSession session) {
		System.out.println("in controller body2");
		SimOffersEntity simOffer=simService.findOffer(simNum);
		session.setAttribute("offerDetails", simOffer);
		return new ResponseEntity<SimOffersEntity>(simOffer,HttpStatus.OK);
		
		
	}
	@RequestMapping(value = "/activation", method = RequestMethod.POST,headers="Accept=application/json")
	public ResponseEntity<Boolean> activateSim(@Valid @RequestBody SimSummary simSummary,BindingResult result,HttpSession session) 
	throws InvalidDetails{
		System.out.println("in activation contoller body");
		if(result.hasErrors()) {
			StringBuilder errorMessage=new StringBuilder();
			for(ObjectError x: result.getAllErrors()) {
				errorMessage.append(x.getDefaultMessage()+"\n");
			}
			throw new InvalidDetails(errorMessage.toString());
		}
		else {
			System.out.println("inside successful");
			System.err.println(simSummary.getSimNumber());
			simService.activateSim(Long.parseLong(simSummary.getSimNumber()));
			return new ResponseEntity<Boolean>(true,HttpStatus.OK);
		}
		
		
	}
}
