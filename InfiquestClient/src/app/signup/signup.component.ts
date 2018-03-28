import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import {InfiquestService} from '../infiquest.service';
import { Router, Params }            from '@angular/router';
import {User} from '../users/user';
import { AppConfig } from '../app.config';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})
export class SignupComponent implements OnInit {

  user : User;
  error: boolean;
  errorMessage : string;
  userConfirmPassword : string;
  
  constructor(private router : Router, private route: ActivatedRoute,private InfiquestService: InfiquestService,private config: AppConfig) 
  {
    this.user = new User();
   }

  ngOnInit() {
  }

  setUserAttributes(user : User) : void{
    
    this.user.setUserName(user.userName);
    this.user.setUserPassword(user.userPassword);
    this.user.setUserDisplayPic("");
    this.user.setUserEmailId(user.userEmailId);
      
  }

  signup(user : User) : void{
    if(user.userPassword != this.userConfirmPassword)
    {
      this.error = true;
      this.errorMessage = this.config.getConfig("pwd_nomatch_error");
      return;
    }

    this.setUserAttributes(user);
    this.InfiquestService.createUpdateUser(this.user)
        .then(user => {
          this.user = user;
          this.router.navigate(['/home']);
        }
        )
        .catch( errorObject => {
          this.error = true;
          this.errorMessage = errorObject.json().message;
        });
      
  }

}
