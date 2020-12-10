import { Injectable } from '@angular/core';
import { BehaviorSubject, Subject } from 'rxjs';
import { Hospital } from 'src/app/models/hospital/hospital';
import { Admin } from 'src/app/models/admin/admin';

@Injectable({
  providedIn: 'root'
})
@Injectable()
export class DataService {
  subject:BehaviorSubject<Hospital> = new BehaviorSubject(null)
  admin:BehaviorSubject<Admin> = new BehaviorSubject(null)
  constructor() { 
  }
}
