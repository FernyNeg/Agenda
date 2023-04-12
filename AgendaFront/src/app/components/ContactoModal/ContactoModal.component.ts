import { Component, OnInit, Inject } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { Contacto } from 'src/app/shared/models/Contacto';
import { ContactosService } from 'src/app/shared/security/contactos.service';

@Component({
  selector: 'app-ContactoModal',
  templateUrl: './ContactoModal.component.html',
  styleUrls: ['./ContactoModal.component.scss']
})
export class ContactoModalComponent implements OnInit {

  constructor(
    private dialog: MatDialogRef<ContactoModalComponent>,
    private service: ContactosService,
    @Inject(MAT_DIALOG_DATA) public datos: Contacto
  ) { }

  ngOnInit() {

  }

  CerrarEvent() {
    this.dialog.close();
  }

}
