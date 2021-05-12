import { Component, OnInit } from '@angular/core';

interface Label {
  value: string;
  viewValue: string;
}

@Component({
  selector: 'app-create-task-page',
  templateUrl: './create-task-page.component.html',
  styleUrls: ['./create-task-page.component.css']
})
export class CreateTaskPageComponent implements OnInit {
  labels: Label[] = [
    {value: 'label-0', viewValue: 'Label 0'},
    {value: 'label-1', viewValue: 'Label 1'},
    {value: 'label-2', viewValue: 'Label 2'},
    ];

  checked = false;
  indeterminate = false;
  labelPosition: 'before' | 'after' = 'after';
  disabled = false;

  constructor() { }

  ngOnInit(): void {
  }

}
