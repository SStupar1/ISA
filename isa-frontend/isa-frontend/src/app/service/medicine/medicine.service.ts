import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import { map, catchError } from 'rxjs/operators';
import { throwError } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class MedicineService {

  constructor(private http: HttpClient) { }

  addMedicine(medicine :String) {
    return this.http.post(`http://localhost:9090/api/medicine/addMedicine`,{
      name: medicine
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
    return this.http.get(`http://localhost:9090/api/medicine/getAll`).
    pipe(
      map((response : any) => {
        
        return response;
      }),
      catchError((err : any) => {
        
        return throwError(err.text);
        
      })
    )
  }
}
