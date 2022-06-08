import { Component, OnInit } from '@angular/core';
import {CdkDragDrop, moveItemInArray, transferArrayItem} from '@angular/cdk/drag-drop';
import { Router, ActivatedRoute, ParamMap } from '@angular/router';
import { TaskService } from '../task.service';
import {FormControl, FormGroup, Validators, ReactiveFormsModule, FormBuilder} from '@angular/forms';
import { CookieService } from 'ngx-cookie-service';
import {MatSnackBar} from '@angular/material/snack-bar';
//import * as moment from 'moment/moment.js';
@Component({
  selector: 'app-home-pg',
  templateUrl: './home-pg.component.html',
  styleUrls: ['./home-pg.component.css']
})
export class HomePgComponent implements OnInit{
  savetask(data1: any) {
    throw new Error('Method not implemented.');
  }
  deleteArchive: any;
  getArchive() {
    throw new Error('Method not implemented.');
  }
  notificationCount: any;
  getNotification(arg0: number) {
    throw new Error('Method not implemented.');
  }
  gettask() {
    throw new Error('Method not implemented.');
  }
  ngAfterViewInit(){
    
    return this.service.loading();
  }

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private service: TaskService,
    private formBuilder: FormBuilder,
    private cookie:CookieService,
    private _snackBar: MatSnackBar
    ) { }
    msg:any="";
    todayDate:Date=new Date();
  value:number=0;
  color:any='';
  wip:any=[];
  todo:any=[];
  done:any=[];
  user:any={};
  task:any={};
  defaultTask:any={};
  username:any="";
  email:any="";
  ngOnInit(): void {
    const id = this.route.snapshot.paramMap.get('id')!;
    if(id!=this.cookie.get('user')){
      this.router.navigate(['']);
    }
    this.service.getAll().subscribe(data=>{
      data.forEach(element => {
        if(element.email==id){
          this.user=element;
          this.username=element.userName;
          this.email=element.email;
        }
      });
    });

    this.service.getAllTask(id).subscribe(data=>{
      data.forEach(element=>{
        if(element.taskStatus=="todo"){
          this.todo.push(element);
        }
        else if(element.taskStatus=="wip"){
          this.wip.push(element);
        }
        else{
          this.done.push(element);
        }
      })
    });

  }

  //task:any={};
  searchForm= new FormControl('');
  addTask=new FormGroup({
    taskTitle :new FormControl('', Validators.required),
    taskDeadLine : new FormControl('',Validators.required),
    taskStatus: new FormControl('',Validators.required),
    taskDescription: new FormControl('', Validators.required)
  });

  get taskTitle(){
    return this.addTask.get('taskTitle');
  }

  get taskDeadLine(){
    return this.addTask.get('taskDeadLine');
  }

  get taskStatus(){
    return this.addTask.get('taskStatus');
  }

  get taskDescription(){
    return this.addTask.get('taskDescription');
  }

  clicked(title:any){
    //alert(title);
    this.service.searchTask(title,this.email).subscribe(data=>{
      console.log(data);
      this.visibleTask=true;
      this.schtask=data;
    });
  }
  colorPriority(date:any){
    const newDate:Date=new Date(date);
    const timeDiff = newDate.getTime() - Date.now();
    let daysLeft = Math.floor((timeDiff / (1000 * 3600 * 24)));
    this.value = 100-daysLeft;
    this.msg=daysLeft+" days";
    if(daysLeft < 10){
      this.color='warn';
      return "red";
    }
    else if(daysLeft >= 10 && daysLeft < 20){
      this.color='accent'
      return "yellow";
    }
    else if(daysLeft >= 20 && daysLeft < 31){
      this.color='primary'
      return "green";
    }
    else{
      return "black";
    }
  }

  search:any={};
  schtask:any={};
  searchTask(){
    this.search=this.searchForm.value;
    console.log(this.search);
    this.service.searchTask(this.search,this.email).subscribe(data=>{
      console.log(data);
      if(data!=null){
        this._snackBar.open("Task Found", "OK");
        this.visibleTask=true;
        this.schtask=data;
      }
      else{
        alert("No such task found");
      }
    });
  }
  visibleForm=false;
  visibleTask=false;
  hide=true;

  loggedOut(){
    this.service.token="";
    this.cookie.delete("user");
    this.cookie.delete("token");
    this.router.navigate(["../login"]);
  }

  deleteTask(taskid:any){
    this.service.deleteTask(taskid).subscribe(data=>{
      console.log(data);
    });
    // let currentUrl = this.router.url;
    // this.router.navigate([currentUrl]);
    window.location.reload();
    //this.router.navigate(["../homePg/"+this.email]);
  }

  dragTask(task:any){
    this.service.addTask(task).subscribe(data=>{
      console.log(data.taskId);
    });
    this.deleteTask(task.taskId);
  }

  selected:number=0;
  updateTask(task:any){
    //console.log(task);
    this.task={taskTitle : task.taskTitle, taskDeadLine:task.taskDeadLine, taskStatus : task.taskStatus, taskDescription:task.taskDescription}
    this.visibleForm=true;
    this.addTask.setValue(this.task);
    this.selected=task.taskId;
    console.log(this.selected);
  }

  onSubmit(){
    this.task={taskTitle : this.taskTitle?.value, taskDeadLine:this.taskDeadLine?.value, taskStatus : this.taskStatus?.value, taskDescription:this.taskDescription?.value, taskAssignee:this.user}
    this.service.addTask(this.task).subscribe(data=>{
      console.log(data);
    });
    console.log(this.selected);

    if(this.task.taskId!=0){
     this.deleteTask(this.selected);
    }
    this.visibleForm=false;
    //this.router.navigate(["../homePg/"+this.email]);
    window.location.reload();
  }

  draggedTaskArray:any=[];
  draggedTask:any={};
  drop(event: CdkDragDrop<string[]>, position:String) {
    if (event.previousContainer === event.container) {
      moveItemInArray(event.container.data, event.previousIndex, event.currentIndex);
    } else {
      transferArrayItem(
        event.previousContainer.data,
        event.container.data,
        event.previousIndex,
        event.currentIndex,
      );
      this.draggedTaskArray=event.container.data;
      this.draggedTaskArray.forEach((element:any) => {
        this.draggedTask={
          taskId:element.taskId,
          taskTitle:element.taskTitle,
          taskDescription:element.taskDescription,
          taskDeadLine:element.taskDeadLine,
          taskAssignee:element.taskAssignee,
          taskStatus:position};
      this.dragTask(this.draggedTask);
      });
    }
  }
}
