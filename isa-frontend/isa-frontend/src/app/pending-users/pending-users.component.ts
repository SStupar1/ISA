import { Component, OnInit } from '@angular/core';
import { JwtHelperService } from '@auth0/angular-jwt';
import { UserService } from '../service/user/user.service';
import { CookieService } from 'ngx-cookie-service';
import { Router } from '@angular/router';
import { AdminService } from '../service/admin/admin.service';

@Component({
  selector: 'app-pending-users',
  templateUrl: './pending-users.component.html',
  styleUrls: ['./pending-users.component.css']
})
export class PendingUsersComponent implements OnInit {

  pendingUsers = [];
  helper: any;

  constructor(private router: Router,
    private cookieService: CookieService,
    private adminService: AdminService) { }

  ngOnInit() {
    this.helper = new JwtHelperService()
    if(this.helper.decodeToken(this.cookieService.get('token')) == null)
      this.router.navigate(['/login']);
    this.getPendingUsers();
  }

  getPendingUsers() {
    this.adminService.getPending()
      .subscribe(
        (data) => {
          console.log(data);
          this.pendingUsers = Object.assign([], (data));
        }
      )
  }

  prihvati(id) {
    this.adminService.acceptUser(id)
      .subscribe(
        () => {
          this.getPendingUsers();
        }
      )
  }

  odbij(id) {
    this.adminService.denyUser(id)
    .subscribe(
      () => {
        this.getPendingUsers();
      }
    )
  }

}
