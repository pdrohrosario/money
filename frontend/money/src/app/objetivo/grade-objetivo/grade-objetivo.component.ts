import { Component, Input, OnInit } from '@angular/core';
import { Goals } from '../objetivo';
import { GoalService } from '../objetivo.service';

@Component({
  selector: 'app-grade-goal',
  templateUrl: './grade-objetivo.component.html',
  styleUrls: ['./grade-objetivo.component.css']
})
export class GradeGoalComponent implements OnInit {


  @Input() objetivos!:Goals;

  constructor(private goalService: GoalService) { }

  ngOnInit(): void {
  }

  delete(id: number) {
    let newObjetivosList = this.objetivos.filter(obj => obj.id != id);
    this.objetivos = newObjetivosList;
    this.goalService.delete(id).subscribe((response :number) => {
      alert('Objetivo removido com sucesso !');
    });;
  }

}
