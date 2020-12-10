import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class CernerAdminAuthServiceService {

  constructor() { }

  gettoken(){
    return !!localStorage.getItem("CernerAdminUser")
  }
}
