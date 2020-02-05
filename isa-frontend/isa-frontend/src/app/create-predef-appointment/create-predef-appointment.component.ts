import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { AppointmentTypeService } from '../service/appointment-type/appointment-type.service';
import { AppointmentService } from '../service/appointment/appointment.service';
import { CookieService } from 'ngx-cookie-service';
import { UserService } from '../service/user/user.service';
import { RoomService } from '../service/room/room.service';

@Component({
  selector: 'app-create-predef-appointment',
  templateUrl: './create-predef-appointment.component.html',
  styleUrls: ['./create-predef-appointment.component.css']
})
export class CreatePredefAppointmentComponent implements OnInit {

  model : any;
  rooms : any;
  doctors : any;
  appointmentTypes : any;
  cena :     any;
  popust : any;

  currentRoom  : any;
  currentDoctor : any;
  currentType : any;
  time : any;


  constructor(private router: Router,
    private appTypeService : AppointmentTypeService,
    private appointmentService : AppointmentService,
    private activatedRoute: ActivatedRoute,
    private cookieService : CookieService,
    private personService : UserService,
    private roomService : RoomService) { }

  ngOnInit() {
    this.init();
  }

  initialized() {
    if (this.rooms != undefined && this.doctors != undefined && this.appTypeService != undefined) {
      return true;
    }
    else
      return false;
  }
  init() {
    this.roomService.findAll().subscribe(
      (data: any) => {
        this.rooms = data;
      }, (error) => alert(error.text)
    );
    this.personService.findDoctors().subscribe(
      (data: any) => {
        this.doctors = data;
      }, (error) => alert(error.text)
    );
    this.appTypeService.getAll().subscribe(
      (data: any) => {
        this.appointmentTypes = data;
      }, (error) => alert(error.text)
    );
  }
  changeSelectedRoom(filterVal: any) {
    this.currentRoom = filterVal;
  }
  changeSelectedDoctor(filterVal: any) {
    this.currentDoctor = filterVal;
  }
  changeSelectedType(filterVal: any) {
    this.currentType = filterVal;
  }
  napraviPregled() {
    var dat = "" + this.model.year + "-" + this.model.month + "-" + this.model.day + "-" + this.time;
    this.appointmentService.createPredef(this.currentDoctor,this.currentRoom,this.currentType,this.cena,this.popust,dat).subscribe(
      (data : any) => {
        console.log(data)
      }, (error) => {
        alert(error.text);
      }
    )  
    
  }



}
