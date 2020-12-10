import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Router, RouterLink } from '@angular/router';
import { FooterComponent } from '../footer/footer.component';

class Login {
  email: string
  password: string
}

@Component({
  selector: 'app-cerner-admin-login',
  templateUrl: './cerner-admin-login.component.html',
  styleUrls: ['./cerner-admin-login.component.css']
})
export class CernerAdminLoginComponent implements OnInit {

  constructor(public http: HttpClient, public router: Router) { }


  ngOnInit(): void {
  }

  errorMessage: string
  errorFlag = false
  login: Login = new Login()

  verifyLogin() {
    this.http.post<Response>('http://localhost:8070/cernadminlogin', this.login).subscribe(x => {
      console.log(x.status)
      localStorage.setItem("CernerAdminUser", this.login.email)
      this.router.navigate(['adminvalidate'])
    }, y => {
      console.log(y)
      this.errorFlag = true
      this.errorMessage = "Invalid Username/Password"
    })

  }
}
