import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { MatDialogRef } from '@angular/material/dialog';
import { ServicesService } from '../services/services.service';


@Component({
  selector: 'app-create-update-status',
  templateUrl: './create-update-status.component.html',
  styleUrls: ['./create-update-status.component.css']
})
export class CreateUpdateStatusComponent implements OnInit {

  constructor(private services: ServicesService, public dialogRef: MatDialogRef<CreateUpdateStatusComponent>) { }

  status:any;
  ArrayOfList:any;
  newStatus: any = {};
  count: number = 0;
  stat:any;
  statusForm:any;

  ngOnInit(): void 
  {
    this.stat= this.services.statusData;
    console.log("old status");
    
    console.log(this.stat);
    

    this.statusForm = new FormGroup({
      statusId: new FormControl(''),
      statusName: new FormControl(''),
      spaceName: new FormControl(''),
      email: new FormControl(''),
    })

    this.statusForm.patchValue({
      statusId: this.stat.statusId,
      statusName: this.stat.statusName,
      spaceName: this.stat.spaceName,
      email: this.stat.email,
    })
  }


  statusUpdate(data:any)
  {
    console.log("new status");
    
    console.log(data);
    
    this.ArrayOfList = this.services.List;
    console.log("Array of List");
    console.log(this.ArrayOfList);
    
    this.status = this.services.status;
    console.log(this.status);
    
    for (let i = 0; i < this.status.length; i++) 
    {
      if (this.status[i].statusName == data.statusName)
      {
        this.count++;
      }
    }
    console.log(this.count);
    


    if (this.count < 1) 
    {
      for (let i = 0; i < this.ArrayOfList.length; i++) 
      {
        for (let j = 0; j < this.ArrayOfList[i].length; j++) 
        {

          if (this.ArrayOfList[i][j].statusName == this.stat.statusName) 
          {
            this.ArrayOfList[i][j].statusName = data.statusName;
            console.log(this.ArrayOfList[i][j]);
            this.services.updateTaskById(this.ArrayOfList[i][j]).subscribe(data => {
              console.log(data);
            })
          }
        }
      }
    }
    // else
    // {
    //   alert("Status Already Present")
      
    // }



    if (this.count < 1) 
    {
      this.stat.statusName = data.statusName;


      if (data.statusDescription != undefined) 
      {
        this.stat.statusDescription = data.statusDescription;
      }


      this.services.saveStatus(data).subscribe(data => {
        this.dialogRef.close({data:"1"})
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
  }

  setNull()
  {
    this.statusForm.patchValue({
      statusId: this.stat.statusId,
      statusName: "",
      spaceName: this.stat.spaceName,
      email: this.stat.email,
    })

  }

}
