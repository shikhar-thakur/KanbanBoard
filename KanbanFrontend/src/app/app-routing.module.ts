import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component';
import { HomePgComponent } from './home-pg/home-pg.component';
import { KanbanGuardGuard } from './kanban-guard.guard';
import { NotificationComponent } from './notification/notification.component';
import { ArchiveComponent } from './archive/archive.component';
// import { PageNotFoundComponent } from './page-not-found/page-not-found.component';

const routes: Routes = [
  {path:'', component:LoginComponent},
  {path:'login',component:LoginComponent},
  {path:'register',component:RegisterComponent},
  {path: 'homePg/:id', component:HomePgComponent, canActivate:[KanbanGuardGuard]},
  {path:'notification',component:NotificationComponent},
  {path:'archive',component:ArchiveComponent},
 
  // {path: '**', component: PageNotFoundComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
