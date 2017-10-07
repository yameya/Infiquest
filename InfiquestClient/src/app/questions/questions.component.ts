import { Component, OnInit , Input} from '@angular/core';
import {Question} from './question';
import {InfiquestService} from '../infiquest.service';
import { Router }            from '@angular/router';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-questions',
  templateUrl: './questions.component.html',
  styleUrls: ['./questions.component.css']
})
export class QuestionsComponent implements OnInit {

  questions : Question[];
  selectedQuestion: Question;
  
  constructor(private InfiquestService: InfiquestService, private router : Router, private route: ActivatedRoute) { }

  ngOnInit() : void {
   
  }

  @Input()
  set questionsArray(questionsArray:Question[]){
    this.questions = questionsArray;
  }

  get questionsArray():Question[] {
    return this.questions;
  }
  onSelect(question :Question) : void
  {
    this.selectedQuestion = question;  
    this.goToDetailsPage();  
  }

  goToDetailsPage() : void
  {
    this.router.navigateByUrl('home/questionDetail/'+ this.selectedQuestion.question_id);
  }

}