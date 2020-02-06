import { Component, OnInit } from '@angular/core';
import { RoomService } from '../service/room/room.service';
import { NgForm } from '@angular/forms';
import { AppoinmentRequestService} from './../service/appointment-request/appoinment-request.service'
import { AppointmentService } from '../service/appointment/appointment.service';

@Component({
  selector: 'app-manage-appointment-request',
  templateUrl: './manage-appointment-request.component.html',
  styleUrls: ['./manage-appointment-request.component.css']
})
export class ManageAppointmentRequestComponent implements OnInit {

  rooms : any;
  appointmentRequests =  [];
  model: any;
  ime = "";
  broj: any;
  appointment_request_id : any;
  disp_doctor_id : any;
  disp_patient_id : any;
  disp_date : any;
  disp_type : any;
  hidden : any;

  constructor(private roomService: RoomService,
              private appointmentRequestService : AppoinmentRequestService,
              private appointmentService : AppointmentService) { }

  ngOnInit() {
    this.getAppointmentRequests();
    this.hidden = true;
  }

  onSubmit(form: NgForm) {
    var dat = "" + this.model.year + "-" + this.model.month + "-" + this.model.day;
    console.log(dat);
    this.findRoom(this.ime, this.broj, dat);
  }

  findRoom(ime: any, broj:any, dat:any) {
    this.roomService.searchRooms(ime, broj, dat)
      .subscribe(
        (data) => {
          console.log(data);
          this.rooms = Object.assign([], (data));
          console.log(this.rooms);
        }
      )
  }

  getAppointmentRequests() {
    this.appointmentRequestService.getAppointmentRequests().subscribe(
      (data) => {
        this.appointmentRequests = Object.assign([], (data));
        console.log(this.appointmentRequests);
      }
    )
  }
  findAppointment(id : any,doctor : any,patient : any,date: any,type : any){
    console.log(id);
    this.appointment_request_id = id;
    this.disp_doctor_id = doctor;
    this.disp_patient_id = patient;
    this.disp_date = date;
    this.disp_type = type;
    this.hidden = false;
    this.model = date;


  }

  zakazi(date : any,room_id) {
    this.appointmentService.makeNewAppointment(
      this.appointment_request_id,
      this.disp_doctor_id,
      this.disp_patient_id,
      room_id,
      date,
      this.disp_type).subscribe(        
      (data) => {
        this.getAppointmentRequests();
        this.hidden  = true;
      }
    )
    
    
  }


}
