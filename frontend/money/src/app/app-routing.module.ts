import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

const routes: Routes = [
  {
    path:'',
    pathMatch:'full',
    redirectTo:'home'
  },
  {
    path:'home',
    loadChildren:() => import('./home/home.module').then((module) => module.HomeModule),
  },
  {
    path:'transfer',
    loadChildren:() => import('./transfer/transfer.module').then((module) => module.TransferModule),
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
