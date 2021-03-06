import { Component, OnInit } from '@angular/core';
import {Router} from "@angular/router";
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {BottleService} from "../bottle.service";
import {IManufacturer} from "../../manufacturer/IManufacturer";
import {ISeries} from "../../series/ISeries";

@Component({
  selector: 'app-create',
  templateUrl: './create.component.html',
  styleUrls: ['./create.component.css']
})
export class CreateComponent implements OnInit {

  form!: FormGroup;
  manufacturers : IManufacturer[] = [];
  serieses : ISeries[] = [];

  /*------------------------------------------
  --------------------------------------------
  Created constructor
  --------------------------------------------
  --------------------------------------------*/
  constructor(
    public bottleService: BottleService,
    private router: Router
  ) { }


  ngOnInit(): void {
    this.form = new FormGroup({
      label: new FormControl('', [Validators.required]),
      price: new FormControl('',[Validators.required]),
      yearOfManufacture: new FormControl('',[Validators.required]),
      manufacturer: new FormControl(null,[Validators.required]),
      forSale: new FormControl(''),
      favorite: new FormControl(''),
      unsaleable: new FormControl(''),
      series: new FormControl(null)
    });
    this.bottleService.getAllManufacturers().subscribe((data: IManufacturer[])=>{
      this.manufacturers = data;
    })
    this.bottleService.getAllSeries().subscribe((data: ISeries[])=>{
      this.serieses = data;
    })
  }

  get f(){
    return this.form.controls;
  }

  submit(){
    console.log(this.form.value);
    this.bottleService.create(this.form.value).subscribe((res:any) => {
      console.log('Bottle created successfully!');
      this.router.navigateByUrl('bottle/index');
    })
  }

}
