import {Tag} from '../tags/tag';

export class Question {
 
  question_id: number;
  question_description: string;
  question_title:string;
  question_timestamp:number;
  question_upvotes:number;
  question_downvotes:number;
  user_id : number;
  tags:Tag[];

  setUserId(userId : number) : void
  {
      this.user_id = userId;
  }

  setQuestionDescription(description : string) : void
  {
      this.question_description = description;
  }

  setQuestionTitle(title : string) : void
  {
      this.question_title = title;
  }

  setQuestionTimestamp(dateInEpoch : number) : void
  {
      this.question_timestamp = dateInEpoch;
  }

  setTags(tags: Tag[])
  {
    this.tags = tags;
  }

  setQuestionUpvotes(upvotes: number) : void
  {
    this.question_upvotes = upvotes;
  }

  setQuestionDownvotes(donwvotes: number) : void{
    this.question_downvotes = donwvotes;
  }


}


 