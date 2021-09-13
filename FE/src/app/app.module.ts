import { MaterialModule } from './shared/modules/material-module/material.module';
import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { ShowDataComponent } from './core/components/show-data/show-data.component';
import { AcceptFileComponent } from './core/components/accept-file/accept-file.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { DropZoneDirective } from './shared/directives/drop-zone.directive';
import { HttpClientModule } from '@angular/common/http';
import { ToastrModule } from 'ngx-toastr';

@NgModule({
  declarations: [
    AppComponent,
    ShowDataComponent,
    AcceptFileComponent,
    DropZoneDirective,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    MaterialModule,
    BrowserAnimationsModule,
    HttpClientModule,
    ToastrModule.forRoot(),
  ],
  providers: [],
  bootstrap: [AppComponent],
})
export class AppModule {}
