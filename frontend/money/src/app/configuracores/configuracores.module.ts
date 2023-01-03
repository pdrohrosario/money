import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {ConfiguracoesRoutingModule} from "./configuracoes-routing.module";
import { FormsModule,ReactiveFormsModule } from '@angular/forms';
import {ConfiguracoresComponent} from "./configuracores.component";
import { MessageModule } from '../components/message/message.module';

@NgModule({
  declarations: [ConfiguracoresComponent],
  imports: [
    CommonModule,FormsModule, ReactiveFormsModule,ConfiguracoesRoutingModule, MessageModule
  ]
})
export class ConfiguracoresModule { }
