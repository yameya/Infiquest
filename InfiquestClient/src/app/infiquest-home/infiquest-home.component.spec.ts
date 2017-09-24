import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { InfiquestHomeComponent } from './infiquest-home.component';

describe('InfiquestHomeComponent', () => {
  let component: InfiquestHomeComponent;
  let fixture: ComponentFixture<InfiquestHomeComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ InfiquestHomeComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(InfiquestHomeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
