import { ApplicationService } from './core/services/application.service';
import {
  Component,
  ViewEncapsulation,
  Output,
  EventEmitter,
} from '@angular/core';
import { ThemePalette } from '@angular/material/core';
@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss'],
  encapsulation: ViewEncapsulation.None,
})
export class AppComponent {
  constructor(private service: ApplicationService) {}

  tabChange() {
    this.service.reloadData();
  }

  title = 'excel-poc';
}
