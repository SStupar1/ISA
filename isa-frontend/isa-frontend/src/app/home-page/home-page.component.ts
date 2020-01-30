import { Component, OnInit } from '@angular/core';
import { CookieService } from 'ngx-cookie-service';
import { JwtHelperService } from '@auth0/angular-jwt';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';


import { Router } from '@angular/router';
import { UserService } from '../service/user/user.service';

@Component({
  selector: 'app-home-page',
  templateUrl: './home-page.component.html',
  styleUrls: ['./home-page.component.css']
})
export class HomePageComponent implements OnInit {

  userType: any;
  helper: any;
  userPatient = false;
  userAdmin = false;
  userNurse = false;
  userDoctor = false;
  userClinicCenterAdmin = false;
  loggedUser: any;
  password: string;
  userMail: string;

  constructor(private cookieService: CookieService,
    private modalService: NgbModal,
    private userService: UserService,
    private router: Router) { }

    ngOnInit() {
      this.helper = new JwtHelperService()
      if (this.helper.decodeToken(this.cookieService.get('token')) == null)
        this.router.navigate(['/login']);
      this.userType = this.helper.decodeToken(this.cookieService.get('token')).type;
      this.userMail = this.helper.decodeToken(this.cookieService.get('token')).sub;
      console.log(this.userMail);
      this.getUser();
      if (this.userType === "P") {
        this.userPatient = true;
        this.userAdmin = false;
        this.userNurse = false;
        this.userDoctor = false;
        this.userClinicCenterAdmin = false;
      }
      else if (this.userType === "D") {
        this.userDoctor = true;
        this.userPatient = false;
        this.userAdmin = false;
        this.userNurse = false;
        this.userClinicCenterAdmin = false;
      }
      else if (this.userType === "N") {
        this.userNurse = true;
        this.userPatient = false;
        this.userAdmin = false;
        this.userDoctor = false;
        this.userClinicCenterAdmin = false;
      }
      else if (this.userType === "A") {
        this.userAdmin = true;
        this.userPatient = false;
        this.userNurse = false;
        this.userDoctor = false;
        this.userClinicCenterAdmin = false;
      }
      else if (this.userType === "CCA") {
        this.userClinicCenterAdmin = true;
        this.userPatient = false;
        this.userAdmin = false;
        this.userNurse = false;
        this.userDoctor = false;
      }
    }
    getUser() {
      this.userService.getCurrentUserByEmail(this.userMail)
        .subscribe(
          (data) => {
            console.log(data);
            this.loggedUser = Object.assign([], (data));
          }
        )
    }

}
