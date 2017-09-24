import { Component, OnInit } from '@angular/core';
import {User} from '../users/user';
import { ActivatedRoute } from '@angular/router';
import {InfiquestService} from '../infiquest.service';
import { Router }            from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  user : User;

  constructor(private router : Router, private route: ActivatedRoute,private InfiquestService: InfiquestService) {
    this.user = new User();
  }


  ngOnInit() {
  }

  setUserAttributes() : void{
  
    this.user.setUserName("admin");
    this.user.setUserPassword("infinera1")
    this.user.setUserDisplayPic("");
    this.user.setUserEmailId("");
    this.user.setUserId(0);
  }

  login() : void{
    this.setUserAttributes();
    this.InfiquestService.loginForUser(this.user)
        .then(user => {
          this.user = user;
          this.InfiquestService.setUser(user);
          this.router.navigate(['/home']);
        }
        );   
  }
}
