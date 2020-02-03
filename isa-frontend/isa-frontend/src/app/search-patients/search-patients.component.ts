import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Router, ActivatedRoute } from '@angular/router';
import { PatientService } from '../service/patient/patient.service';
import { CookieService } from 'ngx-cookie-service';
import { JwtHelperService } from '@auth0/angular-jwt';

@Component({
  selector: 'app-search-patients',
  templateUrl: './search-patients.component.html',
  styleUrls: ['./search-patients.component.css']
})
export class SearchPatientsComponent implements OnInit {

  patients = [];
  ime = "";
  prezime = "";
  jmbg = "";
  helper : any
  userType : any

  constructor(private router: Router,
    private activeRoute: ActivatedRoute,
    private patientService: PatientService,
    private cookieService :CookieService,) { }

  ngOnInit() {
    this.helper = new JwtHelperService();
    this.userType = this.helper.decodeToken(this.cookieService.get('token')).type;

    this.findPatients("","","");

  }
  currentUser() {
    if(this.userType === "D"){
      return true;
    }else{
      return false;
    }
  }

  onSubmit(form: NgForm) {
    this.findPatients(this.ime, this.prezime, this.jmbg);
    //this.router.navigate(['/searchpatients'], { queryParams: { name: this.ime, lastname: this.prezime, jmbg: this.jmbg } });
  }

  findPatients(ime: string, prezime: string, jmbg: string) {
    this.patientService.searchPatients(this.ime, this.prezime, this.jmbg)
      .subscribe(
        (data) => {
          console.log(data);
          this.patients = Object.assign([], (data));
        }
      )
  }
  medicalRecord(id : any){
    this.router.navigate(['/medicalRecord/' + id]);
  }
  startExamination(id : any){
    this.router.navigate(['/medicalExamination/' + id]);
  }

}
