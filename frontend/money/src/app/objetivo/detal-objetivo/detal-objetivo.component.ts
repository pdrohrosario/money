import { Component, Input, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { first } from 'rxjs';
import { Goal } from '../objetivo';
import {GoalService} from '../objetivo.service';

@Component({
  selector: 'app-detal-objetivo',
  templateUrl: './detal-objetivo.component.html',
  styleUrls: ['./detal-objetivo.component.css']
})
export class DetalObjetivoComponent implements OnInit {

  @Input() id: number = 0;

  objetivo!: Goal;

  objetivoDetalForm!: FormGroup;

  constructor(
    private route: ActivatedRoute,
    private formBuilder: FormBuilder,
    private objetivoService:GoalService
  ) {}

  ngOnInit(): void {

    this.id = this.route.snapshot.params['objetivoID'];

    this.objetivoService
      .findById(this.id)
      .pipe(first())
      .subscribe((x: Goal) => {
        this.objetivoDetalForm.patchValue(x);
        this.objetivo = x;
      });

    this.objetivoDetalForm = this.formBuilder.group({
      titulo:['',[]],
      quantia: ['', []],
      descricao: ['', []],
      dataInicio: ['', []],
      dataFim: ['', []],
      tipoGasto: ['', []],
    });


  }
}
