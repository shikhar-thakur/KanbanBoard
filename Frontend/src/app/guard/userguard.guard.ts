import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot, UrlTree } from '@angular/router';
import { Observable } from 'rxjs';
import { ServicesService } from '../services/services.service';

@Injectable({
  providedIn: 'root'
})
export class UserguardGuard implements CanActivate {

  constructor(private userService : ServicesService, private router : Router) {}

  canActivate(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree {
    return this.checkLogin(state.url);
  }

  checkLogin(url:string)
  {
    if (this.userService.isLoggedIn)
    {
      console.log(url);
      
      return this.userService.isLoggedIn;
    }
    else
    {
      return this.router.parseUrl('login');
    }
  }
  
}
