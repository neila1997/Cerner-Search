import { Injectable } from '@angular/core';
import { CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot, UrlTree, Router } from '@angular/router';
import { Observable } from 'rxjs';
import { CernerAdminAuthServiceService } from './service/authguard/cerner-admin-auth-service.service';

@Injectable({
  providedIn: 'root'
})
export class CernerAdminGuardGuard implements CanActivate {

  constructor(private adminguard:CernerAdminAuthServiceService, private router:Router){


  }
  canActivate(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree {
      if (!this.adminguard.gettoken()) {  
        this.router.navigateByUrl("cerneradminlogin");  
    }  
    return this.adminguard.gettoken();
  }
  
}
