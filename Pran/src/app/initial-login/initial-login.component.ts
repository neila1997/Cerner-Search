import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { AbstractControl, ControlContainer, Form, FormControl, FormGroup, FormBuilder } from '@angular/forms';
import { Title } from '@angular/platform-browser';
import { Admin } from '../models/admin/admin';
import { DataService } from '../service/data/data.service';
import { RegisterService } from '../service/register/register.service';

@Component({
  selector: 'app-initial-login',
  templateUrl: './initial-login.component.html',
  styleUrls: ['./initial-login.component.css']
})
export class InitialLoginComponent implements OnInit {

  headerText: string
  credentialVerify = true;
  passwordFlag = false;
  admin: Admin
  verificationForm: FormGroup

    ;

  constructor(public data: DataService, public register: RegisterService, public http: HttpClient, public title: Title, public formBuilder: FormBuilder) {
    this.verificationForm = this.formBuilder.group({
      password1: ''
      , password2: ''
    })
    this.headerText = 'Verify your Credentials'
    this.title.setTitle('Verify your Credentials')
  }

  ngOnInit(): void {
    this.admin = new Admin()
    this.data.admin.subscribe(object => {
      this.admin = object
      this.admin.personnelPassword = ''
    })

  }

  // async verifyLogin() {
  //   (await this.register.verifyLogin(this.hospital)).subscribe(x => {
  //     console.log(x)
  //     this.credentialVerify = !x;
  //     this.hospital.admin.personnelPassword = ''
  //     if (this.credentialVerify === false) {
  //       this.headerText = 'Update your Password'
  //       this.title.setTitle('Update your Password')
  //     }
  //   })
  // }

  comparePasswords() {
    if(this.verificationForm.get('password1').value===this.verificationForm.get('password2').value)
      this.passwordFlag = false
    else  
      this.passwordFlag = true
  }

  passwordCheck(){
    return (this.verificationForm.get('password1').value==='')||(this.verificationForm.get('password1').value!=this.verificationForm.get('password2').value)
  }

  updatePassword(){
    this.register.updatePassword(this.admin).subscribe(x=>console.log(x))
  }

}



