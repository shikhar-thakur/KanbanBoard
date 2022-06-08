import { Component, OnInit } from '@angular/core';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { TaskService } from '../task.service';
import {CookieService} from 'ngx-cookie-service';
@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  constructor(private rt:Router, private ts:TaskService, private active:ActivatedRoute, private cookieService:CookieService) { }
  //constructor(private rt:Router) { }

  ngOnInit(): void {
    this.ts.spinnerVisible=false;
  }

  login=new FormGroup({
    email :new FormControl('', [Validators.required, Validators.email]),
    password : new FormControl('',Validators.required),
  });
  get email(){
    return this.login.get('email');
  }

  get password(){
    return this.login.get('password');
  }

  user:any={};
  onSubmit(){
   
    this.ts.loginEmail(this.login.value).subscribe(data=>{
      this.user=this.login.value;
      console.log(data);
      if(data==null){
        alert("Incorrect Credentials!");      
      }
      else{
        this.ts.token=data.token;
        this.cookieService.set('user',this.user.email);
        this.cookieService.set('token',data.token);
        this.rt.navigate(['../homePg/'+this.user.email]);
      }
      }) ;   
  }

  hide=true;
  isLoading():boolean{
    return this.ts.loading();
  }
  
  register(){
    this.rt.navigate(['register']);
  }
}
