import { Component, OnInit } from '@angular/core';
import { VacationService } from '../service/vacation/vacation.service';

@Component({
  selector: 'app-vacation-requests',
  templateUrl: './vacation-requests.component.html',
  styleUrls: ['./vacation-requests.component.css']
})
export class VacationRequestsComponent implements OnInit {

  pendingRequests = [];
  loaded = false;
  idzabrisanje: any;
  brisanje = false;
  razlog: any;

  constructor(private vacationService: VacationService) { }

  ngOnInit() {
    this.getRequests();
  }

  getRequests() {
    this.vacationService.getPending()
      .subscribe(
        (data) => {
          console.log(data);
          this.pendingRequests = Object.assign([], (data));
          this.loaded = true;
        }
      )
  }

  prihvati(id) {
    this.vacationService.acceptRequest(id)
      .subscribe(
        () => {
          this.getRequests();
        }
      )
  }

  odbij(id) {
    this.idzabrisanje = id;
    this.vacationService.denyRequest(this.idzabrisanje, this.razlog)
      .subscribe(
        () => {
          this.getRequests();
          this.brisanje = false;
        }
      )
    
  }
  odustani() {
    this.brisanje = false;
  }

}
