import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TaskStatsPageComponent } from './task-stats-page.component';

describe('TaskStatsPageComponent', () => {
  let component: TaskStatsPageComponent;
  let fixture: ComponentFixture<TaskStatsPageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ TaskStatsPageComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(TaskStatsPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
