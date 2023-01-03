import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ListGoalComponent } from './list-objetivo/list-objetivo.component';
import { GradeGoalComponent } from './grade-objetivo/grade-objetivo.component';
import { GoalRoutingModule } from './objetivo-routing.module';
import { CardObjetivoComponent } from './card-objetivo/card-objetivo.component';
import { FormObjetivoComponent } from './form-objetivo/form-objetivo.component';
import { ReactiveFormsModule } from '@angular/forms';
import { DetalObjetivoComponent } from './detal-objetivo/detal-objetivo.component';

@NgModule({
  declarations: [
    ListGoalComponent,
    GradeGoalComponent,
    CardObjetivoComponent,
    FormObjetivoComponent,
    DetalObjetivoComponent,
  ],
  imports: [CommonModule,ReactiveFormsModule, GoalRoutingModule],
})
export class ObjetivoModule {}
