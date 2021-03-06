import { Component, OnInit } from '@angular/core';
import {User} from '../users/user';
import { ActivatedRoute } from '@angular/router';
import {InfiquestService} from '../infiquest.service';
import { Router, Params }            from '@angular/router';
import { Location, LocationStrategy, PathLocationStrategy } from '@angular/common';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  user : User;
  error: boolean;
  errorMessage : string;

  constructor(private router : Router, private route: ActivatedRoute,private InfiquestService: InfiquestService,private location: Location) {
    this.user = new User();
  }


  ngOnInit() {
   
    // subscribe to router event
    this.route.queryParams.subscribe((params: Params) => {
      
      let message = params['message'];
    
      if(typeof message !== 'undefined')
      {
        this.errorMessage = message;
        this.error = true;
      }
      else
      {
        this.error = false;
      }
      
    });
  }
  
  setUserAttributes(user : User) : void{

    this.user.setUserName(user.userName);
    this.user.setUserPassword(user.userPassword);
    this.user.setUserDisplayPic("");
    this.user.setUserEmailId("");
    this.user.setUserId(0);
  }

  login(user : User) : void{
    this.setUserAttributes(user);
    this.InfiquestService.loginForUser(this.user)
        .then(user => {
          this.user = user;
          this.InfiquestService.setUser(user);
          this.router.navigate(['/home']);
        }
        )
        .catch( errorObject => {
          this.error = true;
          this.errorMessage = errorObject.json().message;
        });
      
  }

  navigateToSignUpPage() : void{
      this.router.navigate(['/signup']);
  }
}
