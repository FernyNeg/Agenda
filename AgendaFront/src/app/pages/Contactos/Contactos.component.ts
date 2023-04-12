import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { ContactoModalComponent } from 'src/app/components/ContactoModal/ContactoModal.component';

import { ConsultaList } from 'src/app/shared/models/ConsultaList';
import { Contacto } from 'src/app/shared/models/Contacto';
import { ContactosService } from 'src/app/shared/security/contactos.service';

@Component({
  selector: 'app-Contactos',
  templateUrl: './Contactos.component.html',
  styleUrls: ['./Contactos.component.scss'],
  providers: [ContactosService]
})
export class ContactosComponent implements OnInit {

  //#region Variales
  listado: ConsultaList<Contacto> = new ConsultaList<Contacto>();
  //#endregion

  //#region Inicios
  constructor(
    private service: ContactosService,
    private dialog: MatDialog
  ) { }
  ngOnInit(): void {
    this.listado.list = [];
    this.listado.param = "";
    this.getContactosService();
  }
  //#endregion

  //#region Metodos
  openDialog(contacto: Contacto) {
    this.dialog.open(ContactoModalComponent,
      {
        panelClass: "custom-modalbox",
        maxHeight: '800px',
        maxWidth: '800px',
        disableClose: true,
        data: contacto
      }
    );
  }
  //#endregion

  //#region Servicios
  getContactosService(): void {
    this.service.getContactos(this.listado).subscribe(res => {
      this.listado.list = res.list;
    });
  }
  //#endregion

}
