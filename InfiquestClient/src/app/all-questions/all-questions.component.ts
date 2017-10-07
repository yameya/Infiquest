import { Component, OnInit } from '@angular/core';
import { AppConfig } from '../app.config';
import {InfiquestService} from '../infiquest.service';
import {Question} from '../questions/question';

@Component({
  selector: 'app-all-questions',
  templateUrl: './all-questions.component.html',
  styleUrls: ['./all-questions.component.css']
})
export class AllQuestionsComponent implements OnInit {

  context : String; 
  questionsArray : Question[];

  constructor(private InfiquestService : InfiquestService, private config: AppConfig) {
    this.context = this.config.getConfig("allContext");
   }

  ngOnInit() {
    this.InfiquestService.getAllQuestions()
      .then(questions => this.questionsArray = questions);  
  }

}
