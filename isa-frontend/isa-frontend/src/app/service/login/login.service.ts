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
export class LoginService {
  
  base = 'http://localhost:9090';
  constructor(private http: HttpClient,
    private cookieService: CookieService,
    private router: Router
  ) { }

  

  login(email: string, password: string) {
    return this.http.post(this.base + "/auth/login", {
      username: email,
      password: password
    })
      .pipe(
        map((res: any) => {
          const helper = new JwtHelperService();
          const token = res.accessToken;
          this.cookieService.set('token', token);
          const data = res;
          return data;
        }),
        catchError((err: any) => {
          console.log(err)
          return throwError(JSON.parse(err.text))
        })
      )
  }
}
