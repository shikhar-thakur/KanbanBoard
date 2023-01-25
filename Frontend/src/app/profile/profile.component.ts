import { Component, OnInit } from '@angular/core';
import { ServicesService } from '../services/services.service';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit 
{
  profile:any ={};

  constructor(private service: ServicesService) 
  {
    
  }

  ngOnInit(): void 
  {
    this.service.getUserByEmail().subscribe(person=>{
      
      this.profile = person;
      console.log(person);
    })
  }

}
