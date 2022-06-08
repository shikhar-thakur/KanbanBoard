import { Component, OnInit, Input } from '@angular/core';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { RouterModule, Router } from '@angular/router';
import { TaskService } from '../task.service';

@Component({
  selector: 'app-update-user',
  templateUrl: './update-user.component.html',
  styleUrls: ['./update-user.component.css']
})
export class UpdateUserComponent implements OnInit {

  @Input() item={};

  constructor(private route: Router, private ts:TaskService, private _snackBar: MatSnackBar) { }

  register=new FormGroup({
    email :new FormControl('', [Validators.required, Validators.email]),
    password : new FormControl('',Validators.required),
    confirm : new FormControl('',Validators.required)  
  });

  user:any={};

  set userName(name:any){
    this.userName=name;
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

  defaulEmail:any="";
  person:any={};
  ngOnInit(): void {
    this.user=this.item;
    this.defaulEmail=this.user.email;
  }
   hide=true;

  onSubmit(){
    if(!this.confirm?.invalid && !this.password?.invalid && (this.confirm?.value==this.password?.value)) {
      var user2={email : this.defaulEmail, password:this.password?.value}
      this.ts.updateUser(this.defaulEmail, user2).subscribe(data=>{
        this.user=data;
        this._snackBar.open("Password Updated", "OK");
        window.location.reload();
      });
    }
    else{
      console.log("Registration failed");
    }
  }

}
