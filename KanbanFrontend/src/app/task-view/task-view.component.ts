import { Component, OnInit, Input } from '@angular/core';
import { TaskService } from '../task.service';

@Component({
  selector: 'app-task-view',
  templateUrl: './task-view.component.html',
  styleUrls: ['./task-view.component.css']
})
export class TaskViewComponent implements OnInit {

  @Input() item='';
  constructor(private service:TaskService) { }

  task:any={};
  ngOnInit(): void {
    this.task=this.item;
  }

  deleteTask(taskid:any){
    this.service.deleteTask(taskid).subscribe(data=>{
      console.log(data);
    });
    //window.location.reload();
  }

}
