import { Component, OnInit } from '@angular/core';
import { BreakpointObserver, Breakpoints } from '@angular/cdk/layout';
import { Observable } from 'rxjs';
import { map, shareReplay } from 'rxjs/operators';
import { ServicesService } from '../services/services.service';
import { Router } from '@angular/router';
import { MatDialog } from '@angular/material/dialog';
import { ProfileComponent } from '../profile/profile.component';
import { CdkDragDrop, moveItemInArray, transferArrayItem } from '@angular/cdk/drag-drop';
import { UpdateTaskComponent } from '../update-task/update-task.component';
import { CreateUpdateStatusComponent } from '../create-update-status/create-update-status.component';

@Component({
  selector: 'app-homepage-navigation',
  templateUrl: './homepage-navigation.component.html',
  styleUrls: ['./homepage-navigation.component.css']
})
export class HomepageNavigationComponent implements OnInit
{

  isHandset$: Observable<boolean> = this.breakpointObserver.observe(Breakpoints.Handset)
    .pipe(
      map(result => result.matches),
      shareReplay()
    );

  spaces: any;
  space: any = {};
  userName:any;

  status: any = [];
  newStatus: any = {};
  num: number = 0;

  tasks: any = {};
  newTask: any = {};
  viewTask: number = 0;
  number: number = 0;

  statusBySpace: any = [];
  taskCount: any = 0;
  noOfTasks: any = [];
  limitingCount: any = 4;
  totalCount: any = 0;
  step!: number;
  ArrayOfList: any = [];
  taskObject: any = {};
  dummytask: any;
  currentItemsToShow: any;
  currentDate=new Date();

  constructor(private breakpointObserver: BreakpointObserver, private services: ServicesService, private router: Router, public dialog: MatDialog) { }

  ngOnInit() 
  { 
    this.userName = this.services.userName;
    this.services.getSpaceByEmail().subscribe(data => {
      this.spaces = data;
      console.log(this.spaces);
    },
      error => {
        console.log(error);
      });

  }

  createSpace() 
  {
    this.space.email = this.services.email;
   

    this.services.saveSpace(this.space).subscribe(data => {

      if (data == null) {
        alert("Space already present!!! Try different name.")
      }
      this.ngOnInit();
      this.ngOnInit();
      this.space={};
    },
      error => {
        console.log(error);
        alert(error.error)

      })
  }

  deleteSpace(data: any) 
  {

    this.services.deleteSpace(data.spaceName, data.email).subscribe(data => {

      this.ngOnInit();

      for (let i = 0; i < this.status.length; i++) {
        this.deleteStatus(this.status[i]);
      }

    }, error => {
      console.log(error);

    })

  }

  getStatus(space: any) 
  {
    this.services.space = space;
   
    console.log(this.userName);
    
    this.services.getstatus(this.services.email, this.services.space).subscribe(data => {
      this.status = data;
      this.services.status = data;
      this.num = 1;
      this.getTasks(space);
      
    },
      error => {
        console.log(error);
      })

  }

  viewStatusForm() 
  {
    this.num = 2;
    this.ngOnInit();
  }

  cancelMethod() 
  {
    this.num = 1;
    this.ngOnInit();
  }

  createStatus() 
  {

    for (let i = 0; i < this.status.length; i++) 
    {
      if (this.status[i].statusName == this.newStatus.statusName) {
        this.count++
      }
    }

    if (this.count < 1) 
    {
      this.newStatus.email = this.services.email;
      this.newStatus.spaceName = this.services.space.spaceName;
      this.services.saveStatus(this.newStatus).subscribe(data => {
        this.newTask = {};
        this.dummytask = data;
        this.newTask.email = this.dummytask.email;
        this.newTask.spaceName = this.dummytask.spaceName;
        this.newTask.statusName = this.dummytask.statusName;
        this.newTask.taskId = "";

        this.services.addTask(this.newTask).subscribe();
        this.getStatus(this.services.space);

      },
        error => {
          console.log(error);

        })
    }
    else 
    {
      alert("Status Already Present")

    }

    this.count = 0;
    this.newStatus = {};
  }


  count: number = 0;

  updateStatus(data: any) 
  {
    console.log(data.statusName);
   
    this.services.statusData = data;
    this.services.List = this.ArrayOfList;

    const dialRef = this.dialog.open(CreateUpdateStatusComponent, {});

    dialRef.afterClosed().subscribe(data => {
      if (data.data == '1') 
      {
        this.getStatus(this.services.space)
      }

    })

  }


  deleteStatus(data: any) 
  {
    // this.newStatus.email = this.services.email;
    // this.newStatus.spaceName = this.services.space.spaceName;

    this.services.deleteStatus(data).subscribe(result => {
      this.getStatus(this.services.space);
    },
      error => {
        console.log(error);

      })

    console.log(this.ArrayOfList);

    for (let i = 0; i < this.ArrayOfList.length; i++)
    {
      for (let j = 0; j < this.ArrayOfList[i].length; j++) 
      {
        if (this.ArrayOfList[i][j].statusName == data.statusName) 
        {
          this.services.deleteTaskById(this.ArrayOfList[i][j].taskId).subscribe();
        }
      }
    }

  }

