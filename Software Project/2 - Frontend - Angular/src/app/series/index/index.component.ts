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

  ngOnInit(): void {
    this.seriesService.getAll().subscribe((data: ISeries[])=>{
      this.serieses = data;
      console.log(this.serieses);
    })
  }

  deleteSeries(uuid:string){
    this.seriesService.delete(uuid).subscribe(res => {
      this.serieses = this.serieses.filter(item => item.uuid !== uuid);
      console.log('Series deleted successfully!');
    })
  }

}
