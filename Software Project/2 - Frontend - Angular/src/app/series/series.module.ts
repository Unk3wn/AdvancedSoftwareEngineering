import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { SeriesRoutingModule } from './series-routing.module';
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
    SeriesRoutingModule,
    FormsModule,    //import here
    ReactiveFormsModule //import here
  ]
})
export class SeriesModule { }
