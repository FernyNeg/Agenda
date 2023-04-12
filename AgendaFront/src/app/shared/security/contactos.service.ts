import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Contacto } from '../models/Contacto';
import { UrlConstantes } from '../constants/UrlConstantes';
import { ConsultaList } from '../models/ConsultaList';

@Injectable({
  providedIn: 'root'
})

export class ContactosService {

  private headers = new HttpHeaders();

  constructor(private Conexion: HttpClient) {
    this.headers.set("content-type", "application/json")
  }

  getContactos(params: ConsultaList<Contacto>) {
    return this.Conexion.post<ConsultaList<Contacto>>(UrlConstantes.LEER_CONTACTOS, params, { headers: this.headers });
  }

  setGuardaContacto(contato: Contacto) {
    return this.Conexion.post<boolean>(UrlConstantes.GUARDA_CONTACTO, contato);
  }

  setCambiaContacto(contato: Contacto) {
    return this.Conexion.post<boolean>(UrlConstantes.ACTUALIZA_CONTACTO, contato);
  }

} 