import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { JwtHelperService } from '@auth0/angular-jwt';
import { MedicineService } from '../service/medicine/medicine.service';
import { DiagnosisService } from '../service/diagnosis/diagnosis.service';
import { MedicanExaminationService } from '../service/medical-examination/medican-examination.service';
import { CookieService } from 'ngx-cookie-service';
import { AppoinmentRequestService } from '../service/appointment-request/appoinment-request.service';
import { AppointmentTypeService } from '../service/appointment-type/appointment-type.service';



@Component({
  selector: 'app-medical-examination',
  templateUrl: './medical-examination.component.html',
  styleUrls: ['./medical-examination.component.css']
})
export class MedicalExaminationComponent implements OnInit {

  patientId : any;
  medicines: object;
  diagnosises: object;
  medicine: any;
  diagnosis: any;
  helper : any;
  doctorId : any;
  description : any;
  appointmentTypes = [];
  model : any;
  selectedType : any;

  constructor(private router: Router,
    private medicineService: MedicineService,
    private diagnosisService: DiagnosisService,
    private medicalExaminationService : MedicanExaminationService,
    private activatedRoute: ActivatedRoute,
    private cookieService : CookieService,
    private appointmentRequestService : AppoinmentRequestService,
    private appointmentTypesService : AppointmentTypeService) { }

    ngOnInit() {
      this.patientId = this.activatedRoute.snapshot.url[1].path;
      this.helper = new JwtHelperService();
      this.doctorId = this.helper.decodeToken(this.cookieService.get('token')).id;
      this.init();
    }
    init() {
      this.medicineService.getAll().subscribe(
        (data: any) => {
          this.medicines = data;
        }, (error) => alert(error.text)
      );
      this.diagnosisService.getAll().subscribe(
        (data: any) => {
          this.diagnosises = data;
        }, (error) => alert(error.text)
      );
      this.appointmentTypesService.getAll().subscribe(
        (data: any) => {
          this.appointmentTypes = data;
        }, (error) => alert(error.text)
      );
    }
    changeSelectedMedicine(filterVal: any) {
      this.medicine = filterVal;
    }
  
    changeSelectedDiagnosis(filterVal: any) {
      this.diagnosis = filterVal;
    }
    changeSelectedAppType(filterVal: any) {
      this.selectedType = filterVal;
      console.log(this.selectedType);
    }
    initialized() {
      if (this.medicines != undefined && this.diagnosises != undefined) {
        return true;
      }
      else
        return false;
    }
    promeni() {
      this.medicalExaminationService.newExamination(this.patientId, this.doctorId, this.description, this.medicine, this.diagnosis).subscribe(
        (data: any) => {
          console.log(data)
        }, (error) => {
          alert(error.text);
        }
      )
    }
    zakazi() {
      var dat = "" + this.model.year + "-" + this.model.month + "-" + this.model.day;
      this.appointmentRequestService.addRequest(this.doctorId, this.patientId, dat, this.selectedType).subscribe(
        (data : any) => {
          console.log(data)
        }, (error) => {
          alert(error.text);
        }
      )  
    }

}
