import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';
import { UserService } from '../service/user/user.service';
import { NgForm } from '@angular/forms';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  firstName:string;
  lastName : string;
  email:string;
  password : string; 
  password2 : string; 
  address :string;
  jmbg : string;

  constructor(private http: HttpClient,
    private router: Router,
    private userService: UserService) { }

    ngOnInit() {
    }
    
    register(form: NgForm) {
      console.log(this.firstName)
      this.userService.registerUser(this.firstName,this.lastName,this.address,this.jmbg, this.email, this.password)
      .subscribe(
        (user: any) => {
          this.router.navigate(['/login']);
        }, (error) => alert(error.text)
      );
    }

}
