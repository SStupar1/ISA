import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { CookieService } from 'ngx-cookie-service';
import { AppointmentService } from '../service/appointment/appointment.service';
import { JwtHelperService } from '@auth0/angular-jwt';

@Component({
  selector: 'app-predef-appointments',
  templateUrl: './predef-appointments.component.html',
  styleUrls: ['./predef-appointments.component.css']
})
export class PredefAppointmentsComponent implements OnInit {

  predefAppointments = [];
  helper: any;
  userId : any;

  constructor(private router: Router,
    private cookieService: CookieService,
    private appointmentService : AppointmentService) { }

  ngOnInit() {
    this.helper = new JwtHelperService()
    this.userId = this.helper.decodeToken(this.cookieService.get('token')).id;
    this.getPredefAppointments();

  }
  getPredefAppointments() {
    this.appointmentService.getPredefAppointment()
      .subscribe(
        (data) => {
          console.log(data);
          this.predefAppointments = Object.assign([], (data));
        }
      )
  }
  zakazi(appId : any){
    this.appointmentService.reserve(appId,this.userId)
      .subscribe(
        (data) => {
          console.log(data);
          this.getPredefAppointments();
        }
      )

  }

}
