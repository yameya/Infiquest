export class Answer {
 
  answerId: number;
  questionId: number;
  userId: number;
  answerContent: string;
  answerCreationTime:number;
  answerUpvotes:number;
  answerDownvotes:number;

  setUserId(userId : number) : void
  {
      this.userId = userId;
  }

  setQuestionId(questionId : number) : void
  {
      this.questionId = questionId;
  }

  setAnswerTimestamp(dateInEpoch : number) : void
  {
      this.answerCreationTime = dateInEpoch;
  }

  setAnswerUpvotes(upvotes: number) : void
  {
    this.answerUpvotes = upvotes;
  }

  setAnswerDownvotes(donwvotes: number) : void{
    this.answerDownvotes = donwvotes;
  }
}


 