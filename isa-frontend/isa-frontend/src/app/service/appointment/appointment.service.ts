import { Injectable } from '@angular/core';
import { Response } from '@angular/http';
import { HttpHeaders } from '@angular/common/http';
import { CookieService } from 'ngx-cookie-service';
import { map, catchError } from 'rxjs/operators';
import { throwError } from 'rxjs';
import { JwtHelperService } from '@auth0/angular-jwt'
import { Router } from '@angular/router';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class AppointmentService {

  constructor(private http: HttpClient,
    private cookieService: CookieService,
    private router: Router
  ) { }

  getPredefAppointment() {
    return this.http.get("http://localhost:9090/api/appointment/getPredefAppointment")
      .pipe(
        map((response: any) => {
          const data = response
          return data;
        }),
        catchError((err: any) => {
          return throwError(err);
        })
      );
  }
  reserve(appId : any,currentUserId :any) {
    return this.http.post("http://localhost:9090/api/appointment/reservePredef",{
        patientId : currentUserId,
        appointmentId : appId

    })
      .pipe(
        map((response: any) => {
          const data = response
          return data;
        }),
        catchError((err: any) => {
          return throwError(err);
        })
      );
  }
  createPredef(currentDoctor : any,
    currentRoom : any,
    currentType : any,
    cena : any,
    popust :any,
    dat :any){
    return this.http.post("http://localhost:9090/api/appointment/createPredef",{
      doctorId :currentDoctor,
      roomId : currentRoom,
      typeId : currentType,
      cena : cena,
      popust : popust,
      dat : dat 
      
    })
    .pipe(
      map((response: any) => {
        const data = response
        return data;
      }),
      catchError((err: any) => {
        return throwError(err);
      })
    );
  }
  getByDoctorIdForCalendar(doctorId : string) {
    console.log("http://localhost:9090/api/appointment/getAppointmentsForDoctor?doctorId=a" + doctorId)
    return this.http.get("http://localhost:9090/api/appointment/getAppointmentsForDoctor?doctorId=a" + doctorId)
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
