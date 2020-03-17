import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { MaterialModule } from '../material.module';
import { FlexLayoutModule } from '@angular/flex-layout';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';

@NgModule({
  declarations: [],
  imports: [CommonModule, FormsModule, MaterialModule, FlexLayoutModule, BrowserAnimationsModule],
  exports: [CommonModule, FormsModule, MaterialModule, FlexLayoutModule, BrowserAnimationsModule],
})
export class SharedModule {}
