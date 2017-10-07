import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpModule } from '@angular/http';
import { AppRoutingModule } from './app-routing.module';
import { TabsModule } from 'ngx-bootstrap';
import {Location, LocationStrategy,PathLocationStrategy} from '@angular/common';

import { APP_INITIALIZER } from '@angular/core';
import { AppConfig }       from './app.config';

import { AppComponent } from './app.component';
import { QuestionsComponent } from './questions/questions.component';
import { TagsComponent } from './tags/tags.component';
import { InfiquestService} from './infiquest.service';
import { QuestionDetailComponent } from './questionDetails/question-detail.component';
import { LoginComponent } from './login/login.component';
import { InfiquestHomeComponent } from './infiquest-home/infiquest-home.component';
import { SearchComponent } from './search/search.component';
import { AllQuestionsComponent } from './all-questions/all-questions.component';

export function loadConfig(config: AppConfig) : Function
{
  return () => config.load();
}

@NgModule({
  declarations: [
    AppComponent,
    QuestionsComponent,
    TagsComponent,
    QuestionDetailComponent,
    LoginComponent,
    InfiquestHomeComponent,
    SearchComponent,
    AllQuestionsComponent

  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpModule,
    AppRoutingModule,
    TabsModule.forRoot()
  ],
  providers: [
    InfiquestService,
    { provide: LocationStrategy, useClass: PathLocationStrategy},
    AppConfig,
     { provide:APP_INITIALIZER,useFactory: loadConfig, deps:[AppConfig], multi:true}
   ],
  bootstrap: [AppComponent]
})
export class AppModule { }
