import { Component, OnInit } from '@angular/core';
import { ClinicService } from '../service/clinic/clinic.service';
import { CookieService } from 'ngx-cookie-service';
import { Router } from '@angular/router';
import { UserService } from '../service/user/user.service';
import { JwtHelperService } from '@auth0/angular-jwt';
import { AdminService } from '../service/admin/admin.service';

@Component({
  selector: 'app-manage-clinic',
  templateUrl: './manage-clinic.component.html',
  styleUrls: ['./manage-clinic.component.css']
})
export class ManageClinicComponent implements OnInit {

  loggedadmin: any;
  ime = "clinic";
  adresa = "dr klinike 1";
  opis = "clinic desc";
  clinic: any;
  helper: any;
  userMail: any;
  
  constructor(private clinicService: ClinicService,
    private cookieService: CookieService,
    private router: Router,
    private userService: UserService,
    private adminService : AdminService) { }


    ngOnInit() {
      this.helper = new JwtHelperService()
      if (this.helper.decodeToken(this.cookieService.get('token')) == null)
        this.router.navigate(['/login']);
  
      this.userMail = this.helper.decodeToken(this.cookieService.get('token')).sub;
      this.getAdmin();
    }
    getAdmin() {
      this.adminService.getAdminByMail(this.userMail)
        .subscribe(
          (data) => {
            console.log(data);
            this.loggedadmin = data;
            this.getClinic(this.loggedadmin.clinic_id);
          }
        )
    }
    getClinic(id: any) {
    this.clinicService.getAdminsClinics(id)
      .subscribe(
        (data) => {
          console.log(data);
          this.clinic = data;
          this.ime = this.clinic.name;
          this.adresa = this.clinic.address;
          this.opis = this.clinic.description;
        }
      )
  }
  onSubmit() {
    this.clinicService.updateClinic(this.ime, this.adresa, this.opis, this.clinic.id)
      .subscribe(
        (user: any) => {
        }, (error) => alert(error.text)
      );
  }


}
