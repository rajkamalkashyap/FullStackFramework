package com.fonepaisa.GenEPG.Controller;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.fonepaisa.GenEPG.CommonUtil.CommonUtil;
import com.fonepaisa.GenEPG.CommonUtil.Constants;
import com.fonepaisa.GenEPG.CommonUtil.ConvertObject;
import com.fonepaisa.GenEPG.DB.CommonDBOperations;
import com.fonepaisa.GenEPG.DB.DBConnector;
import com.fonepaisa.GenEPG.DTO.fetchGameDetailsDTO;
import com.fonepaisa.GenEPG.Exception.GenEPGException;
import com.fonepaisa.GenEPG.entity.game_details;
import com.google.gson.Gson;

@Controller
public class MainController {

	Logger log = Logger.getLogger(MainController.class);
	@Autowired
	private DBConnector dbConnector;
    @Autowired
    private CommonDBOperations comDb;
    @Autowired
   
	@RequestMapping("/helloworld")
	public ModelAndView showMessage() {
		System.out.println("in controller");
		ModelAndView mv = new ModelAndView("helloworld");
		mv.addObject("message", "hellow");
		return mv;
	}

	@RequestMapping(value = "/home")
	public String home( HttpServletRequest request) {
		return "helloworld";
	}

	//api which fetch games data
	@RequestMapping(value = "/fetch/game/details",method = RequestMethod.POST)@Transactional
	public  @ResponseBody fetchGameDetailsDTO setStatus(@RequestBody fetchGameDetailsDTO req, HttpServletRequest request) {
		Query query = dbConnector.getCurrentSession().createQuery("from game_details");
		List<game_details> gameDetails= query.list();
		org.json.simple.JSONObject jobj = new org.json.simple.JSONObject();
		jobj.put("GAME", gameDetails);
		Gson gson = new Gson();
		String gameData = gson.toJson(jobj);
		req.setData(gameData);
		req.setRespCode(GenEPGException.getCode(GenEPGException.SUCCESS));
        req.setRespMsg(GenEPGException.getDescription(GenEPGException.SUCCESS));
		return req;
	}
	
	
		
}
