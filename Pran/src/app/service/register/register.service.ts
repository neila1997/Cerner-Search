import { HttpClient } from '@angular/common/http';
import { ComponentFactoryResolver, Injectable } from '@angular/core';
import { Contact } from '../../models/contact/contact';
import { BehaviorSubject } from 'rxjs';
import { Admin } from '../../models/admin/admin';
import { Hospital } from '../../models/hospital/hospital';
import Swal from 'sweetalert2'
@Injectable({
  providedIn: 'root'
})
export class RegisterService {

  flag: boolean
  obs: BehaviorSubject<boolean> = new BehaviorSubject(null)

  constructor(public http: HttpClient) { }

  public registerHospital(hospital: Hospital) {

    return this.http.post('http://localhost:8070/register', hospital, { responseType: 'text' })  }

  public viewall(hospital: Hospital) {
   return this.http.post('http://localhost:8070/adminvalidate', hospital, { responseType: 'text' })
  }

  public getDetails(hospital: Hospital) {
    var pincode = hospital.site.location.pincode
    var options = "Select Area";
    var xhReq = new XMLHttpRequest();
    xhReq.open("GET", "https://api.postalpincode.in/pincode/" + pincode, false);
    xhReq.send(null);
    var jsonObject = JSON.parse(xhReq.responseText);
    var b = JSON.parse(JSON.stringify(jsonObject));
    if (jsonObject[0].Status != 'Success') {
      hospital.site.location.district = '';
      hospital.site.location.state = '';
    }
    else {
      var city = jsonObject[0].PostOffice[0].District;
      var state = jsonObject[0].PostOffice[0].Circle;
      hospital.site.location.district = city;
      hospital.site.location.state = state;
      var i;

      for (i = 0; i < jsonObject[0].PostOffice.length; i++) {
        var area = jsonObject[0].PostOffice[i].Name;
        options += "<option>" + area + "</option>";
      }
      document.getElementById("area").innerHTML = options;
    }
    return hospital
  }

  public async verifyLogin(hospital: Hospital) {
    return await this.http.post<boolean>("http://localhost:8070/verify", hospital.admin);
  }

  public updatePassword(admin: Admin) {
    return this.http.post<object>("http://localhost:8070/update", admin);
  }
  public validate_pemail(hospital: Hospital) {
    var email = hospital.contact.primaryEmailId;
    var api_key = "2fc4d291b28f119d663554ea341baea9";
    var smpt = "1";
    var format = "1";
    var xhReq = new XMLHttpRequest();
    xhReq.open("GET", "http://apilayer.net/api/check?access_key=" + api_key + "&email=" + email + "&smtp=" + smpt + "&format=" + format, false);
    xhReq.send(null);
    var jsonObject = JSON.parse(xhReq.responseText);
    var b = JSON.parse(JSON.stringify(jsonObject));
    if (jsonObject.smtp_check == false) {
      hospital.contact.primaryEmailId = ''
      Swal.fire({
        position: 'center',
        icon: 'warning',
        title: 'Please enter valid Email ID',
        showConfirmButton: false,
        showCloseButton: true
      })
    }
    return hospital
  }

  public validate_email(email: string) {
    var api_key = "2fc4d291b28f119d663554ea341baea9";
    var smpt = "1";
    var format = "1";
    return this.http.get("http://apilayer.net/api/check?access_key=" + api_key + "&email=" + email + "&smtp=" + smpt + "&format=" + format, {responseType:'text'})
      // });
  }

  public validate_landline(hospital: Hospital) {
  var landlineno = hospital.contact.landlineNo;
  if (landlineno.length != 7) {
    hospital.contact.landlineNo = ''
    Swal.fire({
      position: 'center',
      icon: 'warning',
      title: 'Enter valid 7 digit Landline Number',
      showConfirmButton: false,
      showCloseButton: true
    })
  }
  return hospital
}

  public validate_mobile(hospital: Hospital) {
  var mobno = hospital.contact.mobileNo;
  if (mobno.length != 10) {
    hospital.contact.mobileNo = ''
    Swal.fire({
      title: 'Enter valid 10 digit Mobile Number',
      showCloseButton: true
    })
  }
  return hospital
}

}
