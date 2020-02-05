import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { map, catchError } from 'rxjs/operators';
import { throwError } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AppointmentTypeService {

  constructor(private http: HttpClient) { }

  addType(type :String) {
    return this.http.post(`http://localhost:9090/api/appointmenttype/addAppointmentType`,{
      name: type
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

  getAll() {
    return this.http.get(`http://localhost:9090/api/appointmenttype/getAll`)
      .pipe(
        map((response: any) => {
          const data = response
          return data;
        }),
        catchError((err: any) => {
          return throwError(JSON.parse(err.text));
        })
      );
  }
  
}