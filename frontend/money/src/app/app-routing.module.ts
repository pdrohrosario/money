import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {AuthenticationGuard} from "./authentication/authentication.guard";
import {LoginGuard} from "./authentication/login.guard";

const routes: Routes = [
  {
    path:'',
    pathMatch:'full',
    redirectTo:'home'
  },
  {
    path:'home',
    loadChildren:() => import('./home/home.module').then((module) => module.HomeModule),
    canLoad:[LoginGuard]
  },
  {
    path:'transfer',
    loadChildren:() => import('./transfer/transfer.module').then((module) => module.TransferModule),
      canLoad:[AuthenticationGuard],
  },
  {
    path:'objet',
    loadChildren:() => import('./objetivo/objetivo.module').then((module) => module.ObjetivoModule),
      canLoad:[AuthenticationGuard],
  },
  {
    path:'perfil',
    loadChildren:()=> import('./perfil/perfil.module').then((module) => module.PerfilModule),
      canLoad:[AuthenticationGuard],
  },
  {
    path:'configuracoes',
    loadChildren:()=> import('./configuracores/configuracores.module').then((module) => module.ConfiguracoresModule),
    canLoad:[AuthenticationGuard],
  }

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
