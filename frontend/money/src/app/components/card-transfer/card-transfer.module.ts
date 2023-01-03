import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { CardTransferComponent } from './card-transfer.component';

@NgModule({
  declarations: [
    CardTransferComponent
  ],
  imports: [
    CommonModule
  ],
  exports:[CardTransferComponent]
})
export class CardTransferModule { }
