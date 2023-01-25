import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { ServicesService } from '../services/services.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  constructor(private route:Router, private service: ServicesService) { }
  responseData!:any;
  token!:any;

  ngOnInit(): void 
  {

  }

  loginform=new FormGroup({
    email:new FormControl('',Validators.required),
    password:new FormControl('',Validators.required)
  })

  get email() { return this.loginform.controls['email'];}
  get password() { return this.loginform.controls['password'];}

  login()
  {
    console.log(this.loginform.value);
    this.service.loginUser(this.loginform.value).subscribe(data1=>{
      console.log(data1);
      this.responseData = data1
      console.log(this.responseData.userName);
      this.service.userName = this.responseData.userName;
      this.token = this.responseData.token;

      sessionStorage.setItem('token',this.token)
    
      if(sessionStorage.getItem('token')!=null)
      this.service.isLoggedIn = true;
      this.service.email = this.email.value;
      console.log(this.service.email);
      alert("Login Successful")
      this.route.navigate(['/homepage'])
    },
    error=>{
      alert("Invalid Credentials")
      console.log(error);
    })

  }
}
