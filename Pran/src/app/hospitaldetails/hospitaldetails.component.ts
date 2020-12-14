import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Address } from '../models/address/address';
import { Admin } from '../models/admin/admin';
import { Contact } from '../models/contact/contact';
import { Hospital } from '../models/hospital/hospital';
import { Site } from '../models/site/site';
import { DataService } from '../service/data/data.service';
import Swal from 'sweetalert2';
import { stringify } from 'querystring';
import { element } from 'protractor';

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

@Component({
  selector: 'app-hospitaldetails',
  templateUrl: './hospitaldetails.component.html',
  styleUrls: ['./hospitaldetails.component.css']
})
export class HospitaldetailsComponent implements OnInit {

  hospitalType: Array<string> = ["Hospital", "Dispensary", "Community Health Centre", "Sub Centre", "Poly Clinic", "Clinic", "Nursing Home", "Medical College / Institute", "Others"]
  type: hospType[] = []
  validatedBool = false
  public Hospital = new Hospital()
  public telephone = new telephone()
  constructor(public http: HttpClient, public router: Router, public data: DataService) {
    this.Hospital.admin = new Admin()
    this.Hospital.contact = new Contact()
    this.Hospital.site = new Site()
    this.Hospital.site.location = new Address()

    this.data.subject.subscribe(hospital => {
      this.Hospital = hospital
      var number: string[]
      if (this.Hospital.contact != null) {
        number = this.Hospital.contact.landlineNo.split('-')
        this.telephone.landlineStd = number[0]
        this.telephone.landlineNo = number[1]

        number = this.Hospital.contact.ambulanceNo.split('-')
        this.telephone.ambulanceStd = number[0]
        this.telephone.ambulanceNo = number[1]

        number = this.Hospital.contact.bloodBankNo.split('-')
        this.telephone.bloodbankStd = number[0]
        this.telephone.bloodbankNo = number[1]

        number = this.Hospital.contact.emergencyNo.split('-')
        this.telephone.emergencyStd = number[0]
        this.telephone.emergencyNo = number[1]

        number = this.Hospital.contact.tollfreeNo.split('-')
        this.telephone.tollfreeStd = number[0]
        this.telephone.tollfreeNo = number[1]
      }

        this.Hospital.institutionType = "Government/Public"
        var hp :string[] = ['']
        if(this.Hospital.hospitalType != null){
          console.log("TEST")
        var hp = this.Hospital.hospitalType.split(',')
        }
        
        var j = 0;
        for(let h1 of this.hospitalType){
          this.type[j] = new hospType()
          for(let h2 of hp){
            if(h1.trim() === h2.trim()){
              this.type[j].type = h1 
              this.type[j].checked = true 
              break
            }
            else{
              this.type[j].type = h1 
              this.type[j].checked = false 
            }
          }
          j++;
          
        }
        console.log(this.type)
        console.log(hp)

      if (this.Hospital.hospitalType != '') {
        number = this.Hospital.hospitalType.split(',')
        console.log(number)
        for (var i of number) {
          // event.target. = true
          console.log(this.Hospital.institutionType)
          console.log(document.getElementById(this.Hospital.institutionType))
        }
      }
      // console.log("test")
    }
    )
    console.log(this.Hospital)
    if (this.Hospital === null)
      this.router.navigate(['adminvalidate'])
    // this.http.post<Hospital>("http://localhost:8070/testapi", Hospital.).subscribe(
    //   x => {
    //     this.Hospital=x
    //     , console.log(x)
    //   })
  }

