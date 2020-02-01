import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { OwlDateTimeModule, OwlNativeDateTimeModule } from 'ng-pick-datetime';
import { MatTableModule } from '@angular/material';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { NgbModal, NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { LoginComponent } from './login/login.component';
import { Routes, RouterModule } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { HttpModule } from '@angular/http';
import { CookieService } from 'ngx-cookie-service';
import {HTTP_INTERCEPTORS} from '@angular/common/http';
import { TokenInterceptor } from 'src/Interceptor/TokenInterceptor';
import { HeaderComponent } from './header/header.component';
import { HomePageComponent } from './home-page/home-page.component';
import { AdminComponent } from './home-page/admin/admin.component';
import { CcadminComponent } from './home-page/ccadmin/ccadmin.component';
import { DoctorComponent } from './home-page/doctor/doctor.component';
import { NurseComponent } from './home-page/nurse/nurse.component';
import { PatientComponent } from './home-page/patient/patient.component';
import { RegisterComponent } from './register/register.component';
import { PendingUsersComponent } from './pending-users/pending-users.component';
import { ProfileComponent } from './profile/profile.component';
import { MedicalRecordComponent } from './medical-record/medical-record.component';

const appRoutes: Routes = [
  {path: 'login',component: LoginComponent},
  {path: 'register', component : RegisterComponent},
  {path: 'pendingUsers',component : PendingUsersComponent},
  {path : 'profile', component : ProfileComponent},
  {path : 'medicalRecord/:id', component : MedicalRecordComponent},
  {path : '',component :HomePageComponent},


]

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    HeaderComponent,
    HomePageComponent,
    AdminComponent,
    CcadminComponent,
    DoctorComponent,
    NurseComponent,
    PatientComponent,
    RegisterComponent,
    PendingUsersComponent,
    ProfileComponent,
    MedicalRecordComponent
  ],
  imports: [ 
    BrowserModule,
    HttpClientModule,
    AppRoutingModule,
    FormsModule,
    OwlDateTimeModule, 
    OwlNativeDateTimeModule,
    BrowserAnimationsModule,
    MatTableModule,
    NgbModule,
    RouterModule.forRoot(appRoutes)
  ],
  providers: [
    {
    provide : HTTP_INTERCEPTORS,
    useClass : TokenInterceptor,
    multi :true
  },
  CookieService,],
  bootstrap: [AppComponent]
})
export class AppModule { } 
