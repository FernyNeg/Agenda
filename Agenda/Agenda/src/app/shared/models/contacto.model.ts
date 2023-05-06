import { ClaseBean } from "./ClaseBean.model";

export class Contacto extends ClaseBean {
  idContacto: number;
  nombre: string;
  direccion: string;
  correo: string;
  usuario: string;
  aPaterno: string;
  aMaterno: string;
  ciudad: string;
  pais: string;
  codPostal: number;
  notas: string;
}