import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Admin } from '../models/admin/admin';
import { DataService } from '../service/data/data.service';

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
        this.router.navigate([''])
    }, y => {
      console.log(y)
      this.errorFlag = true
      this.errorMessage = "Invalid Username/Password"
    })
  }
}
