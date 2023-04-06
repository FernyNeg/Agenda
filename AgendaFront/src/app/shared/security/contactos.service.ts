import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Contacto } from '../models/Contacto';
import { UrlConstantes } from '../constants/UrlConstantes';
import { ConsultaList } from '../models/ConsultaList';

@Injectable({
  providedIn: 'root'
})

export class ContactosService {

  constructor(private Conexion: HttpClient) { }

  getContactos(obj: ConsultaList<Contacto>) {
    return this.Conexion.post<ConsultaList<Contacto>>(UrlConstantes.LEER_CONTACTOS, obj);
  }

  setGuardaContacto(contato: Contacto) {
    return this.Conexion.post<boolean>(UrlConstantes.GUARDA_CONTACTO, contato);
  }
  
  setCambiaContacto(contato: Contacto) {
    return this.Conexion.post<boolean>(UrlConstantes.ACTUALIZA_CONTACTO, contato);
  }

} 