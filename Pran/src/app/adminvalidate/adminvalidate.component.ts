import { HttpClient } from '@angular/common/http';
import { Component, ComponentFactoryResolver, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Admin } from '../models/admin/admin';
import { Contact } from '../models/contact/contact';
import { Hospital } from '../models/hospital/hospital';
import { Site } from '../models/site/site';
import { DataService } from '../service/data/data.service';

@Component({
  selector: 'app-adminvalidate',
  templateUrl: './adminvalidate.component.html',
  styleUrls: ['./adminvalidate.component.css']
})
export class AdminvalidateComponent implements OnInit {
  public Hospital :Hospital[] 

  constructor(public http: HttpClient, public data:DataService, public router:Router) { 

    // this.Hospital.admin = new Admin()
    // this.Hospital.contact = new Contact()
    // this.Hospital.site = new Site()
    
    this.http.get<Hospital[]>("http://localhost:8070/hlist",{responseType:'json'}).subscribe(
      
    x => {
        
        this.Hospital=x
        console.log(this.Hospital)
      })

      // this.http.get<Hospital>("http://localhost:8070/testapi").subscribe(
      // x => {
      //   this.Hospital = x
      //   , console.log(x)
      // })
      
  }

  ngOnInit(): void {
  }

test(){
  alert("test")
  var reg= "hospitaldetails"
  window.open("http://localhost:4200/"+reg);
}
viewall(){
  alert("test");

}

checkHospitalDetails(hospital:Hospital){
  // console.log(hospital)
  this.data.subject.next(hospital)
  this.router.navigate(['hospitaldetails'])
}
}
