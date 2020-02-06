import { Component, OnInit } from '@angular/core';
import { CookieService } from 'ngx-cookie-service';
import { UserService } from '../service/user/user.service';
import { RoomService } from '../service/room/room.service';
import { JwtHelperService } from '@auth0/angular-jwt';
import { Router } from '@angular/router';
import { AdminService } from '../service/admin/admin.service';

@Component({
  selector: 'app-manage-rooms',
  templateUrl: './manage-rooms.component.html',
  styleUrls: ['./manage-rooms.component.css']
})
export class ManageRoomsComponent implements OnInit {

  nazivnove: any;
  brnove: any;
  rooms = [];
  nazivi = [];
  userMail: string;
  helper: any;
  loggedUser: any;

  constructor(private cookieService: CookieService,
    private userService: UserService,
    private roomService: RoomService,
    private router: Router,
    private adminService : AdminService) { }

  ngOnInit() {
    this.helper = new JwtHelperService()
    this.userMail = this.helper.decodeToken(this.cookieService.get('token')).sub;
    this.getUser();
  }
  onSubmit() {
    this.roomService.create(this.nazivnove, this.brnove, this.loggedUser.clinic_id)
      .subscribe(
        (data) => {
          console.log(data);
          
        }
      )
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

}
