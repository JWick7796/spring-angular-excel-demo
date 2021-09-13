import { ApplicationService } from '../../services/application.service';
import { Component, OnInit } from '@angular/core';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-accept-file',
  templateUrl: './accept-file.component.html',
  styleUrls: ['./accept-file.component.scss'],
})
export class AcceptFileComponent implements OnInit {
  isHovering: boolean = false;
  selectedFile: File = null;

  constructor(
    private service: ApplicationService,
    private toastr: ToastrService
  ) {}

  ngOnInit(): void {}

  changeIsHover(isHovering: boolean) {
    this.isHovering = isHovering;
  }

  fileDropped(event: FileList) {
    this.selectedFile = event.item(0);
  }

  fileUpload() {
    this.service.uploadFile(this.selectedFile).subscribe(
      (response) => {
        this.toastr.success(response.message, 'Success');
        this.selectedFile = null;
      },
      (err) => {
        this.toastr.error(err.error.error.message, 'Error', {
          timeOut: 5000,
        });
        this.selectedFile = null;
      }
    );
  }
}
