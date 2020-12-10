package com.cerner.pran.model;


import java.util.Date;

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
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Table(name="contact_info")
@Entity
public class ContactInfo {
	
	public ContactInfo() {
	}

	public ContactInfo(String mobileNo, String landlineNo, String emergencyNo, String ambulanceNo, String bloodBankNo,
			String tollfreeNo, String primaryEmailId, String secondaryEmailId, String websiteUrl, Date createdDate,
			Date ceasedDate, Hospital hospital, HospitalSite hospitalSite) {
		super();
		this.mobileNo = mobileNo;
		this.landlineNo = landlineNo;
		this.emergencyNo = emergencyNo;
		this.ambulanceNo = ambulanceNo;
		this.bloodBankNo = bloodBankNo;
		this.tollfreeNo = tollfreeNo;
		this.primaryEmailId = primaryEmailId;
		this.secondaryEmailId = secondaryEmailId;
		this.websiteUrl = websiteUrl;
		this.createdDate = createdDate;
		this.ceasedDate = ceasedDate;
		this.hospital = hospital;
		this.hospitalSite = hospitalSite;
	}



	@Id()
	@SequenceGenerator(name = "contact_seq", sequenceName = "system.contact_seq", allocationSize = 1)
	@GeneratedValue(generator = "system.contact_seq", strategy = GenerationType.AUTO)
	@Column(name="cont_id")
	private int id;

	@Column(name="cont_hosp_mobile")
	private String mobileNo;

	@Column(name="cont_hosp_landline")
	private String landlineNo;

	@Column(name="cont_hosp_emergency")
	private String emergencyNo;

	@Column(name="cont_ambulance_no")
	private String ambulanceNo;
	
	@Column(name="cont_bloodbank_no")
	private String bloodBankNo;

	@Column(name="cont_tollfree_no")
	private String tollfreeNo;

	@Column(name="cont_primarymail_id")
	private String primaryEmailId;

	@Column(name="cont_secondarymail_id")
	private String secondaryEmailId;
	
	@Column(name="cont_website")
	private String websiteUrl;
	
	@Column(name="cont_created_date")
	private Date createdDate;
	
	@Column(name="cont_cease_date")
	private Date ceasedDate;
	
	@OneToOne(cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
	@JoinColumn(name="cont_hosp_id")
	@JsonBackReference(value = "hosp_contact")
	private Hospital hospital;
	
	@OneToOne(cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
	@JoinColumn(name="cont_site_id")
	@JsonIgnore()
	private HospitalSite hospitalSite ;

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public String getLandlineNo() {
		return landlineNo;
	}

	public void setLandlineNo(String landlineNo) {
		this.landlineNo = landlineNo;
	}

	public String getEmergencyNo() {
		return emergencyNo;
	}

	public void setEmergencyNo(String emergencyNo) {
		this.emergencyNo = emergencyNo;
	}

	public String getAmbulanceNo() {
		return ambulanceNo;
	}

	public void setAmbulanceNo(String ambulanceNo) {
		this.ambulanceNo = ambulanceNo;
	}

	public String getBloodBankNo() {
		return bloodBankNo;
	}

	public void setBloodBankNo(String bloodBankNo) {
		this.bloodBankNo = bloodBankNo;
	}

	public String getTollfreeNo() {
		return tollfreeNo;
	}

	public void setTollfreeNo(String tollfreeNo) {
		this.tollfreeNo = tollfreeNo;
	}

	public String getPrimaryEmailId() {
		return primaryEmailId;
	}

	public void setPrimaryEmailId(String primaryEmailId) {
		this.primaryEmailId = primaryEmailId;
	}

	public String getSecondaryEmailId() {
		return secondaryEmailId;
	}

	public void setSecondaryEmailId(String secondaryEmailId) {
		this.secondaryEmailId = secondaryEmailId;
	}

	public String getWebsiteUrl() {
		return websiteUrl;
	}

	public void setWebsiteUrl(String websiteUrl) {
		this.websiteUrl = websiteUrl;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getCeasedDate() {
		return ceasedDate;
	}

	public void setCeasedDate(Date ceasedDate) {
		this.ceasedDate = ceasedDate;
	}

	public Hospital getHospital() {
		return hospital;
	}

	public void setHospital(Hospital hospital) {
		this.hospital = hospital;
	}

	public HospitalSite getHospitalSite() {
		return hospitalSite;
	}

	public void setHospitalSite(HospitalSite hospitalSite) {
		this.hospitalSite = hospitalSite;
	}

	@Override
	public String toString() {
		return "ContactInfo [id=" + id + ", mobileNo=" + mobileNo + ", landlineNo=" + landlineNo + ", emergencyNo="
				+ emergencyNo + ", ambulanceNo=" + ambulanceNo + ", bloodBankNo=" + bloodBankNo + ", tollfreeNo="
				+ tollfreeNo + ", primaryEmailId=" + primaryEmailId + ", secondaryEmailId=" + secondaryEmailId
				+ ", websiteUrl=" + websiteUrl + ", createdDate=" + createdDate + ", ceasedDate=" + ceasedDate + "]";
	}
	
	
}
