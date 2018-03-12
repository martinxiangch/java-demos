import { Injectable } from '@angular/core';
import { Tablelist } from "../entities";

import { Http, Response, Headers, RequestOptions } from '@angular/http';
import { Observable } from 'rxjs/Rx';

// Import RxJs required methods
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/catch';

@Injectable()
export class TablemanagerService {
  private index: number = 1
  public tablelist: Array<Tablelist> = new Array<Tablelist>()

  headers: Headers;
  options: RequestOptions;
  private cmsUrl = 'http://localhost:8080/admin';

  constructor(
    private http: Http

  ) {
    this.headers = new Headers({ 'Content-Type': 'application/json' });
    this.options = new RequestOptions({ headers: this.headers });
  }

  add(name: string, columnlist: string) {

    let tlist = { tableName: name, columnlist: columnlist.split(","), tid: this.index++, createTime: new Date().toLocaleDateString() }

    let url = this.cmsUrl + "/insertTable"
    let bodystr = JSON.stringify(tlist)
    return this.http.post(url, bodystr, this.options)
      .map((res: Response) => {
        console.log(res)
        return JSON.parse(res.text())
      })
      .catch((err: any) => Observable.throw(err || "Server error"))

  }

  delete(id: number) {
    let url = this.cmsUrl + "/deleteTable"
    return this.http.post(url, id, this.options)
      .map((res: Response) => {
        console.log(res)
        var obj= JSON.parse(res.text())
        return obj
      })
      .catch((err: any) => Observable.throw(err || "Server error"))
  }

  removeFromList(id: number) {
    let index = this.tablelist.findIndex(item => item.tid == id)
    this.tablelist.splice(index, 1)
  }

  getTableList() {
    let url = this.cmsUrl + "/getall"
    return this.http.get(url)
      .map((res: Response) => res.json() as Tablelist[])
      .catch((err: any) => Observable.throw(err.json().error || "Server error"))
  }

} 
