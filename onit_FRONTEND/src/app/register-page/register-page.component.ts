import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { Router, ActivatedRoute, NavigationEnd } from '@angular/router';
import { filter } from 'rxjs/operators';

@Component({
  selector: 'app-register-page',
  templateUrl: './register-page.component.html',
  styleUrls: ['./register-page.component.css']
})
export class RegisterPageComponent implements OnInit {

  form: FormGroup;

  constructor(
    private formbuilder: FormBuilder,
    private router: Router
  ) {
  }

    submitForm(): void {

      const fromValue = this.form.value;
      console.log(fromValue);
  
     }

  ngOnInit(): void {
    this.form= this.formbuilder.group({
      firstName:[""],
      lastName:[""],
      email:[""],
      password:[""],
      password2:[""]

    })
    
  }

}
