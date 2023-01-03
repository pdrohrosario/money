import {SelectDefault} from "../model/selectDefault";

export interface Transfer {
  id: number;
  quantiaGasta:number;
  descricao:string;
  data:Date;
  formaPagamento:SelectDefault;
  tipoGasto:SelectDefault;
  userId:number;
}

export type Transfers = Array<Transfer>;
