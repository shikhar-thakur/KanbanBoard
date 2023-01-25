import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class ServicesService {


  constructor(private httpClient : HttpClient, private router:Router) { }
  isLoggedIn: boolean=false;
  
  
  email:any;
  userName:any;
  space:any;
  updatedTask:any;
  status:any;
  statusData:any;
  List:any;
  
  registerUser(user:any)
  {
    this.httpClient.post('http://localhost:8082/register',user).subscribe(data=>{
      console.log(data);
      alert("Registered Successfully")
      this.router.navigate(['login'])
    },
    error=>{console.log(error);
    })
  }

  loginUser(data1:any)
  {
    return this.httpClient.post('http://localhost:8082/login',data1)
    
  }


  getSpaceByEmail()
  {
    return this.httpClient.get(`http://localhost:8082/api/s1/spaces/${this.email}`)
  }
  
  saveSpace(data:any)
  {
    console.log(data);
    
    return this.httpClient.post("http://localhost:8082/api/s1/space",data);
  }

  deleteSpace(spaceName:any,email:any)
  {
    console.log(spaceName);
    console.log(email);
    
    return this.httpClient.delete(`http://localhost:8082/api/s1/space/${email}/${spaceName}`);
  }



  //Status services
  getstatus(email:any, spaceName:any)
  {
    console.log(email);
   
    return this.httpClient.get(`http://localhost:8082/api/s1/status/${email}/${spaceName.spaceName}`);
  }

  saveStatus(status:any)
  {
    return this.httpClient.post("http://localhost:8082/api/s1/status",status)
  }

  deleteStatus(data:any)
  {
    return this.httpClient.delete(`http://localhost:8082/api/s1/status/${data.statusId}`)
  }


  getTasks(data:any)
  {
    console.log(data);
    
    return this.httpClient.get(`http://localhost:8082/api/s1/task/${data.email}/${data.spaceName}`)
  }

  addTask(data:any)
  {
    return this.httpClient.post("http://localhost:8082/api/s1/task",data);
  }

  
  getTaskByEmail()
  {
    console.log("email");
    
    console.log(this.email);
    
    return this.httpClient.get(`http://localhost:9000/api/s1/task/${this.email}`);
  }

  getNotificationById(id:any)
  {
    return this.httpClient.get(`http://localhost:9000/api/s1/notifications/${id}`);
  }

  getUserByEmail()
  {
    return this.httpClient.get(`http://localhost:9000/user/${this.email}`);
  }

  getTaskByEnailAndSpace(UserEmail:any,spaceName:any)
  {
    return this.httpClient.get(`http://localhost:9000/api/s1/task/${UserEmail}/${spaceName}`);
  }

  updateTaskById(data:any)
  {
    console.log("in service");
    console.log(data);
    
    
    return this.httpClient.put(`http://localhost:8082/api/s1/task/${data.taskId}`,data);
  }

  deleteTaskById(id:any)
  {
    return this.httpClient.delete(`http://localhost:8082/api/s1/task/${id}`);
  }


  sendEmailNotification(count:any)
  {

    
    return this.httpClient.post(`http://localhost:8082/api/s1//sendEmail/${this.email}`,count);
  }

}
