import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';
import { AdminService } from '../service/admin/admin.service';

@Component({
  selector: 'app-register-ccadmin',
  templateUrl: './register-ccadmin.component.html',
  styleUrls: ['./register-ccadmin.component.css']
})
export class RegisterCcadminComponent implements OnInit {

  firstName:string;
  lastName : string;
  username:string;
  password : string;  
  address :string;

  constructor(private http: HttpClient,
    private router: Router,
    private adminService : AdminService) { }

  ngOnInit() {
  }
  addAdmin() {
    this.adminService.registerClinicCentreAdmin(this.firstName,this.lastName,this.address,this.username)
    .subscribe(
      (user: any) => {
        this.router.navigate(['/']);
      }, (error) => alert(error.text)
    )
  }

}
