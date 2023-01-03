import { Component, Input, OnInit } from '@angular/core';
import { first, Observable } from 'rxjs';
import { Transfer } from '../transfers';
import { ActivatedRoute } from '@angular/router';
import { TransfersService } from '../transfers.service';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';

@Component({
  selector: 'app-detal-transfer',
  templateUrl: './detal-transfer.component.html',
  styleUrls: ['./detal-transfer.component.css'],
})
export class DetalTransferComponent implements OnInit {
  @Input() id: number = 0;

  transfer!: Transfer;

  transferDetalForm!: FormGroup;

  constructor(
    private route: ActivatedRoute,
    private formBuilder: FormBuilder,
    private transferService: TransfersService
  ) {}

  ngOnInit(): void {

    this.id = this.route.snapshot.params['transferID'];

    this.transferDetalForm = this.formBuilder.group({
      quantiaGasta: ['', []],
      descricao: ['', []],
      data: ['', []],
      formaPagamento: ['', []],
      tipoGasto: ['', []],
    });


    this.transferService
      .findById(this.id)
      .pipe(first())
      .subscribe((x: Transfer) => {
        this.transfer = x;
        this.transferDetalForm.patchValue(x);
        this.transferDetalForm.get('formaPagamento')?.setValue(x.formaPagamento.nome);
        this.transferDetalForm.get('tipoGasto')?.setValue(x.tipoGasto.nome);

      });

  }
}
