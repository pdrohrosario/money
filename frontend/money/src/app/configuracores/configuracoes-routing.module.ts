import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {ConfiguracoresComponent} from "./configuracores.component";


const routes: Routes = [
  {
    path:'',
    component:ConfiguracoresComponent,
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ConfiguracoesRoutingModule { }
