import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {IndexComponent} from "./index/index.component";

const routes: Routes = [
  { path: 'manufacturer', redirectTo: 'manufacturer/index', pathMatch: 'full'},
  { path: 'manufacturer/index', component: IndexComponent },];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ManufacturerRoutingModule { }
