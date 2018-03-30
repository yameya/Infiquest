import { Component, OnInit } from '@angular/core';
import {Tag} from './tag';
import {InfiquestService} from '../infiquest.service';
import { AppConfig } from '../app.config';

@Component({
  selector: 'app-tags',
  templateUrl: './tags.component.html',
  styleUrls: ['./tags.component.css']
})
export class TagsComponent implements OnInit {
 
 tags : Tag[];
 tagName: string;
 newtag : Tag;
 toggleCreateButton : boolean = false;
 isError: boolean = false;
 errorMessage : string;
 recordsfound : boolean = true;
 
  constructor(private InfiquestService: InfiquestService, private config: AppConfig) {
    this.newtag = new Tag();
   }

  ngOnInit() : void {
     this.InfiquestService.getAllTags()
      .then(tags => {
        this.tags = tags;
        if(tags.length < 1)
        {
          this.recordsfound = false;
          this.errorMessage = this.config.getConfig("no_records_found");
        }  
      })
      .catch( errorObject => {
        this.isError = true;
        this.errorMessage = this.config.getConfig("server_error");
   }); 
  }

  setTagAttributes() : void{
    
    this.newtag.setTagName(this.tagName);
    this.newtag.setTagImage("");
    this.newtag.setTagTimeStamp(Date.now()); 
      
  }

  createTag() : void{
  
    this.setTagAttributes();
    this.InfiquestService.createUpdateTag(this.newtag)
        .then(user => {
          this.toggleButton();
          this.InfiquestService.getAllTags()
          .then(tags => this.tags = tags);  
          
        }
        )
        .catch( errorObject => {
          this.toggleButton();
          this.isError = true;
          this.errorMessage = errorObject.json().message;
        });
      
  }

  toggleButton(): void {
    this.toggleCreateButton = !this.toggleCreateButton;
  }

}
