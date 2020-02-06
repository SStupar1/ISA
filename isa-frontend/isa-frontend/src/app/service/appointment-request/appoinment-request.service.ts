import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { map, catchError } from 'rxjs/operators';
import { throwError } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AppoinmentRequestService {

  constructor(private http: HttpClient) { }

  addRequest(
    doctor: any,
    patient: any,
    date: any,
    type: string
    ) {
    return this.http.post("http://localhost:9090/api/appointmentrequest/addAppointmentRequest", {
      doctor: doctor,
      patient: patient,
      date: date,
      type: type
    })
      .pipe(
        map((response: any) => {
          const data = response
          return data;
        }),
        catchError((err: any) => {
          return throwError(err);
        })
      )
  }
  getAppointmentRequests() {
    return this.http.get("http://localhost:9090/api/administrator/getAppointmentRequests")
    .pipe(
      map((response: any) => {
        const data = response;
        return data;
      }),
      catchError((err: any) => {
        return throwError(JSON.parse(err.text));
      })
    )
  }
}
