import { Component, OnInit } from '@angular/core';
import {InfiquestService} from '../infiquest.service';
import {Question} from '../questions/question';
import { AppConfig } from '../app.config';

@Component({
  selector: 'app-search',
  templateUrl: './search.component.html',
  styleUrls: ['./search.component.css']
})
export class SearchComponent implements OnInit {

  private hasSearchFinished : boolean = false;
  private searchText : string;
  private searchedQuestions : Question[];
  private context : String;
  private recordsFound : boolean = false;
  private errorMessage : string;


  constructor(private InfiquestService: InfiquestService, private config: AppConfig) {
    this.context = this.config.getConfig("searchContext");
   }

  ngOnInit(){
  }


  searchQuestionsForKey(searchText : String) : void{
    
    this.InfiquestService.searchQuestionsForKey(this.searchText)
        .then(questions => {
          this.searchedQuestions = questions;
          this.hasSearchFinished = true;
          if(this.searchedQuestions.length > 0)
          {
            this.recordsFound = true;
          }
          else
          {
            this.errorMessage = this.config.getConfig("search_noRecordsFoundMessage");
            this.recordsFound = false;
          }
          
        })
        .catch( errorObject => {
            this.hasSearchFinished = true;
            this.recordsFound = false;
            this.errorMessage = this.config.getConfig("server_error");
        });
      
  }
}
