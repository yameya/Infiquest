import { Component, OnInit } from '@angular/core';
import {Question} from '../questions/question';
import {Tag} from '../tags/tag';
import {InfiquestService} from '../infiquest.service';
import { Router }         from '@angular/router';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-askquestion',
  templateUrl: './askquestion.component.html',
  styleUrls: ['./askquestion.component.css']
})
export class AskquestionComponent implements OnInit {

  newQuestion : Question;
  tags : Tag[];
  selectedTag : Tag;
  postQuestionAttempted : boolean;
  errorOccurred : boolean;
  errorMessage: String = "There was an error in processing request. Please try after some time";
  successMessage : String = "Question was posted successfully."
  
  constructor(private InfiquestService: InfiquestService,private router : Router, private route: ActivatedRoute) {
    this.newQuestion = new Question();
  }

  ngOnInit() : void {

    this.postQuestionAttempted = false;
    this.errorOccurred = false;

    this.InfiquestService.getAllTags()
     .then(tags => { this.tags = tags });
     
  }

 setQuestionAttributes() : void{
  
    this.newQuestion.setQuestionTimestamp(Date.now());
    this.newQuestion.setQuestionUpvotes(0);
    this.newQuestion.setQuestionDownvotes(0);
    this.newQuestion.setUserId(1);
    
    let questTags: Tag[] = new Array<Tag>();
    console.log("selected tag:" + JSON.stringify(this.selectedTag));
    questTags.push(this.selectedTag);
    this.newQuestion.setTags(questTags);
  }

  submitQuestion() : void{
    this.setQuestionAttributes();
    this.postQuestionAttempted = true;
    this.InfiquestService.createUpdateQuestion(this.newQuestion)
        .then(question => {})
        .catch(errorObject => {
            this.errorOccurred = true;
        }); 
  }

  onSelect() : void
  {
    this.goToHomePage();  
  }

  goToHomePage() : void
  {
    this.router.navigateByUrl('home/questions');
  }

}
