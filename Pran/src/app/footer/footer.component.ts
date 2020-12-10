import { Component, OnInit } from '@angular/core';
import { CernerAdminAuthServiceService } from '../service/authguard/cerner-admin-auth-service.service';

@Component({
  selector: 'app-footer',
  templateUrl: './footer.component.html',
  styleUrls: ['./footer.component.css']
})
export class FooterComponent implements OnInit {

  constructor(public auth:CernerAdminAuthServiceService) { }

  bool:boolean = true

  ngOnInit(): void {
  }

  ngOnChange(): void{
  }

}
