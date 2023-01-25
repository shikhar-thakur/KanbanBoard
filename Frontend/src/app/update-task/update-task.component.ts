import { Component, OnInit, Output } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { MatDialog, MatDialogRef } from '@angular/material/dialog';
import { Router } from '@angular/router';
import { EventEmitter } from '@angular/core';
import { ServicesService } from '../services/services.service';

@Component({
  selector: 'app-update-task',
  templateUrl: './update-task.component.html',
  styleUrls: ['./update-task.component.css']
})
export class UpdateTaskComponent implements OnInit {

  constructor(private services: ServicesService, private route:Router,public dialog: MatDialog,
    public dialogRef: MatDialogRef<UpdateTaskComponent>) { }

  taskUpdated:any;
  updatedTaskForm:any;
  allStatus:any;
  currentDate=new Date();

  ngOnInit(): void
  {
    this.taskUpdated = this.services.updatedTask;
    this.allStatus = this.services.status;
    console.log("this.taskUpdated");
    console.log(this.taskUpdated);

    this.updatedTaskForm = new FormGroup({
      taskId:new FormControl(''),
      email:new FormControl(''),
      taskName: new FormControl(''),
      statusName: new FormControl(''),
      spaceName: new FormControl(''),
      priority: new FormControl(''),
      taskDescription: new FormControl(''),
      startDate: new FormControl(''),
      endDate: new FormControl(''),
    })
    
    if(this.taskUpdated.startDate == null && this.taskUpdated.endDate == null)
    {
      this.updatedTaskForm.patchValue({
        taskId: this.taskUpdated.taskId,
        email: this.taskUpdated.email,
        taskName: this.taskUpdated.taskName,
        statusName: this.taskUpdated.statusName,
        spaceName: this.taskUpdated.spaceName,
        priority: this.taskUpdated.priority,
        taskDescription:this.taskUpdated.taskDescription,
        startDate: this.taskUpdated.startDate,
        endDate: this.taskUpdated.endDate,
      })
    }
    else
    {
      this.updatedTaskForm.patchValue({
        taskId: this.taskUpdated.taskId,
        email: this.taskUpdated.email,
        taskName: this.taskUpdated.taskName,
        statusName: this.taskUpdated.statusName,
        spaceName: this.taskUpdated.spaceName,
        priority: this.taskUpdated.priority,
        taskDescription:this.taskUpdated.taskDescription,
        startDate: this.taskUpdated.startDate.substring(0,10),
        endDate: this.taskUpdated.endDate.substring(0,10),
      })
    }
  }

 
  updateTask(taskData:any)
  {
    console.log("task Data");
    
    console.log(taskData);
    console.log(this.taskUpdated.taskId);
    
    this.services.updateTaskById(taskData).subscribe(response=>{
      
      console.log("response");
      
      console.log(response);
      this.dialogRef.close({data:"1"})
      
     
      alert("Task Updated Successfully...");
  
    })
  }

}
