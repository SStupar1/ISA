import { Component, OnInit } from '@angular/core';
import { CookieService } from 'ngx-cookie-service';
import { AdminService } from '../service/admin/admin.service';
import { UserService } from '../service/user/user.service';
import { JwtHelperService } from '@auth0/angular-jwt';
import { Router } from '@angular/router';

@Component({
  selector: 'app-manage-doctors',
  templateUrl: './manage-doctors.component.html',
  styleUrls: ['./manage-doctors.component.css']
})
export class ManageDoctorsComponent implements OnInit {

  doctors = [];
  userMail: string;
  helper: any;
  loggedUser: any;
  imenovog: any;
  prznovog: any;
  adresanovog: any;
  sifranovog: any;
  mailnovog: any;

  constructor(private cookieService: CookieService,
    private adminService: AdminService,
    private userService: UserService,
    private router: Router) { }

  ngOnInit() {
    this.helper = new JwtHelperService()
    this.userMail = this.helper.decodeToken(this.cookieService.get('token')).sub;
    console.log(this.userMail);
    this.getUser();
  }

  getUser() {
    this.adminService.getAdminByMail(this.userMail)
      .subscribe(
        (data) => {
          console.log(data);
          this.loggedUser = Object.assign([], (data));
          
        }
      )
  }

  onSubmit() {
    this.adminService.createDoctor(this.imenovog, this.prznovog, this.adresanovog, this.mailnovog, this.loggedUser.clinic_id)
      .subscribe(
        (data) => {
          console.log(data);
          
        }
      )
  }

}
