export class User {
 
  userId: number;
  userName: string;
  userEmailId:string;
  userPassword: string;
  userDisplayPic:string;

  setUserId(userId : number) : void
  {
      this.userId = userId;
  }

  setUserName(userName : string) : void
  {
      this.userName = userName;
  }

  setUserEmailId(userEmailId : string) : void
  {
    this.userEmailId = userEmailId;
  }

  setUserPassword(userPassword : string) : void
  {
    this.userPassword = userPassword;
  }

  setUserDisplayPic(userDisplayPic : string) : void
  {
    this.userDisplayPic = userDisplayPic;
  }


}


 