import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { QuestionsComponentComponent } from './questions-component.component';

describe('QuestionsComponentComponent', () => {
  let component: QuestionsComponentComponent;
  let fixture: ComponentFixture<QuestionsComponentComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ QuestionsComponentComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(QuestionsComponentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
