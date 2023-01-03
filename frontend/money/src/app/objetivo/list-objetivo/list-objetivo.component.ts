import { Component, OnInit } from '@angular/core';
import { Observable, switchMap } from 'rxjs';
import { UserService } from 'src/app/authentication/user/user.service';
import { Goals } from '../objetivo';
import { GoalService } from '../objetivo.service';

@Component({
  selector: 'app-list-goal',
  templateUrl: './list-objetivo.component.html',
  styleUrls: ['./list-objetivo.component.css']
})
export class ListGoalComponent implements OnInit {

  objetivos$!:Observable<Goals>;

  constructor( private userService:UserService, private goalService:GoalService
  ) { }

  ngOnInit(): void {
    this.objetivos$ = this.userService.returnUser().pipe(
      switchMap((user) => {
        const userId = user.id ?? 0;
        return this.goalService.list(userId);
      })
    )
  }

}
