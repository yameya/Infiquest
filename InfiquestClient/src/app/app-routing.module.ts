import { NgModule }             from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { QuestionsComponent }   from './questions/questions.component';
import { TagsComponent }        from './tags/tags.component';
import { QuestionDetailComponent }   from './questionDetails/question-detail.component';
import { LoginComponent } from './login/login.component';
import { InfiquestHomeComponent } from './infiquest-home/infiquest-home.component';
import { SearchComponent }        from './search/search.component';
import { AllQuestionsComponent }        from './all-questions/all-questions.component';
import { AuthGuard} from './authguard';


const routes: Routes = [
  { path: 'login' , component: LoginComponent},
  { path: '', redirectTo: '/login', pathMatch: 'full' },
  {
    path: 'home',
    component: InfiquestHomeComponent,
    children: [
          {
            path:'',
            component:SearchComponent,
            canActivate: [AuthGuard] 
          },
          {
            path:'search',
            component:SearchComponent,
            canActivate: [AuthGuard] 
          },
          {
            path: 'questions',
            component: AllQuestionsComponent,
            canActivate: [AuthGuard] 
          },
          {
            path: 'tags',
            component: TagsComponent,
            canActivate: [AuthGuard] 
          },
          {
            path: 'questionDetail/:questionId',
            component: QuestionDetailComponent,
            canActivate: [AuthGuard] 
          }
    ]
  }
  
  
];

@NgModule({
  imports: [ RouterModule.forRoot(routes) ],
  exports: [ RouterModule ]
})
export class AppRoutingModule {}
