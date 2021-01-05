import { Component, OnInit, ViewChild } from '@angular/core';
import { FormArray, FormBuilder, FormGroup } from '@angular/forms';
import { ProcedureService } from '../service/procedure/procedure.service';

export class Procedures {
  procedureType: string
  procedureSubtype: string
  roomCost: Room[] = []
  personnels: PersonnelDetails[] = []
}

export class Room {
  roomType: string
  roomCost: number
}

export class PersonnelDetails {
  doctorName: string
  doctorIdentifier: number
}

@Component({
  selector: 'app-admin-landing-page',
  templateUrl: './admin-landing-page.component.html',
  styleUrls: ['./admin-landing-page.component.css']
})
export class AdminLandingPageComponent implements OnInit {
  public procedureForm: FormGroup
  private proc: Procedures

  constructor(private fb: FormBuilder, private procService:ProcedureService) {
  }

  ngOnInit(): void {
    this.procedureForm = this.fb.group({
      procedureType: ''
      , procedureName: ''
      , costs: this.fb.array([this.fb.group({ roomType: '', cost: '' })])
      , personnels: this.fb.array([this.fb.group({ doctorName: '', doctorIdentifier: '' })])
    })
  }

  procedureType: string[] = ["Heart", "Delivery", "Kidney"]
  roomType: string[] = ["Single Room", "Suite", "Two-Sharing", "Three-Sharing"]

  openModal1() {
    document.getElementById("closeModal2").click();
    document.getElementById("closeModal3").click();
    document.getElementById("openModal1Button").click();

  }
  openModal2() {
    document.getElementById("closeModal1").click();
    document.getElementById("closeModal3").click();
    document.getElementById("openModal2Button").click();
  }
  openModal3() {
    document.getElementById("closeModal1").click();
    document.getElementById("closeModal2").click();
    document.getElementById("openModal3Button").click();
  }

  get costs() {
    return this.procedureForm.get('costs') as FormArray;
  }

  addCost() {
    this.costs.push(this.fb.group({ roomType: '', cost: '' }))
  }

  deleteCost(index) {
    this.costs.removeAt(index)
  }

  get personnels() {
    return this.procedureForm.get('personnels') as FormArray;
  }

  addPersonnel() {
    this.personnels.push(this.fb.group({ doctorName: '', doctorIdentifier: '' }))
  }

  deletePersonnel(index) {
    this.personnels.removeAt(index)
  }

  mapProcedures() {
    // var json = JSON.stringify(this.procedureForm)
    console.log(this.procedureForm.value)
    if (this.procedureForm.value.procedureType != '' && this.procedureForm.value.procedureName != '') {
      this.proc = new Procedures
      this.proc.procedureType = this.procedureForm.value.procedureType
      this.proc.procedureSubtype = this.procedureForm.value.procedureName
      var count = 0
      for (let i = 0; i < this.procedureForm.value.costs.length; i++) {
        if (this.procedureForm.value.costs[i].roomType != '' && this.procedureForm.value.costs[i].cost != '') {
          this.proc.roomCost[count] = new Room()
          this.proc.roomCost[count].roomType = this.procedureForm.value.costs[i].roomType
          this.proc.roomCost[count].roomCost = this.procedureForm.value.costs[i].cost
          count += 1
        }
      }

      count = 0
      for (let i = 0; i < this.procedureForm.value.personnels.length; i++) {
        if (this.procedureForm.value.personnels[i].doctorName != '' && this.procedureForm.value.personnels[i].doctorIdentifier != '') {
          this.proc.personnels[count] = new PersonnelDetails()
          this.proc.personnels[count].doctorName = this.procedureForm.value.personnels[i].doctorName
          this.proc.personnels[count].doctorIdentifier = this.procedureForm.value.personnels[i].doctorIdentifier
          count += 1
        }
      }
      console.log(this.proc)
    }

    if(this.proc != null){
        this.procService.mapProcedures(this.proc).subscribe(
          x=>{
            console.log("Wohoo")
          }
          , y=>{
            console.log("Oops")
          }
        )
    }
  }

}
