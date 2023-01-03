import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { FormObjetivoComponent } from './form-objetivo/form-objetivo.component';
import { ListGoalComponent } from './list-objetivo/list-objetivo.component';
import { DetalObjetivoComponent } from './detal-objetivo/detal-objetivo.component';

const routes: Routes = [
  {
    path:'',
    component:ListGoalComponent,
  },{
    path:'edit/:objetivoID',
    component:FormObjetivoComponent
  }
  ,{
    path:'create',
    component:FormObjetivoComponent
  },{
    path:'view/:objetivoID',
    component:DetalObjetivoComponent
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class GoalRoutingModule { }