  ngOnInit(): void {

  }
  check(event) {
    alert("working");
    var a = <HTMLInputElement>document.getElementById('firstcb');

    var b = <HTMLInputElement>document.getElementById('secondcb');

    var c = <HTMLInputElement>document.getElementById('thirdcb');

    var d = <HTMLInputElement>document.getElementById('fourthcb');

    if (a.checked && b.checked && c.checked && d.checked)
      alert("working fine");
    else
      Swal.fire("Kindly Validate all the fields");


  }
  sendback() {
    var a = <HTMLInputElement>document.getElementById('firstcb');
    var b = <HTMLInputElement>document.getElementById('secondcb');
    var c = <HTMLInputElement>document.getElementById('thirdcb');
    var d = <HTMLInputElement>document.getElementById('fourthcb');
    if (a.checked && b.checked && c.checked && d.checked) {
      //alert("all fields have been validated, kindly click on validated ")
      Swal.fire({
        position: 'center',
        icon: 'error',
        title: 'all fields have been validated, kindly click on validate',
        showConfirmButton: false,
        showCloseButton: true
      })
    }
    else {
      this.http.post<boolean>("http://localhost:8070/verifya",this.Hospital.admin.personnelEmail).subscribe(
        
      x=>{
            alert("asd"); 
        }
      )
      Swal.fire({
        position: 'center',
        icon: 'error',
        title: 'Email has been send to the Hospital Admin to re-validate the data.',
        showConfirmButton: false,
        showCloseButton: true
      })
      this.router.navigate(['adminvalidate'])
      if (d.checked) {
        document.getElementById('personnel_name').style.borderColor = "green";
        document.getElementById('personnel_name').style.backgroundColor = "#9ff3b4";
        document.getElementById('personnel_contact').style.borderColor = "green";
        document.getElementById('personnel_contact').style.backgroundColor = "#9ff3b4";
        document.getElementById('personnel_email').style.borderColor = "green";
        document.getElementById('personnel_email').style.backgroundColor = "#9ff3b4";
      }
      else {
        document.getElementById('personnel_name').style.borderColor = "red";
        document.getElementById('personnel_name').style.backgroundColor = "rgb(228, 159, 159)";
        document.getElementById('personnel_contact').style.borderColor = "red";
        document.getElementById('personnel_contact').style.backgroundColor = "rgb(228, 159, 159)";
        document.getElementById('personnel_email').style.borderColor = "red";
        document.getElementById('personnel_email').style.backgroundColor = "rgb(228, 159, 159)";
      }

      if (a.checked) {
        document.getElementById('hospitalName').style.borderColor = "green";
        document.getElementById('hospitalName').style.backgroundColor = "#9ff3b4";
        document.getElementById('radio1').style.borderColor = "green";
        document.getElementById('radio1').style.backgroundColor = "#9ff3b4";
        document.getElementById('accreditation').style.borderColor = "green";
        document.getElementById('accreditation').style.backgroundColor = "#9ff3b4";
        document.getElementById('registrationNo').style.borderColor = "green";
        document.getElementById('registrationNo').style.backgroundColor = "#9ff3b4";
      }
      else {
        document.getElementById('hospitalName').style.borderColor = "red";
        document.getElementById('hospitalName').style.backgroundColor = "rgb(228, 159, 159)";
        document.getElementById('radio1').style.borderColor = "red";
        document.getElementById('radio1').style.backgroundColor = "rgb(228, 159, 159)";
        document.getElementById('accreditation').style.borderColor = "red";
        document.getElementById('accreditation').style.backgroundColor = "rgb(228, 159, 159)";
        document.getElementById('registrationNo').style.borderColor = "red";
        document.getElementById('registrationNo').style.backgroundColor = "rgb(228, 159, 159)";

      }

      if (b.checked) {
        document.getElementById('address').style.borderColor = "green";
        document.getElementById('address').style.backgroundColor = "#9ff3b4";
        document.getElementById('pincode').style.borderColor = "green";
        document.getElementById('pincode').style.backgroundColor = "#9ff3b4";
        document.getElementById('city').style.borderColor = "green";
        document.getElementById('city').style.backgroundColor = "#9ff3b4";
        document.getElementById('state').style.borderColor = "green";
        document.getElementById('state').style.backgroundColor = "#9ff3b4";
        document.getElementById('area').style.borderColor = "green";
        document.getElementById('area').style.backgroundColor = "#9ff3b4";
      }
      else {
        document.getElementById('address').style.borderColor = "red";
        document.getElementById('address').style.backgroundColor = "rgb(228, 159, 159)";
        document.getElementById('pincode').style.borderColor = "red";
        document.getElementById('pincode').style.backgroundColor = "rgb(228, 159, 159)";
        document.getElementById('city').style.borderColor = "red";
        document.getElementById('city').style.backgroundColor = "rgb(228, 159, 159)";
        document.getElementById('state').style.borderColor = "red";
        document.getElementById('state').style.backgroundColor = "rgb(228, 159, 159)";
        document.getElementById('area').style.borderColor = "red";
        document.getElementById('area').style.backgroundColor = "rgb(228, 159, 159)";
      }

      if (c.checked) {
        document.getElementById('hospital_telephone_country').style.borderColor = "green";
        document.getElementById('hospital_telephone_country').style.backgroundColor = "#9ff3b4";
        document.getElementById('hospital_telephone_no').style.borderColor = "green";
        document.getElementById('hospital_telephone_no').style.backgroundColor = "#9ff3b4";
        document.getElementById('hospital_emergency_country').style.borderColor = "green";
        document.getElementById('hospital_emergency_country').style.backgroundColor = "#9ff3b4";
        document.getElementById('hospital_emergency_no').style.borderColor = "green";
        document.getElementById('hospital_emergency_no').style.backgroundColor = "#9ff3b4";
        document.getElementById('hospital_ambulance_country').style.borderColor = "green";
        document.getElementById('hospital_ambulance_country').style.backgroundColor = "#9ff3b4";
        document.getElementById('hospital_ambulance_no').style.borderColor = "green";
        document.getElementById('hospital_ambulance_no').style.backgroundColor = "#9ff3b4";
        document.getElementById('hospital_bb_country').style.borderColor = "green";
        document.getElementById('hospital_bb_country').style.backgroundColor = "#9ff3b4";
        document.getElementById('hospital_bb_no').style.borderColor = "green";
        document.getElementById('hospital_bb_no').style.backgroundColor = "#9ff3b4";
        document.getElementById('hospital_toll_free_country').style.borderColor = "green";
        document.getElementById('hospital_toll_free_country').style.backgroundColor = "#9ff3b4";
        document.getElementById('hospital_toll_free_no').style.borderColor = "green";
        document.getElementById('hospital_toll_free_no').style.backgroundColor = "#9ff3b4";
        document.getElementById('mobile_emergency_no').style.borderColor = "green";
        document.getElementById('mobile_emergency_no').style.backgroundColor = "#9ff3b4";
        document.getElementById('hospital_primary_email').style.borderColor = "green";
        document.getElementById('hospital_primary_email').style.backgroundColor = "#9ff3b4";
        document.getElementById('hospital_secondary_email').style.borderColor = "green";
        document.getElementById('hospital_secondary_email').style.backgroundColor = "#9ff3b4";
        document.getElementById('hospital_url').style.borderColor = "green";
        document.getElementById('hospital_url').style.backgroundColor = "#9ff3b4";
      }
      else {

        document.getElementById('hospital_telephone_country').style.borderColor = "red";
        document.getElementById('hospital_telephone_country').style.backgroundColor = "rgb(228, 159, 159)";
        document.getElementById('hospital_telephone_no').style.borderColor = "red";
        document.getElementById('hospital_telephone_no').style.backgroundColor = "rgb(228, 159, 159)";
        document.getElementById('hospital_emergency_country').style.borderColor = "red";
        document.getElementById('hospital_emergency_country').style.backgroundColor = "rgb(228, 159, 159)";
        document.getElementById('hospital_emergency_no').style.borderColor = "red";
        document.getElementById('hospital_emergency_no').style.backgroundColor = "rgb(228, 159, 159)";
        document.getElementById('hospital_ambulance_country').style.borderColor = "red";
        document.getElementById('hospital_ambulance_country').style.backgroundColor = "rgb(228, 159, 159)";
        document.getElementById('hospital_ambulance_no').style.borderColor = "red";
        document.getElementById('hospital_ambulance_no').style.backgroundColor = "rgb(228, 159, 159)";
        document.getElementById('hospital_bb_country').style.borderColor = "red";
        document.getElementById('hospital_bb_country').style.backgroundColor = "rgb(228, 159, 159)";
        document.getElementById('hospital_bb_no').style.borderColor = "red";
        document.getElementById('hospital_bb_no').style.backgroundColor = "rgb(228, 159, 159)";
        document.getElementById('hospital_toll_free_country').style.borderColor = "red";
        document.getElementById('hospital_toll_free_country').style.backgroundColor = "rgb(228, 159, 159)";
        document.getElementById('hospital_toll_free_no').style.borderColor = "red";
        document.getElementById('hospital_toll_free_no').style.backgroundColor = "rgb(228, 159, 159)";
        document.getElementById('mobile_emergency_no').style.borderColor = "red";
        document.getElementById('mobile_emergency_no').style.backgroundColor = "rgb(228, 159, 159)";
        document.getElementById('hospital_primary_email').style.borderColor = "red";
        document.getElementById('hospital_primary_email').style.backgroundColor = "rgb(228, 159, 159)";
        document.getElementById('hospital_secondary_email').style.borderColor = "red";
        document.getElementById('hospital_secondary_email').style.backgroundColor = "rgb(228, 159, 159)";
        document.getElementById('hospital_url').style.borderColor = "red";
        document.getElementById('hospital_url').style.backgroundColor = "rgb(228, 159, 159)";


      }
    }

  }


  filldata() {
    alert("enter");
  }

  sendForValidation() {
    var a = <HTMLInputElement>document.getElementById('firstcb');

    var b = <HTMLInputElement>document.getElementById('secondcb');

    var c = <HTMLInputElement>document.getElementById('thirdcb');

    var d = <HTMLInputElement>document.getElementById('fourthcb');

    if (a.checked && b.checked && c.checked && d.checked) {
      //   alert("working fine");
      this.http.post<boolean>("http://localhost:8070/validatehospital", this.Hospital.admin).subscribe(x => {
        console.log(x)
        document.getElementById("openModalButton").click();
      })
    }
    else
      
      Swal.fire({
        position: 'center',
        icon: 'error',
        title: 'Kindly Validate all the fields',
        showConfirmButton: false,
        showCloseButton: true
      })


  }


}
