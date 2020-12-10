package com.cerner.pran.service;

import org.json.JSONObject;

import com.cerner.pran.model.CernerAdmin;
import com.cerner.pran.model.HospitalAdmin;

public interface LoginService {
	
	HospitalAdmin login(JSONObject json);
	boolean updatePassword(HospitalAdmin admin);
	boolean login(HospitalAdmin admin);
	CernerAdmin loginCernAdmin(JSONObject json);
}
