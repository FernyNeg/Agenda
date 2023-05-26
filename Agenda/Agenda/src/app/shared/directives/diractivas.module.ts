import { NgModule } from '@angular/core';
import { NumeroEnteroDirective } from './SoloNums.directive';
/* import { HTTP_INTERCEPTORS } from '@angular/common/http'; */
/* import { JwtInterceptor } from '../../security/helper/jwt.interceptor'; */
/* import { GlobalHttpInterceptorService } from '../../security/helper/globalhttp.interceptor'; */
/* import { SpinnerInterceptor } from '../../shared/interceptors/spinner.interceptor'; */

@NgModule({
  declarations: [NumeroEnteroDirective],
  imports: [],
  exports: [NumeroEnteroDirective],
  providers: [
    /* { provide: HTTP_INTERCEPTORS, useClass: JwtInterceptor, multi: true }, */
    /* { provide: HTTP_INTERCEPTORS, useClass: GlobalHttpInterceptorService, multi: true }, */
    /* { provide: HTTP_INTERCEPTORS, useClass: SpinnerInterceptor, multi: true, }, */
  ]
})
export class DirectivasModule{}