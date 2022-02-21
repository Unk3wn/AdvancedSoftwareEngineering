import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Observable, throwError} from "rxjs";
import {catchError} from "rxjs/operators";
import {IBottle} from "./IBottle";
import {ManufacturerService} from "../manufacturer/manufacturer.service";
import {SeriesService} from "../series/series.service";

@Injectable({
  providedIn: 'root'
})
export class BottleService {
  private apiURL = "http://localhost:8080/bottle";

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
  constructor(
    private httpClient: HttpClient,
    private manufacturerService : ManufacturerService,
    private seriesService : SeriesService,
  ) { }

  getAll(): Observable<any> {
    return this.httpClient.get(this.apiURL)
      .pipe(
        catchError(this.errorHandler)
      )
  }

  getAllManufacturers() : Observable<any> {
    return this.manufacturerService.getAll();
  }

  getAllSeries() : Observable<any> {
    return this.seriesService.getAll();
  }

  find(uuid: string | null): Observable<any> {
    return this.httpClient
      .get(this.apiURL + '/read/uuid?bottleUUID=' + uuid)
      .pipe(catchError(this.errorHandler))
  }

  create(iBottle:IBottle): Observable<any> {
    return this.httpClient.post(this.apiURL,JSON.stringify(iBottle),this.httpOptions)
      .pipe(
        catchError(this.errorHandler)
      )
  }

  update(uuid:string, iBottle:IBottle): Observable<any> {
    return this.httpClient.put(this.apiURL + '/edit?uuid=' + uuid, JSON.stringify(iBottle), this.httpOptions)
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
