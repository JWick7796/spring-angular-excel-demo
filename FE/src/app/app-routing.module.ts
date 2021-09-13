import { ShowDataComponent } from './core/components/show-data/show-data.component';
import { AcceptFileComponent } from './core/components/accept-file/accept-file.component';
import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

const routes: Routes = [
  {
    path: 'upload',
    component: AcceptFileComponent,
  },
  {
    path: 'show',
    component: ShowDataComponent,
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
