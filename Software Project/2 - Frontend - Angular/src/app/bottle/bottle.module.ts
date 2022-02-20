import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { BottleRoutingModule } from './bottle-routing.module';
import { IndexComponent } from './index/index.component';


@NgModule({
  declarations: [
    IndexComponent
  ],
  imports: [
    CommonModule,
    BottleRoutingModule
  ]
})
export class BottleModule { }
