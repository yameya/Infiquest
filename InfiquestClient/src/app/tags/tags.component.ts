import { Component, OnInit } from '@angular/core';
import {Tag} from './tag';
import {InfiquestService} from '../infiquest.service';

@Component({
  selector: 'app-tags',
  templateUrl: './tags.component.html',
  styleUrls: ['./tags.component.css']
})
export class TagsComponent implements OnInit {
 
 tags : Tag[];
 
  constructor(private InfiquestService: InfiquestService) { }

  ngOnInit() : void {
     this.InfiquestService.getAllTags()
      .then(tags => this.tags = tags);
  }

}
