import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

import { ConsultaList } from "./../shared/models/consultaList.model";
import { Contacto } from "./../shared/models/contacto.model";
import { UrlConstantes } from '../shared/urlConstantes';

@Injectable({
  providedIn: 'root'
})

export class ContactosService {

  constructor(private Conexion: HttpClient) { }

  getContactos(param: ConsultaList<Contacto>) {
    return this.Conexion.post<ConsultaList<Contacto>>(UrlConstantes.ConsultaContactos, param);
  }

  getContactoPorId(contacto: Contacto) {
    return this.Conexion.post<Contacto>(UrlConstantes.ConsultaContactoPorId, contacto);
  }

  setContactoPorId(param: Contacto) {
    return this.Conexion.post<Contacto>(UrlConstantes.ActualizaContactoPorId, param);
  }

}