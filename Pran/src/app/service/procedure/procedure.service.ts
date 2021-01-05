import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Procedures } from 'src/app/admin-landing-page/admin-landing-page.component';

@Injectable({
  providedIn: 'root'
})
export class ProcedureService {

  constructor(private http:HttpClient) { }

  mapProcedures(proc:Procedures){
    return this.http.post<boolean>("http://localhost:8070/mapprocedure", proc)
  }
}
