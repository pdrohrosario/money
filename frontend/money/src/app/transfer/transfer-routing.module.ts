import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {ListTransferComponent} from "./list-transfer/list-transfer.component";
import {DetalTransferComponent} from "./detal-transfer/detal-transfer.component";
import { FormTransferComponent } from './form-transfer/form-transfer.component';

const routes: Routes = [
  {
    path:'',
    component:ListTransferComponent,
  },{
    path:'view/:transferID',
    component:DetalTransferComponent
  },{
    path:'edit/:transferID',
    component:FormTransferComponent
  }
  ,{
    path:'create',
    component:FormTransferComponent
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class TransferRoutingModule { }
