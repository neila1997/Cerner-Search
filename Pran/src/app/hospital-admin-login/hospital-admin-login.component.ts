import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Admin } from '../models/admin/admin';
import { DataService } from '../service/data/data.service';
import { Hospital } from '../models/hospital/hospital';

class Login {
  email: string
  password: string
}

@Component({
  selector: 'app-hospital-admin-login',
  templateUrl: './hospital-admin-login.component.html',
  styleUrls: ['./hospital-admin-login.component.css']
})

export class HospitalAdminLoginComponent implements OnInit {
  errorMessage: string
  errorFlag = false
  login: Login = new Login()
  public Hospital = new Hospital()
 
    
  constructor(public http: HttpClient, public data: DataService, public router: Router) { }
    
  ngOnInit(): void {
    
  }

  verifyLogin() {
    
    console.log("TEST")
    this.http.post<Admin>('http://localhost:8070/adminlogin', this.login).subscribe(x => {
      this.data.admin.next(x)
      if (x.lastLogin === null)
        this.router.navigate(['initiallogin'])
      else
        this.router.navigate(['adminhome'])
      //  //alert(this.email1)
      //  this.router.navigate(['hospitalview/'+this.login.email]) 
    //     this.http.get<Hospital>("http://localhost:8070/hospview?email="+this.login.email,{responseType:'json'}).subscribe(
          
    // x => {
    //     this.router.navigate(['hospitalview']) 
    //     this.Hospital=x
    //     console.log(x)
        
    //   })
      
    }, y => {
      console.log(y)
      this.errorFlag = true
      this.errorMessage = "Invalid Username/Password"
    })
  }
}
