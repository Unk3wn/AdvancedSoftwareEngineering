import { Component, OnInit } from '@angular/core';
import {ICountry} from "../ICountry";
import {CountryService} from "../country.service";

@Component({
  selector: 'app-index',
  templateUrl: './index.component.html',
  styleUrls: ['./index.component.css']
})
export class IndexComponent implements OnInit {

  countrys : ICountry[] = [];

  /*------------------------------------------
 --------------------------------------------
 Created constructor
 --------------------------------------------
 --------------------------------------------*/
  constructor(public countryService: CountryService) { }

  ngOnInit(): void {
    this.countryService.getAll().subscribe((data: ICountry[])=>{
      this.countrys = data;
    })
  }

}
