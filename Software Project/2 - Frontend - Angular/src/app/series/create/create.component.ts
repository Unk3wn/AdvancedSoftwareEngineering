import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { FormGroup, FormControl, Validators} from '@angular/forms';
import {SeriesService} from "../series.service";

@Component({
  selector: 'app-create',
  templateUrl: './create.component.html',
  styleUrls: ['./create.component.css']
})
export class CreateComponent implements OnInit {

  form!: FormGroup;

  /*------------------------------------------
  --------------------------------------------
  Created constructor
  --------------------------------------------
  --------------------------------------------*/
  constructor(
    public seriesService: SeriesService,
    private router: Router
  ) { }


  ngOnInit(): void {
    this.form = new FormGroup({
      label: new FormControl('', [Validators.required]),
    });
  }

  get f(){
    return this.form.controls;
  }

  submit(){
    console.log(this.form.value);
    this.seriesService.create(this.form.value).subscribe((res:any) => {
      console.log('Series created successfully!');
      this.router.navigateByUrl('series/index');
    })
  }

}
