package com.cerner.pran.dao;

import java.util.List;

import com.cerner.pran.model.CernerAdmin;
import com.cerner.pran.model.Hospital;
import com.cerner.pran.model.HospitalAdmin;

public interface RegisterDao {
	public void registerHospital(Hospital hospital);
	public boolean loginAdmin(HospitalAdmin admin);
	public boolean updatePassword(HospitalAdmin admin);
	public boolean findHospitalByName(String hospName);
	public HospitalAdmin login(String email, String password);
	public boolean updateLoginDate(String email);
	boolean updateCernLoginDate(String email);
//	public String allHospitals(String hospital);
//	public Hospital viewall(String hospName);
	public Hospital getHospitalDetails(String registerNo);
//	public String seeAllHospital(String hospName);
	public List<Hospital> ah();
	public Hospital fetchHospital(String hospName);
	
}
