import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ListTransferComponent } from './list-transfer/list-transfer.component';

const routes: Routes = [
  {
    path:'',
    component:ListTransferComponent,
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
