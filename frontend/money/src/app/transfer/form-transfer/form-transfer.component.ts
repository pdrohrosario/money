import { Component, OnInit, Input } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { first, Observable, tap } from 'rxjs';
import { UserService } from 'src/app/authentication/user/user.service';
import { FormaPagamentoService } from 'src/app/formaPagamento/forma-pagamento.service';
import { SelectDefault, SelectDefaults } from '../../model/selectDefault';
import { TipoGastoService } from '../../tipoGasto/tipo-gasto.service';
import { Transfer } from '../transfers';
import { TransfersService } from '../transfers.service';
import { switchMap } from 'rxjs/operators';
import { User } from 'src/app/authentication/user/user';

@Component({
  selector: 'app-form-transfer',
  templateUrl: './form-transfer.component.html',
  styleUrls: ['./form-transfer.component.css'],
})
export class FormTransferComponent implements OnInit {
  @Input() id: number = 0;

  tipoGastoList!: SelectDefaults;

  private user!:User;

  formaPagamentoList!: SelectDefaults;

  isCreateMode!: boolean;

  transfer!: Transfer;

  transferEditForm!: FormGroup;
  tipoGasto: any;

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private formBuilder: FormBuilder,
    private transferService: TransfersService,
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

    this.id = this.route.snapshot.params['transferID'];
    this.isCreateMode = !this.id;

    this.transferEditForm = this.formBuilder.group({
      quantiaGasta: ['', [Validators.required]],
      descricao: [''],
      data: ['', [Validators.required]],
      formaPagamento: [null, [Validators.required]],
      tipoGasto: [null, [Validators.required]],
    });



    if (!this.isCreateMode) {
      this.transferService
        .findById(this.id)
        .pipe(first())
        .subscribe((x : Transfer) => {
          this.transfer = x;
          this.transferEditForm.patchValue(x);
          const formaPagamento = this.formaPagamentoList.find(c => c.id == this.transfer.formaPagamento.id);
          this.transferEditForm.get('formaPagamento')?.setValue(formaPagamento);
          const tipoGasto = this.tipoGastoList.find(c => c.id == this.transfer.tipoGasto.id);
          this.transferEditForm.get('tipoGasto')?.setValue(tipoGasto);
        });
    }
  }

  getFormaPagamento() {
    this.formaPagamentoService.list()
    .subscribe(
      formaPagamento => {
        const value  : SelectDefaults = formaPagamento as SelectDefaults;
        this.formaPagamentoList = value;
      }
    );
  }

  getTipoGasto() {
 {
      this.tipoGastoService.list()
      .subscribe(
        tipoGasto => {
          const value  : SelectDefaults = tipoGasto as SelectDefaults;
          this.tipoGastoList = value;
        }
      );
    }
  }

  onSubmit() {
    const transfer : Transfer = this.transferEditForm.value as Transfer;
    transfer.userId = this.user.id ? this.user.id : 0;

    if(this.isCreateMode){
      this.transferService.create(transfer).subscribe(
        () => {
         this.router.navigate(['/transfer'])
         alert("A tranferência foi incluída com sucesso.");
        },
        (error) => {
          alert('Erro ao incluir a tranferência, confira o valores dos campos!');
          console.log(error);
        }
      );
    }
    else{
      transfer.id=this.id;
      this.transferService.update(transfer).subscribe(
        () => {
         this.router.navigate(['/transfer'])
         alert("A tranferência foi alterada com sucesso.");
        },
        (error) => {
          alert('Erro ao alterar a tranferência, confira o valores dos campos!');
          console.log(error);
        }
      );
    }
  }
}
