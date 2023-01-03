import { Component, Input, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { first } from 'rxjs';
import { UserService } from 'src/app/authentication/user/user.service';
import { SelectDefaults } from 'src/app/model/selectDefault';
import { TipoGastoService } from 'src/app/tipoGasto/tipo-gasto.service';
import { Goal, Goals } from '../objetivo';
import { GoalService } from '../objetivo.service';

@Component({
  selector: 'app-form-objetivo',
  templateUrl: './form-objetivo.component.html',
  styleUrls: ['./form-objetivo.component.css']
})
export class FormObjetivoComponent implements OnInit {

  @Input() id: number = 0;

  tipoGastoList!: SelectDefaults;


  isCreateMode!: boolean;

  objetivo!: Goal;

  objetivoEditForm!: FormGroup;
  tipoGasto: any;

  private userName? = '';

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private formBuilder: FormBuilder,
    private objetivoService: GoalService,
    private tipoGastoService: TipoGastoService,
    private userService: UserService
  ) {
    this.userService.returnUser().subscribe((response) =>{
      this.userName = response.userName;
    })
  }

  ngOnInit(): void {
    this.id = this.route.snapshot.params['objetivoID'];
    this.isCreateMode = !this.id;

    this.objetivoEditForm = this.formBuilder.group({
      titulo:['',[Validators.required]],
      quantia: ['', [Validators.required]],
      descricao: ['', [Validators.required]],
      dataInicio: ['', [Validators.required]],
      dataFim: ['', [Validators.required]],
      tipoGasto: [null, [Validators.required]],
    });


    this.getTipoGasto();

    if (!this.isCreateMode) {
      this.objetivoService
        .findById(this.id)
        .pipe(first())
        .subscribe((x : Goal) => {
          this.objetivoEditForm.patchValue(x);
          this.objetivo = x;
        });
    }
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
    const goal : Goal = this.objetivoEditForm.value as Goal;
    goal.tipoGasto = this.objetivoEditForm.value.tipoGasto.nome;
    goal.userName = this.userName ? this.userName : '';

    if(this.isCreateMode){
      this.objetivoService.create(goal).subscribe(
        () => {
         this.router.navigate(['/objet'])
         alert("O objetivo foi incluído com sucesso.");
        },
        (error) => {
          alert('Erro ao incluir o objetivo, confira o valores dos campos!');
          console.log(error);
        }
      );
    }
    else{
      goal.id=this.id;
      this.objetivoService.update(goal).subscribe(
        () => {
         this.router.navigate(['/objet'])
         alert("O objetivo foi alterado com sucesso.");
        },
        (error) => {
          alert('Erro ao alterar o objetivo, confira o valores dos campos!');
          console.log(error);
        }
      );
    }
  }

}
