import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot, UrlTree, RouterModule } from '@angular/router';
import { Observable } from 'rxjs';
import { TaskService } from './task.service';

@Injectable({
  providedIn: 'root'
})
export class KanbanGuardGuard implements CanActivate {
  constructor(private ts:TaskService, private router:Router){}
  canActivate(
    route: ActivatedRouteSnapshot,
    //state: RouterStateSnapshot): Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree {
    state: RouterStateSnapshot): boolean {
      if(!this.ts.isUserLoggedIn()){
        this.router.navigate(['']);
        return false;
      }
    return true;
  }
  
}
