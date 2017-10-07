import { Injectable }    from '@angular/core';
import { Headers, Http } from '@angular/http';
import { Question } from './questions/question';
import { Answer } from './answers/answer';
import { User } from './users/user';
import { Tag } from './tags/tag';
import { AppConfig } from './app.config';
import {Location, LocationStrategy} from '@angular/common';

import 'rxjs/add/operator/toPromise';


@Injectable()
export class InfiquestService {

  private headers = new Headers({'Content-Type': 'application/json'});
  private username = "admin";
  private password="infinera1";
  private SLASH : string = "/";
  private COLON : string =":";
  private currentUser : User;
  private infiquestLocalStorage = window.localStorage;

  constructor(private http: Http,private location:Location, private locationStrategy:LocationStrategy,private config: AppConfig) {
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
    let url = this.config.getConfig('protocol') + this.COLON + this.SLASH + this.SLASH + this.config.getConfig('host') + this.COLON + this.config.getConfig('port') + this.SLASH + this.config.getConfig(api);
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
               .catch(this.handleError);
  }

  getAllTags(): Promise<Tag[]> {

    let url : string = this.prepareUrl("getAllTagsUrl");
    let headers = new Headers();
    this.createAuthorizationHeader(headers);
    return this.http.get(url,{headers:headers})
               .toPromise()
               .then(
                 response => response.json() as Tag[])
               .catch(this.handleError);
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
               .catch(this.handleError);
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
               .catch(this.handleError);
  }

  createNewAnswer(answer: Answer): Promise<Answer> {
      let url : string = this.prepareUrl("createAnswerForQuestionIdUrl");
      let headers = new Headers();
      this.createAuthorizationHeader(headers);
      return this.http
        .post(url, JSON.stringify(answer), {headers: headers})
        .toPromise()
        .then(res => res.json() as Answer)
        .catch(this.handleError);
  }

  createUpdateQuestion(question : Question) : Promise<Question>{
      let url : string = this.prepareUrl("createOrUpdateQuestionUrl");
      let headers = new Headers();
      this.createAuthorizationHeader(headers);
      return this.http
        .post(url, JSON.stringify(question), {headers: headers})
        .toPromise()
        .then(res => res.json() as Question)
        .catch(this.handleError);  
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
        .catch(this.handleError);
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
      .catch(this.handleError);
}


setUser(user : User) : void
{
    this.currentUser = user;
    this.infiquestLocalStorage.setItem("currentUser",JSON.stringify(user));
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

  private handleError(error: any): Promise<any> {
    console.error('An error occurred', error); // for demo purposes only
    return Promise.reject(error);
  }

}

