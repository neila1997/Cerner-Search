package com.cerner.pran.service;

import java.util.Date;
import java.util.List;

import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cerner.pran.dao.RegisterDao;
import com.cerner.pran.model.Hospital;
import com.cerner.pran.model.HospitalAdmin;

@Service
public class RegisterServiceImpl implements RegisterService {

	@Autowired
	private RegisterDao repo;
	@Autowired 
	private UtilitiesService utilities;

	@Override
	public void registerHospital(Hospital hospital) {

		hospital.setHospitalCreateDate(new Date());
		hospital.getSite().setCreatedDate(new Date());
		hospital.getContact().setCreatedDate(new Date());
		hospital.getSite().getLocation().setCreatedDate(new Date());

		// Setting the objects
		hospital.getSite().setHospital(hospital);
		hospital.getContact().setHospital(hospital);
		hospital.getContact().setHospitalSite(hospital.getSite());
		hospital.getAdmin().setHospital(hospital);
		String encryptedPassword = utilities.passwordEncrypter(utilities.passwordGenerator(8));
		hospital.getAdmin().setPersonnelPassword(encryptedPassword);

		repo.registerHospital(hospital);
	}
	
	@Override
	public boolean sendRegistrationMail(HospitalAdmin admin) {
		String registerText = "Hi <b>" + admin.getPersonnelName()
				+ "</b>, <br><br>Welcome to Cerner Search. Your initial password to login is "
				+ utilities.passwordDecrypter(admin.getPersonnelPassword());
		String registerSubject = "Welcome to Cerner Search";
		return utilities.mailSender(admin.getPersonnelEmail(), registerSubject, registerText);
	}

	
	@Override
	public boolean findHospitalByName(String hospName) {
		return repo.findHospitalByName(hospName);
	}

//	@Override
//	public String allHospitals(String hospital) {
//		// TODO Auto-generated method stub
//		return repo.allHospitals(hospital);
//	}

//	@Override
//	public String seeAllHospital(String hospName) {
//		return repo.seeAllHospital(hospName);
//		 
//	}

	@Override
	public List<Hospital> ah() {
		return repo.ah();
		
	}






}
