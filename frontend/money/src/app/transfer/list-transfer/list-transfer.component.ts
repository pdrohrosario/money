import { Component, OnInit } from '@angular/core';
import {Observable} from "rxjs";
import {switchMap} from "rxjs/operators";
import {Transfers} from "../transfers";
import {TransfersService} from "../transfers.service";
import {UserService} from "../../authentication/user/user.service";

@Component({
  selector: 'app-list-transfer',
  templateUrl: './list-transfer.component.html',
  styleUrls: ['./list-transfer.component.css']
})
export class ListTransferComponent implements OnInit {

  transfers$!:Observable<Transfers>;

  constructor(private transfersService:TransfersService, private userService:UserService
  ) { }

  ngOnInit(): void {
    this.transfers$ = this.userService.returnUser().pipe(
      switchMap((user) => {
        const userId = user.id ?? 0;
        return this.transfersService.list(userId);
      })
    )
  }

}
