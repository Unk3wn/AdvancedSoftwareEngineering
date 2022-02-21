import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {IndexComponent} from "./index/index.component";
import {CreateComponent} from "./create/create.component";

const routes: Routes = [
  { path: 'bottle', redirectTo: 'bottle/index', pathMatch: 'full'},
  { path: 'bottle/index', component: IndexComponent },
  { path: 'bottle/create', component: CreateComponent }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class BottleRoutingModule { }
