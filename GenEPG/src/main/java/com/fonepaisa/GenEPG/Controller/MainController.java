package com.fonepaisa.GenEPG.Controller;

import java.io.BufferedReader;
import java.util.Date;
import java.util.HashMap;
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
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

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
    private HashMap curent_users = new HashMap();
    @RequestMapping(value = "/{test}", method = RequestMethod.GET)
    public ModelAndView login(HttpServletRequest request) {
    	ModelAndView mv = new ModelAndView("login");
    	return mv;
    }
	@RequestMapping("/helloworld")
	public ModelAndView showMessage() {
		System.out.println("in controller");
		ModelAndView mv = new ModelAndView("helloworld");
		mv.addObject("message", "hellow");
		return mv;
	}
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/submitLogin", method = RequestMethod.POST)
	public  @ResponseBody String submitLogin(HttpServletRequest request, HttpServletResponse response) throws Exception{
        StringBuilder buffer = new StringBuilder();
        BufferedReader reader = request.getReader();
        String line, responseCode = "", token = "";
        JSONArray jArray;
        JSONObject loginObj = new JSONObject();
        JSONParser jsonParser = new JSONParser();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        while ((line = reader.readLine()) != null) {
            buffer.append(line);
        }
        String requestString = buffer.toString();
        System.out.println(requestString);
        JSONObject requestJson = (JSONObject) jsonParser.parse(requestString);
        System.out.println(requestJson);
        System.out.println(requestJson);
        if (!StringUtils.isEmpty(requestJson.get("user_id")) && !StringUtils.isEmpty(requestJson.get("password"))){
        	if(((String)requestJson.get("user_id")).equalsIgnoreCase("shyam") && ((String)requestJson.get("password")).equalsIgnoreCase("shyam@123")){
        		request.getSession().setAttribute(request.getSession().getId(), true);
        		System.out.println(request.getSession().getId());
        		System.out.println(request.getSession().getAttribute(request.getSession().getId()));
        		return "/genepg/views/html/main.html";
        	}else{
        		request.getSession().setAttribute("token", false);
        	}
        	if(((String)requestJson.get("user_id")).equalsIgnoreCase("raj") && ((String)requestJson.get("password")).equalsIgnoreCase("raj@123")){
        		request.getSession().setAttribute(request.getSession().getId(), true);
        		System.out.println(request.getSession().getId());
        		System.out.println(request.getSession().getAttribute(request.getSession().getId()));
        		return "/genepg/views/html/main.html";
        	}else{
        		request.getSession().setAttribute("token", false);
        	}
        }
        return "ERROR";
	}
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/submitLogout", method = RequestMethod.POST)
	public  void submitLogout(HttpServletRequest request, HttpServletResponse response) throws Exception{
		request.getSession().removeAttribute(request.getSession().getId());
	}
	@RequestMapping(value = "/home")
	public String home( HttpServletRequest request) {
		return "helloworld";
	}

	//api which fetch games data
	@RequestMapping(value = "/fetch/game/details",method = RequestMethod.POST)@Transactional
	public  @ResponseBody fetchGameDetailsDTO setStatus(@RequestBody fetchGameDetailsDTO req, HttpServletRequest request) {
		System.out.println(request.getSession().getId());
		try{
			System.out.println((boolean)request.getSession().getAttribute(request.getSession().getId())  == false);
			if((boolean)request.getSession().getAttribute(request.getSession().getId()) == false){
				req.setRespCode("9999");
				req.setRespMsg("Unauthorised access");
				return req;
			}
		}catch(Exception e){
			req.setRespCode("9999");
			req.setRespMsg("Unauthorised access");
			return req;
		}
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






























