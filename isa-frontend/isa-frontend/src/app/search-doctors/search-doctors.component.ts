import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { JwtHelperService } from '@auth0/angular-jwt';
import { CookieService } from 'ngx-cookie-service';
import { PatientService } from '../service/patient/patient.service';
import { AppointmentService } from '../service/appointment/appointment.service';

@Component({
  selector: 'app-search-doctors',
  templateUrl: './search-doctors.component.html',
  styleUrls: ['./search-doctors.component.css']
})
export class SearchDoctorsComponent implements OnInit {
  doctors = [];
  ime = "";
  prezime = "";
  ocena=""
  helper : any;
  userId : any;
  appointment : any;
  constructor(private router: Router,
    private activeRoute: ActivatedRoute,
    private patientService: PatientService,
    private cookieService : CookieService,
    private appointmentService : AppointmentService) { }


  ngOnInit() {
    this.helper = new JwtHelperService()
    this.userId = this.helper.decodeToken(this.cookieService.get('token')).id;
    
    this.patientService.searchDoctors("", "", "")
      .subscribe(
        (data) => {
          console.log(data);
          this.doctors = Object.assign([], (data));
        }
      )
  }
  onSubmit(form: NgForm) {
    this.findDoctors(this.ime, this.prezime, this.ocena);
  }
  findDoctors(ime: string, prezime: string, jmbg: string) {
    this.patientService.searchDoctors(this.ime, this.prezime, this.ocena)
      .subscribe(
        (data) => {
          console.log(data);
          this.doctors = Object.assign([], (data));
        }
      )
  }
  changeSelectedAppointment(filterVal: any) {
    this.appointment = filterVal;
    console.log(this.appointment);
  }
  zakazi(){
    this.appointmentService.reserve(this.appointment,this.userId)
      .subscribe(
        (data) => {
          console.log(data);
          
        }
      )

  }
}