  getTasks(data: any) 
  {

    this.ArrayOfList = [];
    this.taskObject = {};

    this.services.getTasks(data).subscribe((data: any) => {

      for (let i = 0; i < this.status.length; i++) 
      {
        let taskByStatus: any = [];
        for (let j = 0; j < data.length; j++) 
        {
          if (this.status[i].statusName == data[j].statusName) 
          {
            taskByStatus.push(data[j]);
          }
        }

        this.ArrayOfList.push(taskByStatus);
        console.log(this.ArrayOfList);
      }
      this.tasks = data;

    }, error => {
      console.log(error);
    })

  }

  startDate!:string;

  addTask(data: any) 
  {
    // this.statusBySpace = [];
    // this.noOfTasks = [];
    // this.totalCount = 0;
    console.log(data[0].statusName);
    this.newTask.email = this.services.email;
    this.newTask.spaceName = this.services.space.spaceName;
    this.newTask.statusName = data[0].statusName;
    this.newTask.taskId = "";
   
    // console.log(this.newTask);
    // for (let index = 0; index <this.status.length; index++)
    // {
    //   const element = this.status[index];
    //   this.statusBySpace.push(element.statusName);
    // }
    // console.log("array of status");
    // console.log(this.statusBySpace);
    // this.services.getTaskByEnailAndSpace(this.newTask.email,this.newTask.spaceName).subscribe((tasksByEmailAndSpace:any) =>{
    //   console.log("task by email mand space");
    //   console.log(tasksByEmailAndSpace);
    //   for (let index = 0; index < this.statusBySpace.length; index++)
    //   {
    //     const stat = this.statusBySpace[index];
    //     console.log("status --> "+stat);
    //     for (let index = 0; index < tasksByEmailAndSpace.length; index++)
    //     {
    //       const element = tasksByEmailAndSpace[index].statusName;
    //       console.log("element ---->"+element);
    //       if(stat == element)
    //       {
    //         this.taskCount++;
    //       }
    //     }
    //     this.noOfTasks.push(this.taskCount);
    //     console.log("task Count ---> "+this.taskCount);
    //     this.taskCount = 0;
    //     console.log(" ary of tasks ---> "+this.noOfTasks);
    //   }
    //   for (let index = 0; index < this.noOfTasks.length; index++)
    //   {
    //     const element = this.noOfTasks[index];
    //     console.log(element);
    //     if(element >= this.limitingCount)
    //     {
    //       this.totalCount++
    //     }
    //   }
    //   console.log("tatal count ---> "+this.totalCount);
    //   if(this.totalCount == 0)
    //   {
    //     console.log("this.totalCount");
    //     console.log(this.totalCount);
    //     this.services.addTask(this.newTask).subscribe(data=>{
    //       console.log(data);
    //       this.newTask = {};
    //       this.getStatus(this.services.space);
    //     },
    //     error=>{
    //       console.log(error);
    //     })
    //   }
    //   else
    //   {
    //     alert("Your Tasks Reached Maximum Limit...");
    //   }
    // });

    console.log(this.newTask);

    this.services.addTask(this.newTask).subscribe(data => {
      this.newTask = {};
      this.getStatus(this.services.space);
    },
      error => {
        console.log(error);
      })
  }

  viewTaskForm() 
  {
    this.viewTask = 2;
  }

  openProfile() 
  {
    this.dialog.open(ProfileComponent, {});
  }

 
  nullValues() 
  {
    this.newTask = {};
  }

  drop(event: CdkDragDrop<any>) 
  {

    if (event.previousContainer === event.container) 
    {
      console.log("inside if");

      console.log(event);

      moveItemInArray(event.container.data, event.previousIndex, event.currentIndex);
    }
    else 
    {
      console.log("inside else");
      console.log(event.container.data[0].statusName);
      console.log(event.item.data);

      this.newTask = {};

      this.newTask = event.item.data;
      this.newTask.statusName = event.container.data[0].statusName;

      this.services.updateTaskById(this.newTask).subscribe(data => {
        console.log(data);
        this.getStatus(this.services.space)
      })

      transferArrayItem(
        event.previousContainer.data,
        event.container.data,
        event.previousIndex,
        event.currentIndex,
      );
    }
  }

  updateTask(data: any) 
  {
    this.services.updatedTask = data;
    const dialRef = this.dialog.open(UpdateTaskComponent, {});

    dialRef.afterClosed().subscribe(data => {

      if (data.data == '1') 
      {
        this.getStatus(this.services.space)
      }
    })
  }

  deleteTask(task: any) 
  {

    this.services.deleteTaskById(task.taskId).subscribe(response => {
      // this.getStatus(this.services.space)
    },
      error => {
        if (error.status == 200) {
          alert("Deleted successfully")
          this.getStatus(this.services.space)
        }
        else {
          alert("Task in not deleted!!!!!!! Try Again")
        }

      })

  }

  isArray(item: any): boolean 
  {
    return Array.isArray(item);
  }


}
