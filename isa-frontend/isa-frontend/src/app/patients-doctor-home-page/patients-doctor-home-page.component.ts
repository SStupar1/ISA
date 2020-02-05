import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { PatientService } from '../service/patient/patient.service';
import { CookieService } from 'ngx-cookie-service';
import { JwtHelperService } from '@auth0/angular-jwt';

@Component({
  selector: 'app-patients-doctor-home-page',
  templateUrl: './patients-doctor-home-page.component.html',
  styleUrls: ['./patients-doctor-home-page.component.css']
})
export class PatientsDoctorHomePageComponent implements OnInit {

  patients = [];
  helper: any;

  constructor(private router: Router,
    private patientService: PatientService,
    private cookieService: CookieService) { }

  ngOnInit() {
    this.helper = new JwtHelperService()
    if(this.helper.decodeToken(this.cookieService.get('token')) == null)
      this.router.navigate(['/login']);
    this.ucitajPacijente();
  }

  ucitajPacijente() {
    this.patientService.getAllPatients()
      .subscribe(
        (data) => {
          console.log(data);
          this.patients = Object.assign([], (data));
        }
      )
  }

}
