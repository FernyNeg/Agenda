import { Injectable } from '@angular/core';
import { ToastrService } from 'ngx-toastr';
import Swal from 'sweetalert2';

@Injectable({
  providedIn: 'root'
})
export class AlertasService {
  //#region Variables de alertas
  private PropConfirmAccion = {
    title: '¿Esta seguro de realizar esta acción?', text: "¡No puede deshacer esta acción!",
    buttonsStyling: false, showConfirmButton: true, showCancelButton: true, reverseButtons: true,
    confirmButtonText: 'Eliminar', cancelButtonText: 'Cancelar', customClass:
      { confirmButton: 'btn btn-info', cancelButton: 'btn btn-danger' }
  };
  //#endregion

  //#region Inicios

  constructor(
    public toastr: ToastrService
  ) { }
  //#endregion

  //#region Swal Alerts
  confirmacionAlert(callback) {
    return Swal.fire(this.PropConfirmAccion).then(res => { callback(res); });
  }
  deleteSuccess() {
    Swal.fire('Saved!', '', 'success')
  }
  //#endregion

  errorServidor() {
    this.toastr.error(
      '<span class=" tim-icons icon-bell-55"></span> El servidor dejo de responder', "",
      { timeOut: 8000, enableHtml: true, closeButton: true, toastClass: "alert alert-danger alert-with-icon", positionClass: "toast-top-right" }
    );
  }
  errorAlert(encabezado: string) {
    this.toastr.error(
      '<span class=" tim-icons icon-alert-circle-exc "></span>', `${encabezado}`,
      { timeOut: 8000, enableHtml: true, closeButton: true, toastClass: "alert alert-danger alert-with-icon", positionClass: "toast-top-right" }
    );
  }
  successAlert(alerta: string) {
    this.toastr.success(
      '<span class="tim-icons icon-bell-55" [data-notify]="icon"></span><b>Completado: </b>' + alerta, '',
      { timeOut: 8000, closeButton: true, enableHtml: true, toastClass: "alert alert-success alert-with-icon", positionClass: 'toast-top-right' }
    );
  }
  warningAlert(alerta: string) {
    this.toastr.success(
      '<span class="tim-icons icon-bell-55" [data-notify]="icon"></span><b>Completado: </b>' + alerta, '',
      { timeOut: 8000, closeButton: true, enableHtml: true, toastClass: "alert alert-success alert-with-icon", positionClass: 'toast-top-right' }
    );
  }

}