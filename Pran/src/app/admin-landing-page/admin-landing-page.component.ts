import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-admin-landing-page',
  templateUrl: './admin-landing-page.component.html',
  styleUrls: ['./admin-landing-page.component.css']
})
export class AdminLandingPageComponent implements OnInit {

  constructor() { 

  }

  ngOnInit(): void {
  }

  procedureType : string[] = ["Heart", "Delivery", "Kidney"]
  addProcedure(){
    document.getElementById("openModalButton").click();

    navigator.geolocation.getCurrentPosition(x=>{
      console.log(x.coords.latitude)
      console.log(x.coords.longitude)
    })
  }

}
