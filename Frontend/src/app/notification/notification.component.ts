import { Component, OnInit } from '@angular/core';
import { ServicesService } from '../services/services.service';

@Component({
  selector: 'app-notification',
  templateUrl: './notification.component.html',
  styleUrls: ['./notification.component.css']
})
export class NotificationComponent implements OnInit 
{

  tasks:any = {};
  notifications :any=[];
  total:any=0;
  cdate = new Date();

  constructor(private service : ServicesService)
  {
    
  }


  ngOnInit(): void
  {
    this.service.getTaskByEmail().subscribe(task =>{
      
      this.tasks = task;
      console.log(this.tasks);

      
      for (let index = 0; index < this.tasks.length; index++) 
      {
        const element = this.tasks[index];
        
        let currentDate = new Date();
        let dateEnd = new Date(element.endDate);

        console.log(dateEnd);
        
        let noOfDays = Math.floor(( Date.UTC(dateEnd.getFullYear(), dateEnd.getMonth(), dateEnd.getDate()) - Date.UTC(currentDate.getFullYear(), currentDate.getMonth(), currentDate.getDate()) ) /(1000 * 60 * 60 * 24));
    
        console.log(noOfDays);

        if(noOfDays < -5 && element.taskName != null && element.startDate != null && element.endDate != null && element.statusName!= "Completed")
        {
          this.service.getNotificationById(9).subscribe(response=>{

            let data:any={};

            data.notification = response;        
            data.taskName = element.taskName;
            data.expiryDate = element.endDate;
            data.space = element.spaceName;
            data.statusName = element.statusName;

            this.notifications.push(data);
            this.total++;
  
          })
        }
        if(noOfDays == -5 && element.taskName != null && element.startDate != null && element.endDate != null && element.statusName!= "Completed")
        {
          this.service.getNotificationById(8).subscribe(response=>{

            let data:any={};

            data.notification = response;        
            data.taskName = element.taskName;
            data.expiryDate = element.endDate;
            data.space = element.spaceName;
            data.statusName = element.statusName;


            this.notifications.push(data);
            this.total++;

          })
        }
        if(noOfDays == -4 && element.taskName != null && element.startDate != null && element.endDate != null && element.statusName!= "Completed")
        {
          this.service.getNotificationById(7).subscribe(response=>{

            let data:any={};

            data.notification = response;        
            data.taskName = element.taskName;
            data.expiryDate = element.endDate;
            data.space = element.spaceName;
            data.statusName = element.statusName;


            this.notifications.push(data);
            this.total++;

          })
        }
        if(noOfDays == -3 && element.taskName != null && element.startDate != null && element.endDate != null && element.statusName!= "Completed")
        {
          this.service.getNotificationById(6).subscribe(response=>{

            let data:any={};

            data.notification = response;        
            data.taskName = element.taskName;
            data.expiryDate = element.endDate;
            data.space = element.spaceName;
            data.statusName = element.statusName;


            this.notifications.push(data);
            this.total++;

          })
        }
        else if(noOfDays == -2 && element.taskName != null && element.startDate != null && element.endDate != null && element.statusName!= "Completed")
        {
          this.service.getNotificationById(5).subscribe(response=>{

            let data:any={};

            data.notification = response;        
            data.taskName = element.taskName;
            data.expiryDate = element.endDate;
            data.space = element.spaceName;
            data.statusName = element.statusName;


            this.notifications.push(data);
            this.total++;
 
          })
        }
        else if(noOfDays == -1 && element.taskName != null && element.startDate != null && element.endDate != null && element.statusName!= "Completed")
        {
          this.service.getNotificationById(4).subscribe(response=>{
            
            let data:any={};
            data.notification = response;        
            data.taskName = element.taskName;
            data.expiryDate = element.endDate;
            data.space = element.spaceName;
            data.statusName = element.statusName;


            this.notifications.push(data);
            this.total++;
       
          })
        }
        else if(noOfDays == 0 && element.taskName != null && element.startDate != null && element.endDate != null && element.statusName!= "Completed")
        {
          this.service.getNotificationById(3).subscribe(response=>{
            let data:any={};
            data.notification = response;        
            data.taskName = element.taskName;
            data.expiryDate = element.endDate;
            data.space = element.spaceName;
            data.statusName = element.statusName;


            this.notifications.push(data);
            this.total++;
      
          })
        }
        else if(noOfDays == 1 && element.taskName != null && element.startDate != null && element.endDate != null && element.statusName!= "Completed")
        {
          this.service.getNotificationById(2).subscribe(response=>{
            let data:any={};
            data.notification = response;        
            data.taskName = element.taskName;
            data.expiryDate = element.endDate;
            data.space = element.spaceName;
            data.statusName = element.statusName;


            this.notifications.push(data);
            this.total++;
          
          })
        }
        else if(noOfDays == 2 && element.taskName != null && element.startDate != null && element.endDate != null && element.statusName!= "Completed")
        {
          this.service.getNotificationById(1).subscribe(response=>{
            let data:any={};
            data.notification = response;        
            data.taskName = element.taskName;
            data.expiryDate = element.endDate;
            data.space = element.spaceName;
            data.statusName = element.statusName;


            this.notifications.push(data);
            this.total++;
        
          })
        }

      }
    
      console.log(this.notifications);
      


      setTimeout(() => {

        console.log(this.total);
        this.service.sendEmailNotification(this.total).subscribe(response=>{
          console.log(response);
          
        })  

      }, 10000);
      
    })

  }

}
