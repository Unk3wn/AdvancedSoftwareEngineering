import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { BottleRoutingModule } from './bottle-routing.module';
import { IndexComponent } from './index/index.component';
import { CreateComponent } from './create/create.component';
import {FormsModule, ReactiveFormsModule} from "@angular/forms";


@NgModule({
  declarations: [
    IndexComponent,
    CreateComponent
  ],
  imports: [
    CommonModule,
    BottleRoutingModule,
    FormsModule,    //import here
    ReactiveFormsModule //import here
  ]
})
export class BottleModule { }
