import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';
import { CookieService } from 'ngx-cookie-service';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { UserService } from '../service/user/user.service';
import { JwtHelperService } from '@auth0/angular-jwt';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {

  loggedUser: any;
  helper: any;
  userMail: string;
  ime: string;
  prezime: string;
  adresa: string;
  password: string;
  constructor(private http: HttpClient,
    private router: Router,
    private cookieService: CookieService,
    private modalService: NgbModal,
    private userService: UserService) { }

  ngOnInit() {
    this.helper = new JwtHelperService()
    this.userMail = this.helper.decodeToken(this.cookieService.get('token')).sub;
    this.getUser();
  }
  getUser() {
    this.userService.getCurrentUserByEmail(this.userMail)
      .subscribe(
        (data) => {
          console.log(data);
          this.loggedUser = Object.assign([], (data));
          this.ime = this.loggedUser.firstName;
          this.prezime = this.loggedUser.lastName;
          this.adresa = this.loggedUser.address;
        }
      )
  }

  onSubmit() {
    this.userService.updateUser(this.ime, this.prezime, this.adresa, this.loggedUser.id)
      .subscribe(
        (user: any) => {
          this.router.navigate(['/profile']);
        }, (error) => alert(error.text)
      );
  }

}
