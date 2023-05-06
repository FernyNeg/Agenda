import { environment } from "../../environments/environment";

export const UrlConstantes = {

  ActualizaContactoPorId: environment.apiUrl + 'contacto/actualizaContacto.do',
  ConsultaContactos: environment.apiUrl + 'contacto/leerContactos.do',
  ConsultaContactoPorId: environment.apiUrl + 'contacto/leerContactoPorId.do',

}