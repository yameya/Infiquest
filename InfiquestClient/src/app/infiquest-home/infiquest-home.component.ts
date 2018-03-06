import { Component, OnInit } from '@angular/core';
import {InfiquestService} from '../infiquest.service';
import { AppConfig } from '../app.config';

@Component({
  selector: 'app-infiquest-home',
  templateUrl: './infiquest-home.component.html',
  styleUrls: ['./infiquest-home.component.css']
})
export class InfiquestHomeComponent implements OnInit {

  constructor(private InfiquestService: InfiquestService, private config: AppConfig) { }

  ngOnInit() {
  }

  logout() : void{

    this.InfiquestService.reDirectToLoginOnAuthFailure(this.config.getConfig("login_error"));
  }
}
