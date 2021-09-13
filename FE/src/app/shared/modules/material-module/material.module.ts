import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MatTabsModule } from '@angular/material/tabs';
import { MatGridListModule } from '@angular/material/grid-list';
import { MatButtonModule } from '@angular/material/button';
@NgModule({
  declarations: [],
  imports: [CommonModule, MatTabsModule, MatGridListModule],
  exports: [MatTabsModule, MatGridListModule, MatButtonModule],
})
export class MaterialModule {}
