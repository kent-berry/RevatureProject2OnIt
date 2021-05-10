import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-page-guide',
  templateUrl: './page-guide.component.html',
  styleUrls: ['./page-guide.component.css']
})
export class PageGuideComponent implements OnInit {

  pageGroupSize : number = 4;
  numListItems : number;


  constructor() { 
    //this.numListItems = items.length;
  }

  ngOnInit(): void {
  }

}
