import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import SharedModule from 'app/shared/shared.module';
import { ITEM_DELETED_EVENT } from 'app/config/navigation.constants';
import { IUserPoints } from '../user-points.model';
import { UserPointsService } from '../service/user-points.service';

@Component({
  standalone: true,
  templateUrl: './user-points-delete-dialog.component.html',
  imports: [SharedModule, FormsModule],
})
export class UserPointsDeleteDialogComponent {
  userPoints?: IUserPoints;

  constructor(
    protected userPointsService: UserPointsService,
    protected activeModal: NgbActiveModal,
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.userPointsService.delete(id).subscribe(() => {
      this.activeModal.close(ITEM_DELETED_EVENT);
    });
  }
}
