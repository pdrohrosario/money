export interface Goal {
  id: number;
  titulo: string;
  quantia: number;
  descricao:string;
  dataInicio:Date;
  dataFim:Date;
  tipoGasto:string;
  userName:string;
}

export type Goals = Array<Goal>;
