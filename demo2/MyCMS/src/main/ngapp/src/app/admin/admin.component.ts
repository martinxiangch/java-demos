import { Component, OnInit } from '@angular/core';
import { Tablelist } from '../entities';
import { TablemanagerService } from '../services/tablemanager.service';

@Component({
  selector: 'app-admin',
  templateUrl: './admin.component.html',
  styleUrls: ['./admin.component.css']
})
export class AdminComponent implements OnInit {

  private tableName:string
  private columnList:string
  constructor(
  private tablemanager:TablemanagerService
  ) { 
    
  }

  ngOnInit() {
    this.initData()
  }


  initData(){
    this.tablemanager.getTableList()
    .subscribe(res=>
      {
        this.tablemanager.tablelist=res;
        console.log(res)
      })
  }

   add(){
    this.tablemanager.add(this.tableName,this.columnList)
    .subscribe(res=>
      {
        console.log(res)
        this.initData()
      }
    )
    this.tableName=this.columnList="";
   }

   delete(id:number){
     
    this.tablemanager.delete(id)
        .subscribe(res=>{
          console.log(res)
          this.initData()
        })
   }

}
