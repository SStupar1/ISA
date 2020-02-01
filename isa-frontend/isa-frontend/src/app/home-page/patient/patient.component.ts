import { Component, OnInit } from '@angular/core';
import { JwtHelperService } from '@auth0/angular-jwt';
import { CookieService } from 'ngx-cookie-service';

@Component({
  selector: 'app-patient',
  templateUrl: './patient.component.html',
  styleUrls: ['./patient.component.css']
})
export class PatientComponent implements OnInit {

  helper : any
  id : any

  constructor(private cookieService : CookieService) { }

  ngOnInit() {
    this.helper = new JwtHelperService()
    this.id = this.helper.decodeToken(this.cookieService.get('token')).id;
  }

}
