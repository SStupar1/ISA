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
}
