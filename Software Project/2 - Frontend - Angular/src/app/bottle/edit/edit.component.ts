import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { FormGroup, FormControl, Validators} from '@angular/forms';
import {IBottle} from "../IBottle";
import {BottleService} from "../bottle.service";
import {IManufacturer} from "../../manufacturer/IManufacturer";
import {ISeries} from "../../series/ISeries";
import {bottom} from "@popperjs/core";

@Component({
  selector: 'app-edit',
  templateUrl: './edit.component.html',
  styleUrls: ['./edit.component.css']
})
export class EditComponent implements OnInit {

  uuid!: string;
  bottle!: IBottle;
  bottle2!: IBottle;
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
    private route: ActivatedRoute,
    private router: Router,
    private actRoute: ActivatedRoute
  ) { }

  ngOnInit(): void {
    this.uuid = this.route.snapshot.params['bottleUUID'];
    this.bottleService.find(this.uuid).subscribe((data: IBottle)=>{
      this.bottle = data;
      this.bottle2 = JSON.parse(JSON.stringify(this.bottle));
    });
    this.form = new FormGroup({
      uuid : new FormControl(this.uuid),
      label: new FormControl(''),
      price: new FormControl(''),
      yearOfManufacture: new FormControl(''),
      manufacturer: new FormControl(''),
      forSale: new FormControl(''),
      favorite: new FormControl(''),
      unsaleable: new FormControl(''),
      series: new FormControl('')
    });
    this.bottleService.getAllSeries().subscribe((data: ISeries[])=>{
      this.serieses = data;
    })
    this.bottleService.getAllManufacturers().subscribe((data: IManufacturer[])=>{
      this.manufacturers = data;
    })
  }

  get f(){
    return this.form.controls;
  }

  submit(){
    console.log(this.bottle)
    this.bottleService.update(this.uuid, this.bottle).subscribe((res:any) => {
      console.log('Bottle updated successfully!');
      this.router.navigateByUrl('bottle/index');
    })
  }

}
