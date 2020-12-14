import { Component, Input, OnInit } from '@angular/core';
import { Hospital } from '../models/hospital/hospital';
import { Contact } from '../models/contact/contact';
import { HttpClient } from '@angular/common/http';
import { DataService } from '../service/data/data.service';
import { ActivatedRoute, Router } from '@angular/router';
import { Admin } from '../models/admin/admin';
import { Site } from '../models/site/site';
import { Address } from '../models/address/address';


class telephone {
  landlineNo: string = '';
  landlineStd: string = '';
  emergencyNo: string = '';
  emergencyStd: string = '';
  ambulanceNo: string = '';
  ambulanceStd: string = '';
  bloodbankNo: string = '';
  bloodbankStd: string = '';
  tollfreeNo: string = '';
  tollfreeStd: string = '';
}

export class hospType{
  type:string
  checked:boolean
}

class Login {
  email: string
  password: string
}
@Component({
  selector: 'app-hospital-view',
  templateUrl: './hospital-view.component.html',
  styleUrls: ['./hospital-view.component.css']
})
export class HospitalViewComponent implements OnInit {
  public Hospital = new Hospital()
  public telephone = new telephone()
  public admin  = new Admin()
  public login = new Login()
  
  hospitalType: Array<string> = ["Hospital", "Dispensary", "Community Health Centre", "Sub Centre", "Poly Clinic", "Clinic", "Nursing Home", "Medical College / Institute", "Others"]
  type: hospType[] = []

  // @Input('email') public email;  

  constructor(public http: HttpClient, public data:DataService, public router:Router,public actroute:ActivatedRoute) { 
    this.Hospital.admin = new Admin()
    this.Hospital.contact = new Contact()
    this.Hospital.site = new Site()
    this.Hospital.site.location = new Address()
    
    //alert(email)
    // this.http.get<Hospital>("http://localhost:8070/hospview/",{responseType:'json'})
     var email = this.actroute.snapshot.params['name']//'arpitlall20@gmail.com'
     alert(email)
     console.log(email)
    // var email1= email.replace('%2F','/')
    // var email2= email1.replace('%3F','=')
    // console.log(email2)
    this.http.get<Hospital>("http://localhost:8070/hospview?email="+email,{responseType:'json'}).subscribe(
      
    x => {
        this.Hospital=x
        console.log(x)
      })
  }

  ngOnInit(): void {

  }

}
