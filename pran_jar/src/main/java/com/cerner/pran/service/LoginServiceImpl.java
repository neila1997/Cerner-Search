package com.cerner.pran.service;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cerner.pran.dao.AdminDao;
import com.cerner.pran.dao.RegisterDao;
import com.cerner.pran.model.CernerAdmin;
import com.cerner.pran.model.HospitalAdmin;

@Service
public class LoginServiceImpl implements LoginService {

	@Autowired 
	UtilitiesService utilities;
	@Autowired 
	RegisterDao repo;
	@Autowired 
	AdminDao adminDao;
	
	@Override
	public boolean updatePassword(HospitalAdmin admin) {
		System.out.println("Update Password " + admin.getPersonnelPassword());
		admin.setPersonnelPassword(utilities.passwordEncrypter(admin.getPersonnelPassword()));
		System.out.println("Updated Password " + admin.getPersonnelPassword());
		return repo.updatePassword(admin);
	}
	
	@Override
	public boolean login(HospitalAdmin admin) {
		admin.setPersonnelPassword(utilities.passwordDecrypter(admin.getPersonnelPassword()));
		return repo.loginAdmin(admin);
	}
	
	@Override
	public HospitalAdmin login(JSONObject json) {
		try {
			String email = json.getString("email");
			String password = json.getString("password");
			HospitalAdmin admin = repo.login(email, password);
			String decryptedPassword = utilities.passwordDecrypter(admin.getPersonnelPassword()).trim();
			if (decryptedPassword.equals(password)) {
				repo.updateLoginDate(admin.getPersonnelEmail());
				return admin;
			} else
				return null;

		} catch (JSONException e) {
			System.out.println("JSON Exception");
			return null;
		}
		catch(NullPointerException e) {
			System.out.println("Null Pointer - No such admin / Invalid Credentials");
			return null;
		}
	}
	
	@Override
	public CernerAdmin loginCernAdmin(JSONObject json) {try {
		String email = json.getString("email");
		String password = json.getString("password");
		CernerAdmin admin = adminDao.loginCernAdmin(email.toUpperCase());
		String decryptedPassword = utilities.passwordDecrypter(admin.getPersonnelPassword()).trim();
		if (decryptedPassword.equals(password)) {
			repo.updateCernLoginDate(admin.getPersonnelEmail());
			return admin;
		} else
			return null;

	} catch (JSONException e) {
		System.out.println("JSON Exception");
		return null;
	}
	catch(NullPointerException e) {
		System.out.println("Null Pointer - No such admin / Invalid Credentials");
		return null;
	}
		
	}
}
