import { Component, OnInit } from '@angular/core';
import { TaskService } from '../task.service';

@Component({
  selector: 'app-archive',
  templateUrl: './archive.component.html',
  styleUrls: ['./archive.component.css']
})
export class ArchiveComponent implements OnInit {
    TaskService: any;
   
    

 

    constructor(private todo: TaskService) { }
  
    archiveTasks: any = []
  
    ngOnInit(): void {
      this.todo.getArchive().subscribe((data1: any) => {
        this.archiveTasks = data1
      })
    }
  
    delete(id: any) {
      this.todo.deleteArchive(id).subscribe((res: any) => {
        console.log("deleted");
        this.ngOnInit()
  
      }, (err: any) => {
  
        this.ngOnInit()
      })
    }
    moveToHome(data1: any) {
      this.archiveTasks.savetask(data1).subscribe(() => {
        alert("Moved back to Task")
        this.ngOnInit()
      })
    }
  
  
  }
  