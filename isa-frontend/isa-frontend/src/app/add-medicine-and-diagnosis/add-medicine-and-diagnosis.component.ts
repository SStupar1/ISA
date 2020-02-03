import { Component, OnInit } from '@angular/core';
import { DiagnosisService } from '../service/diagnosis/diagnosis.service';
import { MedicineService } from '../service/medicine/medicine.service';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { CookieService } from 'ngx-cookie-service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-add-medicine-and-diagnosis',
  templateUrl: './add-medicine-and-diagnosis.component.html',
  styleUrls: ['./add-medicine-and-diagnosis.component.css']
})
export class AddMedicineAndDiagnosisComponent implements OnInit {

  medicine: string;
  diagnosis: string;

  constructor(private router: Router,
    private cookieService: CookieService,
    private modalService: NgbModal,
    private medicneService: MedicineService,
    private diagnosisService: DiagnosisService) { }

  ngOnInit() {
  }
  addMedicine() {
    if (this.medicine.trim() != '') {
      this.medicneService.addMedicine(this.medicine).subscribe(
        (medicine: any) => {
          alert('Lek dodat');
        }, (error) => alert(error.text)
      );
    } else {
      alert('Morate uneti naziv leka');
    }
  }
  addDiagnosis() {
    if (this.diagnosis.trim() != '') {
      this.diagnosisService.addDiagnosis(this.diagnosis).subscribe(
        (diagnosis: any) => {
          alert('Dijagnoza dodata');
        }, (error) => alert(error.text)
      );
    } else {
      alert('Morate uneti naziv dijagnoze');
    }
  }

}
