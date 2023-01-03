import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ListTransferComponent } from './list-transfer/list-transfer.component';
import { GradeTransferComponent } from './grade-transfer/grade-transfer.component';
import {CardTransferModule} from "../components/card-transfer/card-transfer.module";
import {TransferRoutingModule} from "./transfer-routing.module";
import { DetalTransferComponent } from './detal-transfer/detal-transfer.component';
import { FormTransferComponent } from './form-transfer/form-transfer.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import {MessageModule} from "../components/message/message.module";


@NgModule({
  declarations: [
    ListTransferComponent,
    GradeTransferComponent,
    DetalTransferComponent,
    FormTransferComponent,
  ],
  imports: [
    CommonModule,FormsModule, ReactiveFormsModule, CardTransferModule, TransferRoutingModule, MessageModule
  ]
})
export class TransferModule { }
