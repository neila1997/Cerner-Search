package com.cerner.pran.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;

@Table(name="hospital")
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Hospital {
	
	public Hospital() {
	}

	public Hospital(String hospitalName, String hospitalRegistrationNo, String hospitalAccredition, String hospitalType,
			Date hospitalCreateDate, Date hospitalCeaseDate) {
		super();
		this.hospitalName = hospitalName;
		this.hospitalRegistrationNo = hospitalRegistrationNo;
		this.hospitalAccredition = hospitalAccredition;
		this.hospitalType = hospitalType;
		this.hospitalCreateDate = hospitalCreateDate;
		this.hospitalCeaseDate = hospitalCeaseDate;
	}

	@Id()
	@SequenceGenerator(name = "hosp_seq", sequenceName = "system.hospital_seq",allocationSize = 1)
	@GeneratedValue(generator = "system.hospital_seq", strategy = GenerationType.AUTO)
	@Column(name="hosp_id")
	private int id;

	@Column(name="hosp_name")
	private String hospitalName;
	
	@Column(name="hosp_reg_no")
	private String hospitalRegistrationNo;
	
	@Column(name="hosp_accred")
	private String hospitalAccredition;

	@Column(name="hosp_type")
	private String hospitalType;

	@Column(name="hosp_created_date")
	private Date hospitalCreateDate;

	@Column(name="hosp_cease_date")
	private Date hospitalCeaseDate;
	
	@Column(name="verified_ind")
	private int verifiedInd;
	
	@OneToOne(mappedBy = "hospital")
	@JsonManagedReference(value = "hosp_admin")
//	@JsonProperty("admin")
	private HospitalAdmin admin;

	@OneToOne(mappedBy = "hospital")
	@JsonManagedReference(value = "hosp_contact")
//	@JsonProperty("contact")
	private ContactInfo contact;
	
	@OneToOne(mappedBy = "hospital")
	@JsonManagedReference(value="hosp_site")
//	@JsonProperty("site")
	private HospitalSite site;

	public String getHospitalName() {
		return hospitalName;
	}

	public void setHospitalName(String hospitalName) {
		this.hospitalName = hospitalName;
	}

	public String getHospitalRegistrationNo() {
		return hospitalRegistrationNo;
	}

	public void setHospitalRegistrationNo(String hospitalRegistrationNo) {
		this.hospitalRegistrationNo = hospitalRegistrationNo;
	}

	public String getHospitalAccredition() {
		return hospitalAccredition;
	}

	public void setHospitalAccredition(String hospitalAccredition) {
		this.hospitalAccredition = hospitalAccredition;
	}

	public String getHospitalType() {
		return hospitalType;
	}

	public void setHospitalType(String hospitalType) {
		this.hospitalType = hospitalType;
	}

	public Date getHospitalCreateDate() {
		return hospitalCreateDate;
	}

	public void setHospitalCreateDate(Date hospitalCreateDate) {
		this.hospitalCreateDate = hospitalCreateDate;
	}

	public Date getHospitalCeaseDate() {
		return hospitalCeaseDate;
	}

	public void setHospitalCeaseDate(Date hospitalCeaseDate) {
		this.hospitalCeaseDate = hospitalCeaseDate;
	}

	public HospitalAdmin getAdmin() {
		return admin;
	}

	public void setAdmin(HospitalAdmin admin) {
		this.admin = admin;
	}

	public ContactInfo getContact() {
		return contact;
	}

	public void setContact(ContactInfo contact) {
		this.contact = contact;
	}

	public HospitalSite getSite() {
		return site;
	}

	public void setSite(HospitalSite site) {
		this.site = site;
	}
	
	public int getVerifiedInd() {
		return verifiedInd;
	}

	public void setVerifiedInd(int verifiedInd) {
		this.verifiedInd = verifiedInd;
	}

	@Override
	public String toString() {
		return "Hospital [id=" + id + ", hospitalName=" + hospitalName + ", hospitalRegistrationNo="
				+ hospitalRegistrationNo + ", hospitalAccredition=" + hospitalAccredition + ", hospitalType="
				+ hospitalType + ", hospitalCreateDate=" + hospitalCreateDate + ", hospitalCeaseDate="
				+ hospitalCeaseDate + "]";
	}
	
}
