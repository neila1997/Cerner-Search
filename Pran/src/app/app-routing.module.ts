import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { AdminvalidateComponent } from './adminvalidate/adminvalidate.component';
import { CernerAdminLoginComponent } from './cerner-admin-login/cerner-admin-login.component';
import { HospitaldetailsComponent } from './hospitaldetails/hospitaldetails.component';
import { HospitalAdminLoginComponent } from './hospital-admin-login/hospital-admin-login.component';
import { InitialLoginComponent } from './initial-login/initial-login.component';
import { RegistrationComponent } from './registration/registration.component';
import { CernerAdminGuardGuard } from './cerner-admin-guard.guard';
import { HospitalViewComponent } from './hospital-view/hospital-view.component';
import { AdminLandingPageComponent } from './admin-landing-page/admin-landing-page.component';

const routes: Routes = [
  {path:'adminvalidate', component:AdminvalidateComponent, canActivate:[CernerAdminGuardGuard]}
  , {path:'hospitaldetails', component:HospitaldetailsComponent, canActivate:[CernerAdminGuardGuard]}
  , {path:'initiallogin', component:InitialLoginComponent}
  , {path:'cerneradminlogin', component:CernerAdminLoginComponent}
  , {path:'adminlogin', component:HospitalAdminLoginComponent}
  , {path:'adminhome', component:AdminLandingPageComponent}
  , {path:'registration', component:RegistrationComponent}
  ,{path:'hospitalview/:name', component:HospitalViewComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
