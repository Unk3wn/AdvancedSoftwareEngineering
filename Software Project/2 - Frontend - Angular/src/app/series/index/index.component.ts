import { Component, OnInit } from '@angular/core';
import {ISeries} from "../ISeries";
import {SeriesService} from "../series.service";

@Component({
  selector: 'app-index',
  templateUrl: './index.component.html',
  styleUrls: ['./index.component.css']
})
export class IndexComponent implements OnInit {


  serieses : ISeries[] = [];

  /*------------------------------------------
 --------------------------------------------
 Created constructor
 --------------------------------------------
 --------------------------------------------*/
  constructor(public seriesService: SeriesService) { }

  /**
   * Write code on Method
   *
   * @return response()
   */
  ngOnInit(): void {
    this.seriesService.getAll().subscribe((data: ISeries[])=>{
      this.serieses = data;
      console.log(this.serieses);
    })
  }

}
