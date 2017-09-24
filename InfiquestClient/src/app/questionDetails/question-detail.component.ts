import { Component, OnInit } from '@angular/core';
import {Question} from '../questions/question';
import {Answer} from '../answers/answer';
import { ActivatedRoute } from '@angular/router';
import {InfiquestService} from '../infiquest.service';

@Component({
  selector: 'app-question-detail',
  templateUrl: './question-detail.component.html',
  styleUrls: ['./question-detail.component.css']
})
export class QuestionDetailComponent implements OnInit {

  private subscriber: any;
  questionId:number;
  question : Question;
  answers: Answer[];
  newAnswer: Answer;

  constructor(private route: ActivatedRoute,private InfiquestService: InfiquestService) {
    this.newAnswer = new Answer();
  }

  ngOnInit() {
  
    this.subscriber = this.route.paramMap.subscribe(paramMap => {
       this.questionId = +paramMap.get("questionId"); 
       this.InfiquestService.getQuestionById(this.questionId)
                        .then(questionDetail => this.question = questionDetail);
       this.InfiquestService.getAnsByQuestionId(this.questionId)
                        .then(answers => this.answers = answers);                 
      }
       
    );
 
  }

  ngOnDestroy() {
    this.subscriber.unsubscribe();
  }

  setAnswerAttributes() : void{
  
    this.newAnswer.setQuestionId(+this.questionId);
    this.newAnswer.setAnswerTimestamp(Date.now());
    this.newAnswer.setAnswerUpvotes(0);
    this.newAnswer.setAnswerDownvotes(0);
    this.newAnswer.setUserId(1);
  }

  submitAnswer() : void{
    this.setAnswerAttributes();
    this.InfiquestService.createNewAnswer(this.newAnswer)
        .then(answer => {this.answers.push(answer); this.newAnswer = new Answer();});   
  }

  upVoteQuestion() : void{
    this.question.question_upvotes += 1;
    this.InfiquestService.createUpdateQuestion(this.question)
        .then(question => this.question = question);
  }
  
  downVoteQuestion() : void{
    this.question.question_downvotes += 1;
    this.InfiquestService.createUpdateQuestion(this.question)
        .then(question => this.question = question);
  }

  

}
