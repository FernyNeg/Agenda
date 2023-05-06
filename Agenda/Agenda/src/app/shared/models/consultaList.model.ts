import { ClaseBean } from "./ClaseBean.model";

export class ConsultaList<t> extends ClaseBean {
  list: Array<t>;
  param: string;
}