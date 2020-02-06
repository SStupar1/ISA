import { Component, OnInit } from '@angular/core';
import { AppointmentTypeService } from '../service/appointment-type/appointment-type.service';

@Component({
  selector: 'app-manage-appointment-types',
  templateUrl: './manage-appointment-types.component.html',
  styleUrls: ['./manage-appointment-types.component.css']
})
export class ManageAppointmentTypesComponent implements OnInit {

  nazivnovog = "";
  types = [];
  nazivi = [];

  constructor(private appTService: AppointmentTypeService) { }

  ngOnInit() {
    this.getAll();
  }

  getAll() {
    this.appTService.getAll()
      .subscribe(
        (data) => {
          console.log(data);
          this.types = Object.assign([], (data));
          for (let i = 0; i < this.types.length; i++) {
            this.nazivi[i] = this.types[i].name;
          }
        }
      )
  }

  onSubmit() {
    this.appTService.addType(this.nazivnovog)
      .subscribe(
        (user: any) => {
          this.getAll();
        }, (error) => alert(error)
      );
  }

}
