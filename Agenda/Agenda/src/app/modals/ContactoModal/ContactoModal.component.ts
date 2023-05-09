import { Component, OnInit, Inject } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { ContactosService } from 'src/app/security/contactos.service';
import { AlertasService } from 'src/app/shared/alertas.service';
import { CodRetorno } from 'src/app/shared/enums/CodRetorno.enum';
import { Contacto } from 'src/app/shared/models/contacto.model';

@Component({
  selector: 'app-ContactoModal',
  templateUrl: './ContactoModal.component.html',
  styleUrls: ['./ContactoModal.component.scss']
})
export class ContactoModalComponent implements OnInit {

  contacto: Contacto = new Contacto();
  valor = false;
  FocosArr: Array<Array<boolean>>;

  inicioFocos() {
    this.FocosArr = [
      [false, false],
      [false, false],
      [false, false]
    ];
  }

  public contactoForm: FormGroup;

  InicontactoForm() {
    this.contactoForm = new FormGroup({
      idContacto: new FormControl(''),
      nombre: new FormControl('', [Validators.required, Validators.minLength(2)]),
      aPaterno: new FormControl(''),
      aMaterno: new FormControl(''),
      direccion: new FormControl(''),
      correo: new FormControl('', [Validators.required, Validators.email]),
      usuario: new FormControl('', [Validators.required, Validators.minLength(6)]),
      ciudad: new FormControl(''),
      pais: new FormControl(''),
      codPostal: new FormControl(''),
      notas: new FormControl(''),
    });
  }

  get getcontactoForm() {
    return this.contactoForm.controls;
  }

  constructor(
    private dialog: MatDialogRef<ContactoModalComponent>,
    @Inject(MAT_DIALOG_DATA) private Data: any,
    private alertas: AlertasService,
    private service: ContactosService,
  ) { }

  ngOnInit() {
    this.inicioFocos();
    this.InicontactoForm();
    if (this.Data['edit']) {
      this.contactoForm.patchValue(this.Data.info);
    }
  }

  //#region Eventos
  setContactoEvent() {
    this.valor = true;
    if (this.contactoForm.invalid) {
      this.alertas.errorAlert('Faltan campos por llenar del contacto');
      return;
    }
    this.contacto = this.contactoForm.getRawValue();
    this.setContactoService();
  }
  CerrarEvent() { this.dialog.close(null); }
  //#endregion

  //#region Servicios
  setContactoService() {
    this.service.setContactoPorId(this.contacto).subscribe(res => {
      console.log(res.codRetorno);
      res.codRetorno == CodRetorno.CEROS ?
        this.dialog.close(true) :
        this.alertas.errorAlert(res.mensaje);
    });
  }
  //#endregion

}
