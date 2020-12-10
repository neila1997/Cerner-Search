import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { AdminvalidateComponent } from './adminvalidate/adminvalidate.component';
import { HospitaldetailsComponent } from './hospitaldetails/hospitaldetails.component';
import { RegistrationComponent } from './registration/registration.component';

const routes: Routes = [
  {path:'registration', component:RegistrationComponent},
  {path:'adminvalidate', component:AdminvalidateComponent},
  {path:'hospitaldetails', component:HospitaldetailsComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
