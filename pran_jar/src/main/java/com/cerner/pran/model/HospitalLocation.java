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

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name="location")
public class HospitalLocation {
	
	public HospitalLocation(){
	}

	public HospitalLocation(String address, String pincode, String state, String district, String town,
			String subDistrict, int longitude, int latitude, Date ceaseDate, Date createdDate, HospitalSite site, String area) {
		super();
		this.address = address;
		this.pincode = pincode;
		this.state = state;
		this.district = district;
		this.town = town;
		this.subDistrict = subDistrict;
		this.longitude = longitude;
		this.latitude = latitude;
		this.ceaseDate = ceaseDate;
		this.createdDate = createdDate;
		this.site = site;
		this.area = area;
	}


	@Id
	@SequenceGenerator(name = "location_seq", sequenceName = "system.location_seq", allocationSize = 1)
	@GeneratedValue(generator = "system.location_seq", strategy = GenerationType.AUTO)
	@Column(name="loc_id")
	private int id;
	
	@Column(name="loc_address")
	private String address;
	
	@Column(name="loc_zip_code")
	private String pincode;

	@Column(name="loc_state")
	private String state;
	
	@Column(name="loc_district")
	private String district;

	@Column(name="loc_town")
	private String town;

	@Column(name="loc_sub_district")
	private String subDistrict;
	
	@Column(name="loc_longitude")
	private int longitude;
	
	@Column(name="loc_latitude")
	private int latitude;

	@Column(name="loc_ceased_date")
	private Date ceaseDate;
	
	@Column(name="loc_created_date")
	private Date createdDate;
	
	@Column(name="loc_area")
	private String area;
	
	@Column(name="loc_verified_ind")
	private int loc_verified_ind;
	
	@OneToOne(mappedBy = "location", fetch = FetchType.LAZY)
	@JsonBackReference(value="loc_site")
	private HospitalSite site;
	
	public int getLocverifiedInd() {
		return loc_verified_ind;
	}

	public void setLocverifiedInd(int loc_verified_ind) {
		this.loc_verified_ind = loc_verified_ind;
	}
	
	public String getArea() {
		return area;
	}
	
	public void setArea(String area) {
		this.area = area;
	}
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPincode() {
		return pincode;
	}

	public void setPincode(String pincode) {
		this.pincode = pincode;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getTown() {
		return town;
	}

	public void setTown(String town) {
		this.town = town;
	}

	public String getSubDistrict() {
		return subDistrict;
	}

	public void setSubDistrict(String subDistrict) {
		this.subDistrict = subDistrict;
	}

	public int getLongitude() {
		return longitude;
	}

	public void setLongitude(int longitude) {
		this.longitude = longitude;
	}

	public int getLatitude() {
		return latitude;
	}

	public void setLatitude(int latitude) {
		this.latitude = latitude;
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

	public HospitalSite getSite() {
		return site;
	}

	public void setSite(HospitalSite site) {
		this.site = site;
	}

	@Override
	public String toString() {
		return "HospitalLocation [id=" + id + ", address=" + address + ", pincode=" + pincode + ", state=" + state
				+ ", district=" + district + ", town=" + town + ", subDistrict=" + subDistrict + ", longitude="
				+ longitude + ", latitude=" + latitude + ", ceaseDate=" + ceaseDate + ", createdDate=" + createdDate
				+ ", area=" + area + "]";
	}
	
}
