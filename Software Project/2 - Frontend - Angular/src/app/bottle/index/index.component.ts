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

  ngOnInit(): void {
    this.bottleService.getAll().subscribe((data: IBottle[])=>{
      this.bottles = data;
      console.log(this.bottles);
    })
  }

  deleteSeries(uuid:string){
    this.bottleService.delete(uuid).subscribe(res => {
      this.bottles = this.bottles.filter(item => item.uuid !== uuid);
      console.log('Bottle deleted successfully!');
    })
  }

}
