import { NgModule } from '@angular/core';
import { ContactoModalComponent } from './ContactoModal/ContactoModal.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatDialogModule } from "@angular/material/dialog";
import { CommonModule } from '@angular/common';
/* import { HTTP_INTERCEPTORS } from '@angular/common/http'; */
/* import { JwtInterceptor } from '../../security/helper/jwt.interceptor'; */
/* import { GlobalHttpInterceptorService } from '../../security/helper/globalhttp.interceptor'; */
/* import { SpinnerInterceptor } from '../../shared/interceptors/spinner.interceptor'; */

@NgModule({
  declarations: [
    ContactoModalComponent
  ],
  imports: [
    BrowserAnimationsModule,
    ReactiveFormsModule,
    FormsModule,
    CommonModule,
    MatDialogModule
  ],
  exports: [
    ContactoModalComponent
  ],
  providers: [

    /* { provide: HTTP_INTERCEPTORS, useClass: JwtInterceptor, multi: true }, */
    /* { provide: HTTP_INTERCEPTORS, useClass: GlobalHttpInterceptorService, multi: true }, */
    /* { provide: HTTP_INTERCEPTORS, useClass: SpinnerInterceptor, multi: true, }, */
  ]
})
export class ModalsModule { }