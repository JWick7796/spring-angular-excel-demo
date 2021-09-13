import { Department } from '../../shared/models/department';
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, BehaviorSubject } from 'rxjs';
import * as FileSaver from 'file-saver';
import * as XLSX from 'xlsx';

@Injectable({
  providedIn: 'root',
})
export class ApplicationService {
  baseUrl: string = 'http://localhost:8077';

  myData = new BehaviorSubject(false);

  constructor(private http: HttpClient) {}

  uploadFile(file: File): Observable<any> {
    const formdata: FormData = new FormData();
    formdata.append('file', file);
    return this.http.post(this.baseUrl + '/departments', formdata);
  }

  getData(): Observable<any> {
    return this.http.get(this.baseUrl + '/departments');
  }

  reloadData() {
    this.myData.next(true);
  }

  exportToExcel(data: Department[]) {
    const workbook: XLSX.WorkBook = {
      Sheets: {},
      SheetNames: [],
    };

    for (let index = 0; index < data.length; index++) {
      const worksheet: XLSX.WorkSheet = XLSX.utils.json_to_sheet(
        data[index].employees
      );
      worksheet.A1.v = 'Id';
      worksheet.B1.v = 'Name';
      workbook.Sheets[data[index].name] = worksheet;
      workbook.SheetNames.push(data[index].name);
    }

    XLSX.writeFile(workbook, 'Data.xlsx');
  }
}
