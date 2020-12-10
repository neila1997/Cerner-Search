package com.cerner.pran.model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Table(name="Cerner_Admin")
@Entity
public class CernerAdmin {
	
	public CernerAdmin() {
	}

	public CernerAdmin(String personnelName, String personnelEmail, String personnelNumber, String personnelPassword, Hospital hospital) {
		super();
		this.personnelName = personnelName;
		this.personnelEmail = personnelEmail;
		this.personnelNumber = personnelNumber;
		this.personnelPassword = personnelPassword;
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
		return "CernerAdmin [id=" + id + ", personnelName=" + personnelName + ", personnelEmail=" + personnelEmail
				+ ", personnelNumber=" + personnelNumber + ", personnelPassword=" + personnelPassword + "]";
	}
	
}
