import { StatusContactoEnum } from "../enums/StatusContactoEnum";

export class Contacto {
  idContacto: number;
  nombre: string;
  direccion: string;
  correo: string;
  status: StatusContactoEnum;
}