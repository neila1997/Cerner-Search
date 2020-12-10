import { Component, OnInit } from '@angular/core';
import { Title } from '@angular/platform-browser';
import { Router } from '@angular/router';
import { Site } from '../models/site/site';
import { Address } from '../models/address/address';
import { Hospital } from '../models/hospital/hospital';
import { Admin } from '../models/admin/admin';
import { Contact } from '../models/contact/contact';
import { RegisterService } from '../service/register/register.service';
import { FormArray, FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { HttpClient } from '@angular/common/http';
import Swal from 'sweetalert2'

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
class contact {
  primaryEmailId: string = '';
}

class admin {
  personnelEmail: string = '';
}


@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.css']
})
export class RegistrationComponent implements OnInit {
  test: string = ""
  hospitalType: Array<string> = ["Hospital", "Dispensary", "Community Health Centre", "Sub Centre", "Poly Clinic", "Clinic", "Nursing Home", "Medical College / Institute", "Others"]
  form: FormGroup
  public Hospital = new Hospital()
  checkArray: FormArray
  telephone = new telephone()
  contact = new contact()
  admin = new admin()
  personnelEmailFlag = true;

  constructor(public title: Title, public router: Router, public http: HttpClient, public register: RegisterService, public fb: FormBuilder) {
    this.title.setTitle("Register with us")
    this.Hospital.admin = new Admin()
    this.Hospital.contact = new Contact()
    this.Hospital.site = new Site()
    this.Hospital.site.location = new Address()

    this.form = this.fb.group({
      checkArray: this.fb.array([], [Validators.required])
    })

  }

  ngOnInit(): void {
    this.checkArray = this.form.get('checkArray') as FormArray;
  }



  get_details() {
    var pincode = this.Hospital.site.location.pincode
    if (pincode == '') {
      this.Hospital.site.location.state = '';
      this.Hospital.site.location.district = '';
      Swal.fire({
        position: 'center',
        icon: 'warning',
        title: 'Please enter pincode',
        showConfirmButton: false,
        showCloseButton: true
      })
      var options = "Select Area";
      options = "<option> Select Area </option>";
      document.getElementById("area").innerHTML = options;
    }
    else {
      //fetching the state and district through an api call
      this.Hospital = this.register.getDetails(this.Hospital)

    }
  }

  registerHospital() {
    //Concatenating the phone numbers
    this.Hospital.contact.ambulanceNo = this.telephone.ambulanceStd.concat('-', this.telephone.ambulanceNo);
    this.Hospital.contact.bloodBankNo = this.telephone.bloodbankStd.concat('-', this.telephone.bloodbankNo);
    this.Hospital.contact.tollfreeNo = this.telephone.tollfreeStd.concat('-', this.telephone.tollfreeNo);
    this.Hospital.contact.emergencyNo = this.telephone.emergencyStd.concat('-', this.telephone.emergencyNo);
    this.Hospital.contact.landlineNo = this.telephone.landlineStd.concat('-', this.telephone.landlineNo);
    this.Hospital.hospitalType = ''
    if (this.checkArray.length > 0) {
      this.checkArray.controls.forEach((item: FormControl) => {
        if (this.Hospital.hospitalType === '')
          this.Hospital.hospitalType = this.Hospital.hospitalType.concat(item.value)
        else
          this.Hospital.hospitalType = this.Hospital.hospitalType.concat(", ", item.value)
      })
    }
    // posting the data to the database
    this.register.registerHospital(this.Hospital).subscribe(x => {
      document.getElementById("openModalButton").click();
    })

  }

  mapType(e) {

    if (e.target.checked) {
      this.checkArray.push(new FormControl(e.target.value));
    } else {
      let i: number = 0;
      this.checkArray.controls.forEach((item: FormControl) => {
        if (item.value == e.target.value) {
          this.checkArray.removeAt(i);
          return;
        }
        i++;
      });
    }
  }

  validate_pemail() {
    var email = this.Hospital.contact.primaryEmailId;

    if (email != '') {
      this.register.validate_email(email).subscribe(jsonObject => {
        var json = JSON.parse(jsonObject)
        if (json.smtp_check != true) {
          this.Hospital.admin.personnelEmail = ''
          Swal.fire({
            position: 'center',
            icon: 'warning',
            title: 'Please enter valid Email ID',
            showConfirmButton: false,
            showCloseButton: true
          })
        }
      })
    }
  }

  validate_peremail() {
    var peremail = this.Hospital.admin.personnelEmail;
    if (peremail != '') {
      this.register.validate_email(peremail).subscribe(jsonObject => {
        var json = JSON.parse(jsonObject)
        if (json.smtp_check != true) {
          this.Hospital.admin.personnelEmail = ''
          Swal.fire({
            position: 'center',
            icon: 'warning',
            title: 'Please enter valid Email ID',
            showConfirmButton: false,
            showCloseButton: true
          })
        }
      })
    }
  }

  validate_landline() {
    this.Hospital.contact.landlineNo = this.telephone.landlineNo
    var landlineno = this.Hospital.contact.landlineNo;
    if (landlineno != '') {
      this.Hospital = this.register.validate_landline(this.Hospital)
    }
    else {
      alert("test")
    }
  }

  validate_mobile() {
    var mobno = this.Hospital.contact.mobileNo;
    if (mobno != '') {
      this.Hospital = this.register.validate_mobile(this.Hospital)
    }
    else {
      alert("test")
    }
  }

  redirect() {
    alert("here");

    window.location.replace("http://localhost:4200/hospitaldetails");

  }

}
