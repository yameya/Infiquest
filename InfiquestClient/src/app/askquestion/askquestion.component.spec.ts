import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AskquestionComponent } from './askquestion.component';

describe('AskquestionComponent', () => {
  let component: AskquestionComponent;
  let fixture: ComponentFixture<AskquestionComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AskquestionComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AskquestionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
