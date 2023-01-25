import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { FirstpageComponent } from './firstpage/firstpage.component';
import { UserguardGuard } from './guard/userguard.guard';
import { HomepageNavigationComponent } from './homepage-navigation/homepage-navigation.component';
import { LoginComponent } from './login/login.component';
import { NotificationComponent } from './notification/notification.component';
import { RegisterComponent } from './register/register.component';




const routes: Routes = [
  {
    path: "",
    component: FirstpageComponent,
    children: [
      {
        path: "signup",
        component: RegisterComponent
      },
      {
        path: "login",
        component: LoginComponent
      },
      {
        path: "",
        redirectTo: "login",
        pathMatch: "full"
      }
    ]
  },
  {
    path: "homepage",
    canActivate: [UserguardGuard],
    component: HomepageNavigationComponent,
    
  },
  {
    path:"notifications",
    canActivate: [UserguardGuard],
    component : NotificationComponent
  },
  {
    path: "",
    redirectTo: "homepage",
    pathMatch: "full"
  }
  
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
