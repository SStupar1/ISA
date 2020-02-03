import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { CookieService } from 'ngx-cookie-service';
import { UserService } from '../service/user/user.service';

@Component({
  selector: 'app-change-password',
  templateUrl: './change-password.component.html',
  styleUrls: ['./change-password.component.css']
})
export class ChangePasswordComponent implements OnInit {

  password: string;
  password2: string;
  oldPassword: string;
  //loggedUser: any;
  //id: number;
  userMail : any;
  //helper : any;

  constructor(private router: Router,
    private cookieService: CookieService,
    private userService: UserService) { }

  ngOnInit() {
    /*this.helper = new JwtHelperService();
    this.userMail = this.helper.decodeToken(this.cookieService.get('token')).sub;
    this.getUser();*/
  }

  promeni() {
    if(this.password === this.password2){
      this.userService.updateUserPassword(this.password, this.oldPassword)
      .subscribe(
        (user: any) => {
          this.router.navigate(['/login']);
          this.cookieService.set('loggedUser', '');
        }, (error) => alert(error.text)
      );
    } else {
      alert('Morate uneti istu lozinku dva puta!');
    }
  }

}
