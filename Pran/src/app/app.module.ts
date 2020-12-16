import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { RegistrationComponent } from './registration/registration.component';
import { HeaderComponent } from './header/header.component';
import { FooterComponent } from './footer/footer.component';
import { AdminvalidateComponent } from './adminvalidate/adminvalidate.component';
import { InitialLoginComponent } from './initial-login/initial-login.component';
import { HospitaldetailsComponent } from './hospitaldetails/hospitaldetails.component';
import { CernerAdminLoginComponent } from './cerner-admin-login/cerner-admin-login.component';
import { HospitalAdminLoginComponent } from './hospital-admin-login/hospital-admin-login.component';
import { HospitalViewComponent } from './hospital-view/hospital-view.component';
import { AdminLandingPageComponent } from './admin-landing-page/admin-landing-page.component';

@NgModule({
  declarations: [
    AppComponent,
    RegistrationComponent,
    HeaderComponent,
    FooterComponent,
    AdminvalidateComponent,
    InitialLoginComponent,
    HospitaldetailsComponent,
    CernerAdminLoginComponent,
    HospitalAdminLoginComponent,
    HospitalViewComponent,
    AdminLandingPageComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
