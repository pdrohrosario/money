import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { PerfilComponent } from './perfil.component';
import { PerfilRoutingModule } from './perfil-routing.module' ;
import {ReactiveFormsModule} from "@angular/forms";
import {MessageModule} from "../components/message/message.module";

@NgModule({
  declarations: [
    PerfilComponent
  ],
  imports: [
    CommonModule,PerfilRoutingModule, ReactiveFormsModule, MessageModule
  ]
})
export class PerfilModule { }
