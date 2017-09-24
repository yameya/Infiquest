import { Component, OnInit } from '@angular/core';
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
     this.InfiquestService.getAllQuestions()
      .then(questions => this.questions = questions);
      
  }
  
  onSelect(question :Question) : void
  {
    this.selectedQuestion = question;  
    this.goToDetailsPage();  
  }

  goToDetailsPage() : void
  {
    this.router.navigate(['../questionDetail/'+ this.selectedQuestion.question_id],{ relativeTo: this.route });
  }

}