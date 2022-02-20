import { Component, OnInit } from '@angular/core';
import {IManufacturer} from "../IManufacturer";
import {ManufacturerService} from "../manufacturer.service";

@Component({
  selector: 'app-index',
  templateUrl: './index.component.html',
  styleUrls: ['./index.component.css']
})
export class IndexComponent implements OnInit {

  manufacturers : IManufacturer[] = [];

  /*------------------------------------------
 --------------------------------------------
 Created constructor
 --------------------------------------------
 --------------------------------------------*/
  constructor(public manufacturerService: ManufacturerService) { }

  /**
   * Write code on Method
   *
   * @return response()
   */
  ngOnInit(): void {
    this.manufacturerService.getAll().subscribe((data: IManufacturer[])=>{
      this.manufacturers = data;
      console.log(this.manufacturers);
    })
  }

}
