import { Component, OnInit } from '@angular/core';
import {IBottle} from "../IBottle";
import {BottleService} from "../bottle.service";

@Component({
  selector: 'app-index',
  templateUrl: './index.component.html',
  styleUrls: ['./index.component.css']
})
export class IndexComponent implements OnInit {

  bottles : IBottle[] = [];

  /*------------------------------------------
 --------------------------------------------
 Created constructor
 --------------------------------------------
 --------------------------------------------*/
  constructor(public bottleService: BottleService) { }

  /**
   * Write code on Method
   *
   * @return response()
   */
  ngOnInit(): void {
    this.bottleService.getAll().subscribe((data: IBottle[])=>{
      this.bottles = data;
      console.log(this.bottles);
    })
  }

}
