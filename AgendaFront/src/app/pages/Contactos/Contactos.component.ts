import { Component, OnInit } from '@angular/core';
import { ConsultaList } from 'src/app/shared/models/ConsultaList';
import { Contacto } from 'src/app/shared/models/Contacto';
import { ContactosService } from 'src/app/shared/security/contactos.service';

@Component({
  selector: 'app-Contactos',
  templateUrl: './Contactos.component.html',
  styleUrls: ['./Contactos.component.scss']
})
export class ContactosComponent implements OnInit {

  listado: ConsultaList<Contacto>;

  constructor(
    private service: ContactosService
  ) { }

  ngOnInit(): void { 
    this.listado.list = [];
    this.getContactosService();
  }

  getContactosService(): void {
    this.service.getContactos(this.listado).subscribe(res => {
      this.listado.list = res.list;
    });
  }

}
