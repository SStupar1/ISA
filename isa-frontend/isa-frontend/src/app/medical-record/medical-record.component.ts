import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { UserService } from '../service/user/user.service';
import { MedicalRecordService } from '../service/medical-record/medical-record.service';
import { MedicanExaminationService } from '../service/medical-examination/medican-examination.service';
import { JwtHelperService } from '@auth0/angular-jwt';
import { CookieService } from 'ngx-cookie-service';


@Component({
  selector: 'app-medical-record',
  templateUrl: './medical-record.component.html',
  styleUrls: ['./medical-record.component.css']
})
export class MedicalRecordComponent implements OnInit {

  id: any;
  medicalRecordId: any;
  firstName: string;
  lastName: string;
  jmbg: string;
  height: number;
  weight: number;
  alergies: string;
  bloodType: string;
  diopter: number;
  disabledp = true;
  medicalExaminations: any;
  helper : any;
  userType : any;

  constructor(private activatedRoute: ActivatedRoute,
    private userService: UserService,
    private medicalRecordService: MedicalRecordService,
    private medicalExaminationService: MedicanExaminationService,
    private cookieService : CookieService) { }

  ngOnInit() {
    this.helper = new JwtHelperService();
    this.userType = this.helper.decodeToken(this.cookieService.get('token')).type;
  this.id = this.activatedRoute.snapshot.url[1].path;
    this.getUser();
    this.getMedicalExaminations();
  }
  doktor() {
    if(this.userType === "D"){
      return true;
    }else{
      return false;
    }
  }
  getUser() {
    this.userService.getMedicalRecordInfo(this.id)
      .subscribe(
        (data) => {
          console.log(data);
         this.firstName = data.firstName;
          this.lastName = data.lastName;
          this.jmbg = data.jmbg;
          this.height = data.height;
          this.weight = data.weight;
          this.bloodType = data.bloodType;
          this.diopter = data.diopter;
          this.alergies = data.alergies;
          this.medicalRecordId = data.id;
        }
      )
  }
  getMedicalExaminations() {
    this.medicalExaminationService.getAllForOnePatient(this.id).subscribe(
      (data) => {
        this.medicalExaminations = data;
      }
    )
  }
  izmeni() {
    this.disabledp=false;
  }

  sacuvaj(){
    this.medicalRecordService.updateMedicalRecord(this.id,this.height,this.weight,this.bloodType,this.diopter,this.alergies).subscribe(
      (data) => {
        alert(`Zdrastveni karton je uspesno azuriran!`);
      }
    )
  }

}
