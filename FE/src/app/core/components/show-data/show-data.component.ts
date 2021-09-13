import { ApplicationService } from '../../services/application.service';
import { Department } from './../../../shared/models/department';
import { Component, OnInit, OnChanges, SimpleChanges } from '@angular/core';

@Component({
  selector: 'app-show-data',
  templateUrl: './show-data.component.html',
  styleUrls: ['./show-data.component.scss'],
})
export class ShowDataComponent implements OnInit {
  departments: Department[];

  constructor(private service: ApplicationService) {}

  ngOnInit(): void {
    this.service.myData.subscribe((data) => {
      this.loadData();
    });
  }

  loadData() {
    this.service.getData().subscribe((response) => {
      this.departments = response.data;
    });
  }

  exportToExcel() {
    this.service.exportToExcel(this.departments);
  }
}
