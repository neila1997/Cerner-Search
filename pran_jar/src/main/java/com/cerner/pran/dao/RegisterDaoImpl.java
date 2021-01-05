package com.cerner.pran.dao;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cerner.pran.model.CernerAdmin;
import com.cerner.pran.model.Hospital;
import com.cerner.pran.model.HospitalAdmin;

@Repository
@Transactional
public class RegisterDaoImpl implements RegisterDao {

	@Autowired
	EntityManager em;

	@Override
	public void registerHospital(Hospital hospital) {

		em.persist(hospital.getAdmin());
		em.persist(hospital.getContact());
		em.persist(hospital.getSite());
	}
	

	@Override
	public boolean loginAdmin(HospitalAdmin admin) {
		TypedQuery<HospitalAdmin> query = em.createQuery(
				"select admin from HospitalAdmin admin where admin.personnelEmail = :email and admin.personnelPassword = :password", HospitalAdmin.class);
		query.setParameter("email", admin.getPersonnelEmail());
		query.setParameter("password", admin.getPersonnelPassword());
		try {
			HospitalAdmin adminResult = query.getSingleResult();
			if (adminResult != null)
				return true;
			else
				return false;
		} catch (NoResultException e) {
			return false;
		}
	}

	@Override
	public boolean updatePassword(HospitalAdmin admin) {
		Query query = em.createQuery(
				"update HospitalAdmin set personnelPassword = :password where personnelEmail = :email");
		query.setParameter("email", admin.getPersonnelEmail());
		query.setParameter("password", admin.getPersonnelPassword());
		int n = query.executeUpdate();
		if(n == 0)
			return false;
		else 
		{	
			this.updateLoginDate(admin.getPersonnelEmail());
			return true;
		}
	}

	@Override
	public boolean findHospitalByName(String hospName) {
		TypedQuery<Hospital> query = em.createQuery("select hospital from Hospital hospital where hospital.hospitalName = :name", Hospital.class);
		query.setParameter("name", hospName);
		try {
			Hospital hosp = query.getSingleResult();
			if(hosp!=null)
				return true;
			else
				return false;
		}
		catch(NoResultException e) {
			return false;
		}
		catch(NonUniqueResultException e) {
			return false;
		}
	}

	@Override
	public HospitalAdmin login(String email, String password) {
		HospitalAdmin admin;
		TypedQuery<HospitalAdmin> query = em.createQuery("select admin from HospitalAdmin admin where LOWER(admin.personnelEmail) = :email", HospitalAdmin.class);
		query.setParameter("email", email.toLowerCase());
		try {
			admin = query.getSingleResult();
			return admin;
		}
		catch(Exception e) {
			return null;
		}
	}
	
//	@Override
//	public Hospital viewall(String hospName) {
//		Hospital hospital;
//		TypedQuery<Hospital> query = em.createQuery("select hospital from Hospital hospital where hospital.hospitalName = :hospName", Hospital.class);
//		query.setParameter("hospName", hospName);
//		hospital = query.getSingleResult();
////		String a = hospital.getHospitalName();
////		String b = hospital.getHospitalRegistrationNo();
////		System.out.println(a+" "+b);
//		// TODO Auto-generated method stub
//		return hospital;
//	}
	
	@Override
	public Hospital getHospitalDetails(String registerNo) {
		Hospital hospital;
		TypedQuery<Hospital> query = em.createQuery("select hospital from Hospital hospital where hospital.hospitalRegistrationNo = :registerNo", Hospital.class);
		query.setParameter("registerNo", registerNo);
		hospital = query.getSingleResult();
//		String a = hospital.getHospitalName();
//		String b = hospital.getHospitalRegistrationNo();
//		System.out.println(a+" "+b);
		// TODO Auto-generated method stub
		return hospital;
	}

	
	@Override
	public boolean updateLoginDate(String email) {
		Query query = em.createQuery("update HospitalAdmin set lastLoginDate = :date where personnelEmail = :email");
		query.setParameter("date", new Date());
		query.setParameter("email", email);
		int n = query.executeUpdate();
		if(n != 0)
			return true;
		else
			return false;
	}
	
/*
    public Hospital test(String hosp) {
    	TypedQuery<Hospital> query = em.createQuery("select hospital from Hospital hospital where hospital.hospitalName = :name", Hospital.class);
    	System.out.println(hosp);
    	try {
        	query.setParameter("name", hosp);
//        	System.out.println(query.getSingleResult().admin);
        	System.out.println(em.createQuery("select admin from HospitalAdmin admin where admin.hospital = :hospital", HospitalAdmin.class).setParameter("hospital", query.getSingleResult()).getSingleResult());
    		return query.getSingleResult();
    	}
    	catch (Exception e) {
    		return null;
			// TODO: handle exception
		}
    }*/
    
	@Override
	public boolean updateCernLoginDate(String email) {

		Query query = em.createQuery("update CernerAdmin set lastLoginDate = :date where personnelEmail = :email");
		query.setParameter("date", new Date());
		query.setParameter("email", email);
		int n = query.executeUpdate();
		if(n != 0)
			return true;
		else
			return false;
	}





	@Override
	public Hospital fetchHospital(String hospName) {
		TypedQuery<Hospital> query = em.createQuery("select hospital from Hospital hospital where hospital.hospitalName = :hospName", Hospital.class);
		query.setParameter("hospName", hospName);
		Hospital hospital = query.getSingleResult();
		System.out.println("query.getSingleResult()---->"+hospital);
		return hospital;
	}


	@Override
	public List<Hospital> ah() {
		TypedQuery<Hospital> query = em.createQuery("select hospital from Hospital hospital ", Hospital.class);
//		query.setParameter("hospName", hospName);
//		hospital1 = query.getResultList();
//		System.out.println(a);
		Hospital hospital1 = null;
//		hospital = query.getSingleResult();
		List<Hospital> listOfHosp = query.getResultList();
		for (Hospital entity : listOfHosp) {
		    // do something useful with entity;
			System.out.println("entity.getHospitalName()---->"+entity.getHospitalName());
			System.out.println(entity.getVerifiedInd());
			System.out.println("fetchHospital(entity.getHospitalName())---->"+fetchHospital(entity.getHospitalName())) ;
			hospital1 = fetchHospital(entity.getHospitalName());
			
		}
		//hospital1 = query.getSingleResult();
		return listOfHosp;	
	}


	@Override
	public Hospital hospView(String hospMail) {
		TypedQuery<HospitalAdmin> query = em.createQuery("select ha from HospitalAdmin ha where ha.personnelEmail = :hospMail", HospitalAdmin.class);
		query.setParameter("hospMail", hospMail);
		HospitalAdmin hospital = query.getSingleResult();
		System.out.println("query.getSingleResult()---->"+hospital.getHospital().getAdmin());
		return hospital.getHospital();
	}


	@Override
	public boolean Register(String hosp) {
		// TODO Auto-generated method stub
		return false;
	}



	

	


//	@Override
//	public String allHospitals(String hospital) {
//		// TODO Auto-generated method stub
//		TypedQuery<Hospital> query = em.createQuery("select hospital from Hospital hospital where hospital.hospitalName = :name", Hospital.class);
//		//Hospital admin = query.getSingleResult();
//		query.setParameter("name", hospital);
//			System.out.println(query.getSingleResult());
//		
//		//System.out.println(query.getFirstResult());
//		return null;
//	}





	



	
	

}