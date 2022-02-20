import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {IndexComponent} from "./index/index.component";
import {CreateComponent} from "./create/create.component";

const routes: Routes = [
  { path: 'series', redirectTo: 'series/index', pathMatch: 'full'},
  { path: 'series/index', component: IndexComponent },
  { path: 'series/create', component: CreateComponent },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class SeriesRoutingModule { }
