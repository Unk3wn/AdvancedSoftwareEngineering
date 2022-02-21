import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {IndexComponent} from "./index/index.component";
import {CreateComponent} from "./create/create.component";
import {EditComponent} from "./edit/edit.component";

const routes: Routes = [
  { path: 'bottle', redirectTo: 'bottle/index', pathMatch: 'full'},
  { path: 'bottle/index', component: IndexComponent },
  { path: 'bottle/create', component: CreateComponent },
  { path: 'bottle/:bottleUUID/edit', component: EditComponent }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class BottleRoutingModule { }
