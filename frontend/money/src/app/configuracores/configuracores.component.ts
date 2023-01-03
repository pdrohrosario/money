import { Component, OnInit, Input } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { first, Observable, tap } from 'rxjs';
import { UserService } from 'src/app/authentication/user/user.service';
import { FormaPagamentoService } from 'src/app/formaPagamento/forma-pagamento.service';
import { SelectDefault, SelectDefaults } from '../model/selectDefault';
import { TipoGastoService } from '../tipoGasto/tipo-gasto.service';
import { switchMap } from 'rxjs/operators';
import { User } from 'src/app/authentication/user/user';

@Component({
  selector: 'app-configuracores',
  templateUrl: './configuracores.component.html',
  styleUrls: ['./configuracores.component.css']
})
export class ConfiguracoresComponent implements OnInit {

  tipoGastoList!: SelectDefaults;

  private user!:User;

  isEditMode:boolean=false;

  formaPagamentoList!: SelectDefaults;

  tipoGastoEditForm!: FormGroup;

  formaPagamentoEditForm!: FormGroup;

  tipoGasto: any;

  constructor(
          private route: ActivatedRoute,
          private router: Router,
          private formBuilder: FormBuilder,
          private formaPagamentoService: FormaPagamentoService,
          private tipoGastoService: TipoGastoService,
          private userService: UserService
          ) {
    this.userService.returnUser().subscribe((response) =>{
      this.user = response;
    })
  }

  ngOnInit(): void {

    this.getFormaPagamento();

    this.getTipoGasto();

    this.tipoGastoEditForm = this.formBuilder.group({
      id:['',[]],
      nome: ['', [Validators.required,Validators.minLength(3)]],
    });

    this.formaPagamentoEditForm = this.formBuilder.group({
      id:['',[]],
      nome: ['', [Validators.required,Validators.minLength(3)]],
    });
  }

  getFormaPagamento() {
    this.formaPagamentoService.list()
      .subscribe(
        formaPagamento => {
          const value: SelectDefaults = formaPagamento as SelectDefaults;
          this.formaPagamentoList = value;
        }
      );
  }

  getTipoGasto() {
    {
      this.tipoGastoService.list()
        .subscribe(
          tipoGasto => {
            const value: SelectDefaults = tipoGasto as SelectDefaults;
            this.tipoGastoList = value;
          }
        );
    }
  }


  onSubmitTipoGasto() {
    const novoTipoGasto: SelectDefault = this.tipoGastoEditForm.value as SelectDefault;
    novoTipoGasto.nome = novoTipoGasto.nome.toUpperCase();
    if(!this.isEditMode){
      this.tipoGastoService.create(novoTipoGasto).subscribe(
              () => {
                this.getTipoGasto();
                alert("O tipo de gasto foi incluído com sucesso.");
                },
              (error) => {
                alert('Erro ao adicionar tipo de gasto, confira o valor do campo e se ele não já foi inserido!');
                console.log(error);
              }
              );
    }
    else{
      this.tipoGastoService.update(novoTipoGasto).subscribe(
              () => {
                this.getTipoGasto();
                alert("O tipo de gasto foi alterado com sucesso.");
                },
              (error) => {
                alert('Erro ao alterar o tipo gasto, confira o valor do campo e se ele não já foi inserido !');
                console.log(error);
              }
              );

      this.isEditMode = false;
    }
    this.cleanTipoGastoForm();
  }

  excluirFormaPagamento(id:number){
    this.formaPagamentoService.delete(id).subscribe((response :number) => {
      this.getFormaPagamento();
      alert('Forma de pagamento removida com sucesso !');
    },
    (error)=>{
      alert('Forma de pagamento não pode ser removida !');
    });
  }

  onSubmitFormaPagamento(){
    const formaPagamento: SelectDefault = this.formaPagamentoEditForm.value as SelectDefault;
    formaPagamento.nome = formaPagamento.nome.toUpperCase();
    if(!this.isEditMode){
      this.formaPagamentoService.create(formaPagamento).subscribe(
              () => {
                this.getFormaPagamento();
                alert("A forma de pagamento foi incluida com sucesso.");
                },
              (error) => {
                alert('Erro ao adicionar forma de pagamento, confira o valor do campo e se ele não já foi inserido!');
                console.log(error);
              }
              );
    }
    else{
      this.formaPagamentoService.update(formaPagamento).subscribe(
              () => {
                this.getFormaPagamento();
                alert("A forma de pagamento foi alterada com sucesso.");
                },
              (error) => {
                alert('Erro ao alterar a forma de pagamento, confira o valor do campo e se ele não já foi inserido!');
                console.log(error);
              }
              );

      this.isEditMode = false;

    }
    this.cleanFormaPagamentoForm();
  }

  excluirTipoGasto(id:number){
    this.tipoGastoService.delete(id).subscribe((response :number) => {
      this.getTipoGasto()
      alert('Tipo de gasto removido com sucesso !');
      },
      (error)=>{
      alert('Tipo de gasto não pode ser removida !');
    });
  }

  atualizarTipoGasto(tipoGasto:SelectDefault){
    this.tipoGastoEditForm.get("id")?.setValue(tipoGasto.id);
    this.tipoGastoEditForm.get("nome")?.setValue(tipoGasto.nome);
    this.isEditMode = true;
  }

  atualizarFormaPagamento(formaPagamento:SelectDefault){
    this.formaPagamentoEditForm.get("id")?.setValue(formaPagamento.id);
    this.formaPagamentoEditForm.get("nome")?.setValue(formaPagamento.nome);
    this.isEditMode = true;
  }


  cleanTipoGastoForm(){
    this.tipoGastoEditForm.reset();
  }

  cleanFormaPagamentoForm(){
    this.formaPagamentoEditForm.reset();
  }
}
