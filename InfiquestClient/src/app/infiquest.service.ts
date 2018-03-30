import { Injectable }    from '@angular/core';
import { Headers, Http } from '@angular/http';
import { Question } from './questions/question';
import { Answer } from './answers/answer';
import { User } from './users/user';
import { Tag } from './tags/tag';
import { AppConfig } from './app.config';
import {Location, LocationStrategy} from '@angular/common';
import { Router }            from '@angular/router';
import { ActivatedRoute } from '@angular/router';
import 'rxjs/add/operator/toPromise';


@Injectable()
export class InfiquestService {

  private headers = new Headers({'Content-Type': 'application/json'});
  private username = "admin";
  private password="infinera1";
  private SLASH : string = "/";
  private COLON : string =":";
  private currentUser : User;
  public infiquestLocalStorage = window.localStorage;

  constructor(private http: Http,private location:Location, private locationStrategy:LocationStrategy,private config: AppConfig, private router : Router, private route: ActivatedRoute) {
  }

  createAuthorizationHeader(headers: Headers) {
    let user : User  = JSON.parse(this.infiquestLocalStorage.getItem("currentUser"));
    headers.append('Authorization', 'Bearer ' + user.userPassword); 
    headers.append('Content-Type','application/json');
  }


  setHeaderWithNoAuth(headers: Headers) {
    headers.append('Content-Type','application/json');
  }

  prepareUrl(api :string):string {
    //let url = this.config.getConfig('protocol') + this.COLON + this.SLASH + this.SLASH + this.config.getConfig('host') + this.COLON + this.config.getConfig('port') + this.SLASH + this.config.getConfig(api);
    let host = window.location.host;
    let url = this.config.getConfig('protocol') + this.COLON + this.SLASH + this.SLASH + host + this.SLASH + this.config.getConfig(api);
    
    return url;
  }

  getAllQuestions(): Promise<Question[]> {

    let url : string = this.prepareUrl("getAllQuestionsUrl");
    let headers = new Headers();
    this.createAuthorizationHeader(headers);
    return this.http.get(url,{headers:headers})
               .toPromise()
               .then(
                 response => response.json() as Question[])
               .catch(error => {
                return this.handleError(error);
              });
  }

  getAllTags(): Promise<Tag[]> {

    let url : string = this.prepareUrl("getAllTagsUrl");
    let headers = new Headers();
    this.createAuthorizationHeader(headers);
    return this.http.get(url,{headers:headers})
               .toPromise()
               .then(
                 response => response.json() as Tag[])
               .catch(error => {
                return this.handleError(error);
              });
  }

   getQuestionById(questionId : number): Promise<Question> {

    let url : string = this.prepareUrl("getQuestionsById");
    url += questionId;
    let headers = new Headers();
    this.createAuthorizationHeader(headers);
    return this.http.get(url,{headers:headers})
               .toPromise()
               .then(
                 response => response.json() as Question)
               .catch(error => {
                return this.handleError(error);
              });
  }

   getAnsByQuestionId(questionId : number): Promise<Answer[]> {

    let url : string = this.prepareUrl("getAnswersByQuestionIdUrl");
    url += questionId;
    let headers = new Headers();
    this.createAuthorizationHeader(headers);
    return this.http.get(url,{headers:headers})
               .toPromise()
               .then(
                 response => response.json() as Answer[])
               .catch(error => {
                return this.handleError(error);
              });
  }

  createNewAnswer(answer: Answer): Promise<Answer> {
      let url : string = this.prepareUrl("createAnswerForQuestionIdUrl");
      let headers = new Headers();
      this.createAuthorizationHeader(headers);
      return this.http
        .post(url, JSON.stringify(answer), {headers: headers})
        .toPromise()
        .then(res => res.json() as Answer)
        .catch(error => {
          return this.handleError(error);
        });
  }

  createUpdateQuestion(question : Question) : Promise<Question>{
      let url : string = this.prepareUrl("createOrUpdateQuestionUrl");
      let headers = new Headers();
      this.createAuthorizationHeader(headers);
      return this.http
        .post(url, JSON.stringify(question), {headers: headers})
        .toPromise()
        .then(res => res.json() as Question)
        .catch(error => {
          return this.handleError(error);
        });  
  }

  createUpdateUser(user : User) : Promise<User>{
    let url : string = this.prepareUrl("createOrUpdateUserUrl");
    let headers = new Headers();
    this.setHeaderWithNoAuth(headers);
    return this.http
      .post(url, JSON.stringify(user), {headers: headers})
      .toPromise()
      .then(res => res.json() as User)
      .catch(error => {
        return this.handleError(error);
      });  
}

createUpdateTag(tag : Tag) : Promise<Tag>{
  let url : string = this.prepareUrl("createOrUpdateTagUrl");
  let headers = new Headers();
  this.createAuthorizationHeader(headers);
  return this.http
    .post(url, JSON.stringify(tag), {headers: headers})
    .toPromise()
    .then(res => res.json() as Tag)
    .catch(error => {
      return this.handleError(error);
    });  
}

  loginForUser(user: User): Promise<User> {
      let url : string = this.prepareUrl("loginUser");
      let headers = new Headers();
      this.setHeaderWithNoAuth(headers);
      return this.http
        .post(url, JSON.stringify(user), {headers: headers})
        .toPromise()
        .then(res => 
          res.json() as User
        )
        .catch(error => {
          return this.handleError(error);
        });
  }

  searchQuestionsForKey(searchText: String): Promise<Question[]> {
    let url : string = this.prepareUrl("searchQuestions");
    url += searchText;
    let headers = new Headers();
    this.createAuthorizationHeader(headers);
    return this.http
      .get(url, {headers: headers})
      .toPromise()
      .then(res => 
        res.json() as Question[]
      )
      .catch(error => {
        return this.handleError(error);
      });
}


setUser(user : User) : void
{
  this.infiquestLocalStorage.setItem("currentUser",JSON.stringify(user));
}

private handleError(error: any): Promise<any> {
  if(error.status == 401)
  {
    this.reDirectToLoginOnAuthFailure(this.config.getConfig("login_error"));
  }  
  console.error('An error occurred', JSON.stringify(error)); // for demo purposes only
  return Promise.reject(error);
}


reDirectToLoginOnAuthFailure(message : String): void {
  this.infiquestLocalStorage.removeItem("currentUser");
  //this.router.navigateByUrl("/login");
  this.router.navigate(["/login"],{ queryParams: { message: message }});
}


/*
  delete(id: number): Promise<void> {
    const url = `${this.heroesUrl}/${id}`;
    return this.http.delete(url, {headers: this.headers})
      .toPromise()
      .then(() => null)
      .catch(this.handleError);
  }

*/

}

