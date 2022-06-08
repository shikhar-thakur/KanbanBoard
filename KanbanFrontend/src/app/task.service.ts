import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map, catchError, tap } from 'rxjs/operators';
import { CookieService } from 'ngx-cookie-service';

@Injectable({
  providedIn: 'root'
})
export class TaskService {
  notificationCount(notificationCount: any) {
    throw new Error('Method not implemented.');
  }

  constructor(private httpClient:HttpClient, private cookie:CookieService) {
    //this.isLoggedIn=false;
  }

  public token:any="";
  public spinnerVisible:boolean=false;

  isUserLoggedIn(): boolean {
    if(this.cookie.check('token')){
      this.token=this.cookie.get('token');
    }
    if(this.token!="")
      return true;
    else
      return false;
  }

  loading():boolean{
    return this.spinnerVisible;
  }

  registerEmail(member:any){
    return this.httpClient.post<any>('http://localhost:8090/api/user/add', member);
  }

  updateUser(email:any, member:any){
    return this.httpClient.post<any>("http://localhost:8085/api/v2/update/"+email, member);
  }

  loginEmail(member:any){
    return this.httpClient.post<any>("http://localhost:8085/api/v2/login", member);
  }

  deadlineAlert(date:any){
    return this.httpClient.get<any>("http://localhost:8090/api/task/getdeadline/"+date);
  }

  getAll(){
    return this.httpClient.get<Array<any>>("http://localhost:8090/api/user/getall");
  }

  addTask(task:any){
    return this.httpClient.post<any>("http://localhost:8090/api/task/add", task);
  }

  updateTask(task:any){
    return this.httpClient.post<any>("http://localhost:8090/api/task/update", task);
  }

  getAllTask(email:any){
    return this.httpClient.get<Array<any>>("http://localhost:8090/api/task/getAll/"+email);
  }

  deleteTask(id:any){
    return this.httpClient.delete<any>("http://localhost:8090/api/task/delete/"+id);
  }

  searchTask(task:any, email:any){
    return this.httpClient.get<Array<any>>("http://localhost:8090/api/task/searchtaskbyuser/"+task+"/"+email);
  }


  
  gettask() {
    let header = new HttpHeaders().set("Authorization", "Bearer " + localStorage.getItem("token"))
    const url3 = `http://localhost:8084/api/v1/tasks/` + localStorage.getItem("username")
    return this.httpClient.get(url3, { headers: header })
  }

  getArchive() {
    return this.httpClient.get<any>(`http://localhost:8084/api/v1/archive/` + localStorage.getItem("username"))
  }

  moveToArchive(data: any) {
    return this.httpClient.post<any>(`http://localhost:8084/api/v1/archiveTask/` + localStorage.getItem("username"), data)
  }

  deleteArchive(id: any) {
    return this.httpClient.delete<any>(`http://localhost:8084/api/v1/archive/` + localStorage.getItem("username") + `/` + id)
  }

}
