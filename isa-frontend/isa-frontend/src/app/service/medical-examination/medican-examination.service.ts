import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import { map, catchError } from 'rxjs/operators';
import { throwError } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class MedicanExaminationService {



  constructor(private http : HttpClient) { }

  newExamination(patientId : any,
    doctorId : any,
    description : any,
    medicine : any,
    diagnosis : any) {
    return this.http.post(`http://localhost:9090/api/medicalExamination/new`,{
      patient : patientId,
      doctor : doctorId,
      diagnosis : diagnosis,
      medicine : medicine,
      description : description
    }).pipe(
      map((response: any) => {
        const data = response;
        return data;
      }),
      catchError((err: any) => {
        return throwError(err.text);
      })
    )
  }
  getAllForOnePatient(patientId : any) {
    return this.http.get(`http://localhost:9090/api/medicalExamination/getAll/` + patientId).pipe(
      map(
        (response : any) => {
          return response;
        },catchError((err : any) => {
          return throwError(err.text);
          
        })
      )
    )

  }
}
