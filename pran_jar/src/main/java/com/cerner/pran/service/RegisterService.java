package com.cerner.pran.service;

import java.util.ArrayList;
import java.util.List;

import com.cerner.pran.model.Hospital;
import com.cerner.pran.model.HospitalAdmin;
public interface RegisterService {
	public void registerHospital(Hospital hospital);
	public boolean findHospitalByName(String hospName);
	boolean sendRegistrationMail(HospitalAdmin admin);
//	public String allHospitals(String hospital);
//	public String seeAllHospital(String hospName);
	public List<Hospital> ah();
	public Hospital hospView(String hospMail); 
}
