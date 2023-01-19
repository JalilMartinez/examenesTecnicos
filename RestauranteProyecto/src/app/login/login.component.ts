import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {
  us="user";
  pas="root";
  art={
    user:"",
    password:""
  }
  
  constructor(public router: Router){}

  login(){
    
    console.log(this.art.user);
    console.log(this.art.password);
    
    if(this.us==this.art.user){
      if(this.pas==this.art.password){
        this.router.navigate(['/principal']);
      }else{
        alert("Password incorrecta")
      }
    }else{
      alert("Usuario incorrecto")
    }
  }

}
