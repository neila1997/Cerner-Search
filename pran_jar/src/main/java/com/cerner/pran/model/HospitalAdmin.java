package com.cerner.pran.model;

import java.sql.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Table(name="Hospital_Admin")
@Entity
public class HospitalAdmin {
	
	public HospitalAdmin() {
	}

	public HospitalAdmin(String personnelName, String personnelEmail, String personnelNumber, String personnelPassword, Hospital hospital) {
		super();
		this.personnelName = personnelName;
		this.personnelEmail = personnelEmail;
		this.personnelNumber = personnelNumber;
		this.personnelPassword = personnelPassword;
		this.hospital = hospital;
	}

	@Id
	@SequenceGenerator(name = "admin_seq", allocationSize = 1, sequenceName = "system.admin_seq")
	@GeneratedValue(generator = "system.admin_seq", strategy = GenerationType.AUTO)
	@Column(name = "admin_id")
	public int id;
	
	@Column(name="admin_name")
	private String personnelName;
	
	@Column(name="admin_email")
	private String personnelEmail;

	@Column(name="admin_phone")
	private String personnelNumber;

	@Column(name="admin_password")
	private String personnelPassword;
	
	@Column(name="admin_last_login")
	private Date lastLoginDate;

	@OneToOne(cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
	@JoinColumn(name="admin_hosp_id")
	@JsonBackReference(value = "hosp_admin")
	private Hospital hospital;

	public String getPersonnelName() {
		return personnelName;
	}

	public void setPersonnelName(String personnelName) {
		this.personnelName = personnelName;
	}

	public String getPersonnelEmail() {
		return personnelEmail;
	}

	public void setPersonnelEmail(String personnelEmail) {
		this.personnelEmail = personnelEmail;
	}

	public String getPersonnelNumber() {
		return personnelNumber;
	}

	public void setPersonnelNumber(String personnelNumber) {
		this.personnelNumber = personnelNumber;
	}

	public Hospital getHospital() {
		return hospital;
	}

	public void setHospital(Hospital hospital) {
		this.hospital = hospital;
	}
	
	public String getPersonnelPassword() {
		return personnelPassword;
	}

	public void setPersonnelPassword(String personnelPassword) {
		this.personnelPassword = personnelPassword;
	}

	
	public Date getLastLogin() {
		return lastLoginDate;
	}

	public void setLastLoginDate(Date lastLoginDate) {
		this.lastLoginDate = lastLoginDate;
	}

	@Override
	public String toString() {
		return "HospitalAdmin [id=" + id + ", personnelName=" + personnelName + ", personnelEmail=" + personnelEmail
				+ ", personnelNumber=" + personnelNumber + ", personnelPassword=" + personnelPassword
				+ ", lastLoginDate=" + lastLoginDate + ", hospital=" + hospital + "]";
	}
	
	
}
