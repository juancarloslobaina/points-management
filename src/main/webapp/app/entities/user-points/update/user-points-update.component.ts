import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize, map } from 'rxjs/operators';

import SharedModule from 'app/shared/shared.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { IUser } from 'app/entities/user/user.model';
import { UserService } from 'app/entities/user/user.service';
import { IUserPoints } from '../user-points.model';
import { UserPointsService } from '../service/user-points.service';
import { UserPointsFormService, UserPointsFormGroup } from './user-points-form.service';

@Component({
  standalone: true,
  selector: 'jhi-user-points-update',
  templateUrl: './user-points-update.component.html',
  imports: [SharedModule, FormsModule, ReactiveFormsModule],
})
export class UserPointsUpdateComponent implements OnInit {
  isSaving = false;
  userPoints: IUserPoints | null = null;

  usersSharedCollection: IUser[] = [];

  editForm: UserPointsFormGroup = this.userPointsFormService.createUserPointsFormGroup();

  constructor(
    protected userPointsService: UserPointsService,
    protected userPointsFormService: UserPointsFormService,
    protected userService: UserService,
    protected activatedRoute: ActivatedRoute,
  ) {}

  compareUser = (o1: IUser | null, o2: IUser | null): boolean => this.userService.compareUser(o1, o2);

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ userPoints }) => {
      this.userPoints = userPoints;
      if (userPoints) {
        this.updateForm(userPoints);
      }

      this.loadRelationshipsOptions();
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const userPoints = this.userPointsFormService.getUserPoints(this.editForm);
    if (userPoints.id !== null) {
      this.subscribeToSaveResponse(this.userPointsService.update(userPoints));
    } else {
      this.subscribeToSaveResponse(this.userPointsService.create(userPoints));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IUserPoints>>): void {
    result.pipe(finalize(() => this.onSaveFinalize())).subscribe({
      next: () => this.onSaveSuccess(),
      error: () => this.onSaveError(),
    });
  }

  protected onSaveSuccess(): void {
    this.previousState();
  }

  protected onSaveError(): void {
    // Api for inheritance.
  }

  protected onSaveFinalize(): void {
    this.isSaving = false;
  }

  protected updateForm(userPoints: IUserPoints): void {
    this.userPoints = userPoints;
    this.userPointsFormService.resetForm(this.editForm, userPoints);

    this.usersSharedCollection = this.userService.addUserToCollectionIfMissing<IUser>(this.usersSharedCollection, userPoints.user);
  }

  protected loadRelationshipsOptions(): void {
    this.userService
      .query()
      .pipe(map((res: HttpResponse<IUser[]>) => res.body ?? []))
      .pipe(map((users: IUser[]) => this.userService.addUserToCollectionIfMissing<IUser>(users, this.userPoints?.user)))
      .subscribe((users: IUser[]) => (this.usersSharedCollection = users));
  }
}
