import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { retry } from 'rxjs';
import { ServicesService } from '../services/services.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  constructor(private service:ServicesService) { }

  ngOnInit(): void 
  {
    
  }



  registerForm =new FormGroup({
    email:new FormControl('',[Validators.required,Validators.pattern("^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]+[\\w]$")]),
    userName:new FormControl('',[Validators.required]),
    password:new FormControl('',[Validators.required,Validators.pattern("(?=.*[0-9])"+ "(?=.*[a-z])(?=.*[A-Z])"+ "(?=.*[@#$%^&+=])"+ "(?=\\S+$).{8,20}$")]),
    address:new FormControl('',Validators.required),
    phoneNo:new FormControl('',[Validators.required,Validators.pattern("^((\\+91-?)|0)?[0-9]{10,13}$")])
  })

  get email() { return this.registerForm.controls['email'];}
  get userName(){return this.registerForm.controls['userName']}
  get password() { return this.registerForm.controls['password'];}
  get address() { return this.registerForm.controls['address'];}
  get phoneNo() { return this.registerForm.controls['phoneNo'];}

  
  register()
  {
    console.log(this.registerForm.value);
    this.service.registerUser(this.registerForm.value)
  }
}
