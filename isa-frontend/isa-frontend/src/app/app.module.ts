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
import { HTTP_INTERCEPTORS } from '@angular/common/http';
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
import { MedicalExaminationComponent } from './medical-examination/medical-examination.component';
import { AddClinicComponent } from './add-clinic/add-clinic.component';
import { RegisterAdminComponent } from './register-admin/register-admin.component';
import { RegisterCcadminComponent } from './register-ccadmin/register-ccadmin.component';
import { AddMedicineAndDiagnosisComponent } from './add-medicine-and-diagnosis/add-medicine-and-diagnosis.component';
import { SearchClinicComponent } from './search-clinic/search-clinic.component';
import { ChangePasswordComponent } from './change-password/change-password.component';
import { AllPatientsComponent } from './all-patients/all-patients.component';
import { SearchPatientsComponent } from './search-patients/search-patients.component';
import { PredefAppointmentsComponent } from './predef-appointments/predef-appointments.component';
import { CreatePredefAppointmentComponent } from './create-predef-appointment/create-predef-appointment.component';
import { CalendarComponent } from './calendar/calendar.component';
import { CalendarModule, DateAdapter } from 'angular-calendar';
import { adapterFactory } from 'angular-calendar/date-adapters/date-fns';
import { PatientsDoctorHomePageComponent } from './patients-doctor-home-page/patients-doctor-home-page.component';

const appRoutes: Routes = [
  { path: 'login', component: LoginComponent },
  { path: 'register', component: RegisterComponent },
  { path: 'pendingUsers', component: PendingUsersComponent },
  { path: 'profile', component: ProfileComponent },
  { path: 'medicalRecord/:id', component: MedicalRecordComponent },
  { path: 'medicalExamination/:id', component: MedicalExaminationComponent },
  { path: 'addMedicineAndDiagnosis', component: AddMedicineAndDiagnosisComponent },
  { path: 'addClinic', component: AddClinicComponent },
  { path: 'addAdmin', component: RegisterAdminComponent },
  { path: 'addCCAdmin', component: RegisterCcadminComponent },
  { path: 'searchClinics',component : SearchClinicComponent},
  { path: 'changePassword', component : ChangePasswordComponent},
  { path: 'patients', component: SearchPatientsComponent },
  { path: 'predefAppointments',component : PredefAppointmentsComponent},
  { path: 'createPredefAppointment',component : CreatePredefAppointmentComponent},
  { path: 'calendar',component : CalendarComponent},
  { path: '', component: HomePageComponent }, 


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
    MedicalRecordComponent,
    MedicalExaminationComponent,
    AddClinicComponent,
    RegisterAdminComponent,
    RegisterCcadminComponent,
    AddMedicineAndDiagnosisComponent,
    SearchClinicComponent,
    ChangePasswordComponent,
    AllPatientsComponent,
    SearchPatientsComponent,
    PredefAppointmentsComponent,
    CreatePredefAppointmentComponent,
    CalendarComponent,
    PatientsDoctorHomePageComponent,
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
    RouterModule.forRoot(appRoutes),
    CalendarModule.forRoot({ provide: DateAdapter, useFactory: adapterFactory })

  ],
  providers: [
    {
      provide: HTTP_INTERCEPTORS,
      useClass: TokenInterceptor,
      multi: true
    },
    CookieService,],
  bootstrap: [AppComponent]
})
export class AppModule { } 
