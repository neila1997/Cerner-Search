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
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Table(name="site")
@Entity
public class HospitalSite {
	
	public HospitalSite() {
	}
	
	public HospitalSite(String siteName, Date ceaseDate, Date createdDate, Hospital hospital,
			HospitalLocation location) {
		super();
		this.siteName = siteName;
		this.ceaseDate = ceaseDate;
		this.createdDate = createdDate;
		this.hospital = hospital;
		this.location = location;
	}

	@Id
	@SequenceGenerator(name = "site_seq", sequenceName = "system.site_seq", allocationSize = 1)
	@GeneratedValue(generator = "system.site_seq", strategy = GenerationType.AUTO)
	@Column(name="site_id")
	private int id;
	
	@Column(name="site_name")
	private String siteName;
	
	@Column(name="site_cease_date")
	private Date ceaseDate;
	
	@Column(name="site_created_date")
	private Date createdDate;

	@OneToOne(cascade = {CascadeType.PERSIST}, fetch = FetchType.LAZY)
	@JoinColumn(name="site_hosp_id")
	@JsonBackReference(value="hosp_site")
	private Hospital hospital;

	@OneToOne(cascade = {CascadeType.PERSIST})
	@JoinColumn(name="site_loc_id")
	@JsonManagedReference(value="loc_site")
	private HospitalLocation location;
	
	@OneToOne(mappedBy="hospitalSite", fetch = FetchType.LAZY)
//	@JsonBackReference(value="cont_site")
	private ContactInfo contact;

	public String getSiteName() {
		return siteName;
	}

	public void setSiteName(String siteName) {
		this.siteName = siteName;
	}

	public Date getCeaseDate() {
		return ceaseDate;
	}

	public void setCeaseDate(Date ceaseDate) {
		this.ceaseDate = ceaseDate;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Hospital getHospital() {
		return hospital;
	}

	public void setHospital(Hospital hospital) {
		this.hospital = hospital;
	}

	public HospitalLocation getLocation() {
		return location;
	}

	public void setLocation(HospitalLocation location) {
		this.location = location;
	}

	@Override
	public String toString() {
		return "HospitalSite [id=" + id + ", siteName=" + siteName + ", ceaseDate=" + ceaseDate + ", createdDate="
				+ createdDate + "]";
	}
	
}
