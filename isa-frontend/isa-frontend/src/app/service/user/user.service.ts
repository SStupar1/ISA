import { Injectable } from '@angular/core';
import { CookieService } from 'ngx-cookie-service';
import { map, catchError } from 'rxjs/operators';
import { throwError } from 'rxjs';
import { JwtHelperService } from '@auth0/angular-jwt'
import { Router } from '@angular/router';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  base = 'http://localhost:9090';

  constructor(private http: HttpClient,
    private cookieService: CookieService,
    private router: Router
  ) { }

  getCurrentUserByEmail(email: string) {
    return this.http.post(this.base + "/api/person/getByEmail", {
      username: email
    })
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
  registerUser(
    firstname: string,
    lastname: string,
    address: string,
    jmbg: string,
    email: string,
    password: string) {
    return this.http.post("http://localhost:9090/auth/register", {
      firstName: firstname,
      lastName: lastname,
      address: address,
      jmbg: jmbg,
      username: email,
      password: password
    })
      .pipe(
        map((response: any) => {
          const data = response
          return data;
        }),
        catchError((err: any) => {
          return throwError(JSON.parse(err.text));
        })
      )
  }
  updateUser(
    firstname: string,
    lastname: string,
    address: string,
    id: number) {
    return this.http.post("http://localhost:9090/api/person/update", {
      firstName: firstname,
      lastName: lastname,
      address: address,
      id: id
    })
      .pipe(
        map((response: any) => {
          const data = response
          return data;
        }),
        catchError((err: any) => {
          return throwError(JSON.parse(err.text));
        })
      )
  }
  updateUserPassword(
    newPassword: string,
    oldPassword: string) {
    return this.http.post("http://localhost:9090/api/person/changePassword", {
      newPassword: newPassword,
      oldPassword: oldPassword
    })
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
  getMedicalRecordInfo(id: Number) {
    return this.http.get("http://localhost:9090/api/medicalRecord/getByPatientId?id=" + id)
      .pipe(
        map((response: any) => {
          console.log(response)
          const data = response
          return data;
        }),
        catchError((err: any) => {
          return throwError(JSON.parse(err.text));
        })
      );
  }
}
