package com.cerner.pran.controller;

import java.net.URLEncoder;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cerner.pran.dao.AdminDao;
import com.cerner.pran.dao.RegisterDaoImpl;
import com.cerner.pran.model.CernerAdmin;
import com.cerner.pran.model.Hospital;
import com.cerner.pran.model.HospitalAdmin;
import com.cerner.pran.service.LoginService;
import com.cerner.pran.service.RegisterService;
import com.cerner.pran.service.UtilitiesService;

@RestController
@CrossOrigin()
public class HospitalController {

	@Autowired 
	public AdminDao dao;
	@Autowired
	public RegisterDaoImpl dao1;	
	@Autowired
	public RegisterService register;
	@Autowired
	public LoginService login;
	@Autowired 
	public UtilitiesService utilities;
	
//	@GetMapping("/testapi")
//	public Hospital testApi() {
//		System.out.println(dao.findByEmail("arpitlall318@gmail.com").getHospital());
//		return dao.findByEmail("arpitlall318@gmail.com").getHospital();
//	}
	
	@GetMapping("/findhospital")
	public ResponseEntity<Boolean> findHospital(@RequestParam("name") String name){
		System.out.println(name);
		boolean bool = register.findHospitalByName(name);
		return new ResponseEntity<Boolean>(bool, HttpStatus.OK);
	}
	
	@PostMapping(path="/validatehospital")
	public ResponseEntity<Boolean> validateHospital(@RequestBody HospitalAdmin admin){
		//Hospital hospital = null;
		//hospital.setVerifiedInd(1);
		
		return new ResponseEntity<Boolean>(register.sendRegistrationMail(admin), HttpStatus.OK);
	}
	
	@PostMapping(path="/register")
	public ResponseEntity<String> registerApi(@RequestBody Hospital hospital) {
		System.out.println(hospital);
		register.registerHospital(hospital);
		return new ResponseEntity<String>("success",HttpStatus.OK);
	}

	@PostMapping(path="/verify")
	public ResponseEntity<Boolean> verifyCredentials(@RequestBody HospitalAdmin admin){
		return new ResponseEntity<Boolean>(login.login(admin),HttpStatus.OK);
	}
	
	@PostMapping(path="/update")
	public ResponseEntity<Boolean> updatePassword(@RequestBody HospitalAdmin admin){
		return new ResponseEntity<Boolean>(login.updatePassword(admin),HttpStatus.OK);
	}
	
	@PostMapping(path="/adminlogin")
	public ResponseEntity<HospitalAdmin> adminLogin(HttpEntity<String> httpEntity) {
		JSONObject jsonObject = null;
		try {
			jsonObject = new JSONObject(httpEntity.getBody());
			System.out.println(jsonObject);
			HospitalAdmin admin = login.login(jsonObject);
			if(admin != null)
				return new ResponseEntity<HospitalAdmin>(admin, HttpStatus.OK);
			else {
				return new ResponseEntity<HospitalAdmin>(admin, HttpStatus.BAD_REQUEST);
			}
			
		} catch (JSONException e) {
			return new ResponseEntity<HospitalAdmin>(login.login(jsonObject), HttpStatus.BAD_REQUEST);
			// TODO Auto-generated catch block
		}
	}
	
	@PostMapping(path="/cernadminlogin")
	public ResponseEntity<CernerAdmin> cernAdminLogin(HttpEntity<String> httpEntity) {
		JSONObject jsonObject = null;
		try {
			jsonObject = new JSONObject(httpEntity.getBody());
			System.out.println(jsonObject);
			CernerAdmin admin = login.loginCernAdmin(jsonObject);// = login.login(jsonObject);
			if(admin != null)
				return new ResponseEntity<CernerAdmin>(admin, HttpStatus.OK);
			else {
				return new ResponseEntity<CernerAdmin>(admin, HttpStatus.BAD_REQUEST);
			}
			
		} catch (JSONException e) {
			return new ResponseEntity<CernerAdmin>(new CernerAdmin(), HttpStatus.BAD_REQUEST);
			// TODO Auto-generated catch block
		}
	}
	
	//to get list of all hospitals
//	@GetMapping(path="/hlist")
//	public ResponseEntity<String> hello(HttpEntity<String> httpEntity) {
//		System.out.println("inside");
//		System.out.println(register1.allHospitals("arpitlall20@gmail.com"));
//		return new ResponseEntity<String>("apollo",HttpStatus.OK);
//	}
	
//	@GetMapping(path="/hlist")
//	public ResponseEntity<Hospital> hello(HttpEntity<String> httpEntity) {
//		System.out.println("inside");
//		System.out.println(dao1.viewall("arpitlall20@gmail.com"));
//		return new ResponseEntity<Hospital>(dao1.viewall("arpit test"),HttpStatus.OK);
//	}
	
	@GetMapping(path="/hlist")
	public ResponseEntity<List<Hospital>> hello(HttpEntity<String> httpEntity){
		System.out.println("inside");
		//System.out.println(dao1.seeAllHospital(hospital));
//		return new ResponseEntity<Hospital>(register1.seeAllHospital(hospital));
//		register.seeAllHospital(null);
//		register.ah();
		return new ResponseEntity<List<Hospital>>(register.ah(),HttpStatus.OK);
	}
	
	@GetMapping(path="/testapi")
	public ResponseEntity<Hospital> hello1(HttpEntity<String> httpEntity) {
		System.out.println(httpEntity.getBody());
		return new ResponseEntity<Hospital>(dao1.getHospitalDetails(httpEntity.getBody()),HttpStatus.OK);
	}
	
//	@GetMapping(path="/hospview")
//	public ResponseEntity<Hospital> hospviewdet(HttpEntity<String> httpEntity)
//	{	
//		HospitalAdmin admin = null;
//		return new ResponseEntity<Hospital>(register.hospView(admin.getPersonnelEmail()),HttpStatus.OK);
//	}

	
	@GetMapping(path="/hospview")
	public ResponseEntity<Hospital> hospviewdet(@RequestParam("email") String email)
	{	
		//name = "arpitlall20@gmail.com";
		System.out.println(email);
		email = this.utilities.passwordDecrypter(email).trim();
		
		System.out.println(email);
		return new ResponseEntity<Hospital>(register.hospView(email),HttpStatus.OK);
	}
	
	@PostMapping(path="/verifya")
	public ResponseEntity<Boolean> cernAdminLogin1(@RequestBody String email) {
		JSONObject jsonObject = null;
		boolean t;
		System.out.println("testing");
		String append = this.utilities.passwordEncrypter(email).trim();
		System.out.println(append);
		String replace = append.replaceAll("/","%2F");
		String replace1 = replace.replaceAll("=","%3D");
		String replace2 = replace1.replaceAll("\\+","%2B");
		System.out.println(replace1);
		t = this.utilities.mailSender(email, "Please re-enter your data" , "http://localhost:4200/hospitalview/"+replace2);
		
		return new ResponseEntity<Boolean>(t,HttpStatus.OK);
	}
	
	@PostMapping(path="/mapprocedure")
	public ResponseEntity<Boolean> mapProcedure(@RequestBody String procedures){
		JSONObject json = null;
		try {
			json = new JSONObject(procedures);
		} catch (JSONException e) {
			e.printStackTrace();
		} 
		System.out.println(json);
		return new ResponseEntity<Boolean>(true, HttpStatus.OK);
	
	}
	
}
