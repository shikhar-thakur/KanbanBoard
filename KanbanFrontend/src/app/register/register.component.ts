import { Component, OnInit } from '@angular/core';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import { RouterModule, Router } from '@angular/router';
import { TaskService } from '../task.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  constructor(private route: Router, private ts:TaskService) { }

  register=new FormGroup({
    userName:new FormControl('',Validators.required),
    email :new FormControl('', [Validators.required, Validators.email]),
    password : new FormControl('',Validators.required),
    confirm : new FormControl('',Validators.required)  
  });

  get userName(){
    return this.register.get('userName');
  }

  get email(){
    return this.register.get('email');
  }

  get password(){
    return this.register.get('password');
  }

  get confirm(){
    return this.register.get('confirm');
  }
  user:any={};

  hide=true;

  onSubmit(){
   
    if(!this.confirm?.invalid && !this.password?.invalid && (this.confirm?.value==this.password?.value)) {
      //const addressForm = this.register.value;
      //this.ms.registerEmail(addressForm);
      this.user={email : this.email?.value, password:this.password?.value, userName : this.userName?.value}
      this.ts.registerEmail(this.user).subscribe(data=>{
        this.user=data;
        console.log(data);
         this.route.navigate(['/login']);
      });
    }
    else{
      console.log("Registration failed");
    }
  }

  isLoading():boolean{
    return this.ts.loading();
  }

  ngOnInit(): void {
  }
}
