import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { CookieService } from 'ngx-cookie-service';
import { AppointmentService } from '../service/appointment/appointment.service';
import { JwtHelperService } from '@auth0/angular-jwt';

@Component({
  selector: 'app-inc-appointments',
  templateUrl: './inc-appointments.component.html',
  styleUrls: ['./inc-appointments.component.css']
})
export class IncAppointmentsComponent implements OnInit {

  predefAppointments = [];
  helper: any;
  userId : any;

  constructor(private router: Router,
    private cookieService: CookieService,
    private appointmentService : AppointmentService) { }

  ngOnInit() {
    this.helper = new JwtHelperService()
    this.userId = this.helper.decodeToken(this.cookieService.get('token')).id;
    this.getIncomingAppointments();

  }
  getIncomingAppointments() {
    this.appointmentService.getIncomingAppointments()
      .subscribe(
        (data) => {
          console.log(data);
          this.predefAppointments = Object.assign([], (data));
          console.log(this.predefAppointments);
        }
      )
  }
  otkazi(app : any){
    this.appointmentService.cancle(app.appointmentId)
      .subscribe(
        (data) => {
          console.log(data);
          this.getIncomingAppointments();
        }
      )
    

  }

}
