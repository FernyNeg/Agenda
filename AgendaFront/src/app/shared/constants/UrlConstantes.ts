import { environment } from "src/environments/environment";

export const UrlConstantes = {
  GUARDA_CONTACTO: environment.apiUrl + "controller/contacto/insertaContacto.do",
  LEER_CONTACTOS: environment.apiUrl + "controller/contacto/leerContactos.do",
  ACTUALIZA_CONTACTO: environment.apiUrl + "controller/contacto/actualizaContacto.do",
}