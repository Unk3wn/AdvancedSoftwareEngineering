import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders, HttpParams} from "@angular/common/http";
import {Observable, throwError} from "rxjs";
import {catchError} from "rxjs/operators";
import {ISeries} from "./ISeries";

@Injectable({
  providedIn: 'root'
})
export class SeriesService {
  private apiURL = "http://localhost:8080/series";

  /*------------------------------------------
  --------------------------------------------
  Http Header Options
  --------------------------------------------
  --------------------------------------------*/
  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json'
    })
  }

  /*------------------------------------------
  --------------------------------------------
  Created constructor
  --------------------------------------------
  --------------------------------------------*/
  constructor(private httpClient: HttpClient) { }

  getAll(): Observable<any> {

    return this.httpClient.get(this.apiURL)

      .pipe(
        catchError(this.errorHandler)
      )
  }

  create(iSeries:ISeries): Observable<any> {
    return this.httpClient.post(this.apiURL,JSON.stringify(iSeries),this.httpOptions)
      .pipe(
        catchError(this.errorHandler)
      )
  }

  delete(uuid:string){
    return this.httpClient.delete(this.apiURL + '?uuid=' + uuid, this.httpOptions)
      .pipe(
        catchError(this.errorHandler)
      )
  }

  errorHandler(error:any) {
    let errorMessage = '';
    if(error.error instanceof ErrorEvent) {
      errorMessage = error.error.message;
    } else {
      errorMessage = `Error Code: ${error.status}\nMessage: ${error.message}`;
    }
    return throwError(errorMessage);
  }
}
