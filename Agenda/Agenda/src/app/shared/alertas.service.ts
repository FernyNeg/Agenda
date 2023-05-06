import { Injectable } from '@angular/core';
import { ToastrService } from 'ngx-toastr';

@Injectable({
  providedIn: 'root'
})
export class AlertasService {
  constructor(
    public toastr: ToastrService
  ) { }
  errorServidor() {
    this.toastr.error(
      '<span class=" tim-icons icon-bell-55"></span> El servidor dejo de responder', "",
      { timeOut: 8000, enableHtml: true, closeButton: true, toastClass: "alert alert-danger alert-with-icon", positionClass: "toast-top-right" }
    );
  }
  mensajeError(encabezado: string) {
    this.toastr.error(
      '<span class=" tim-icons icon-alert-circle-exc "></span>', `${encabezado}`,
      { timeOut: 8000, enableHtml: true, closeButton: true, toastClass: "alert alert-danger alert-with-icon", positionClass: "toast-top-right" }
    );
  }
  mensajeSuccess(alerta: string) {
    this.toastr.success('<span class="tim-icons icon-bell-55" [data-notify]="icon"></span><b>Completado: </b>' + alerta, '', {
      timeOut: 8000,
      closeButton: true,
      enableHtml: true,
      toastClass: "alert alert-success alert-with-icon",
      positionClass: 'toast-top-right'
    });
  }
}