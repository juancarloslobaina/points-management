import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of, Subject, from } from 'rxjs';

import { IUser } from 'app/entities/user/user.model';
import { UserService } from 'app/entities/user/user.service';
import { UserPointsService } from '../service/user-points.service';
import { IUserPoints } from '../user-points.model';

import { UserPointsFormService } from './user-points-form.service';

import { UserPointsUpdateComponent } from './user-points-update.component';

describe('UserPoints Management Update Component', () => {
  let comp: UserPointsUpdateComponent;
  let fixture: ComponentFixture<UserPointsUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let userPointsFormService: UserPointsFormService;
  let userPointsService: UserPointsService;
  let userService: UserService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, RouterTestingModule.withRoutes([]), UserPointsUpdateComponent],
      providers: [
        FormBuilder,
        {
          provide: ActivatedRoute,
          useValue: {
            params: from([{}]),
          },
        },
      ],
    })
      .overrideTemplate(UserPointsUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(UserPointsUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    userPointsFormService = TestBed.inject(UserPointsFormService);
    userPointsService = TestBed.inject(UserPointsService);
    userService = TestBed.inject(UserService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should call User query and add missing value', () => {
      const userPoints: IUserPoints = { id: 456 };
      const user: IUser = { id: 22246 };
      userPoints.user = user;

      const userCollection: IUser[] = [{ id: 10790 }];
      jest.spyOn(userService, 'query').mockReturnValue(of(new HttpResponse({ body: userCollection })));
      const additionalUsers = [user];
      const expectedCollection: IUser[] = [...additionalUsers, ...userCollection];
      jest.spyOn(userService, 'addUserToCollectionIfMissing').mockReturnValue(expectedCollection);

      activatedRoute.data = of({ userPoints });
      comp.ngOnInit();

      expect(userService.query).toHaveBeenCalled();
      expect(userService.addUserToCollectionIfMissing).toHaveBeenCalledWith(
        userCollection,
        ...additionalUsers.map(expect.objectContaining),
      );
      expect(comp.usersSharedCollection).toEqual(expectedCollection);
    });

    it('Should update editForm', () => {
      const userPoints: IUserPoints = { id: 456 };
      const user: IUser = { id: 1494 };
      userPoints.user = user;

      activatedRoute.data = of({ userPoints });
      comp.ngOnInit();

      expect(comp.usersSharedCollection).toContain(user);
      expect(comp.userPoints).toEqual(userPoints);
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IUserPoints>>();
      const userPoints = { id: 123 };
      jest.spyOn(userPointsFormService, 'getUserPoints').mockReturnValue(userPoints);
      jest.spyOn(userPointsService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ userPoints });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: userPoints }));
      saveSubject.complete();

      // THEN
      expect(userPointsFormService.getUserPoints).toHaveBeenCalled();
      expect(comp.previousState).toHaveBeenCalled();
      expect(userPointsService.update).toHaveBeenCalledWith(expect.objectContaining(userPoints));
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IUserPoints>>();
      const userPoints = { id: 123 };
      jest.spyOn(userPointsFormService, 'getUserPoints').mockReturnValue({ id: null });
      jest.spyOn(userPointsService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ userPoints: null });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: userPoints }));
      saveSubject.complete();

      // THEN
      expect(userPointsFormService.getUserPoints).toHaveBeenCalled();
      expect(userPointsService.create).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IUserPoints>>();
      const userPoints = { id: 123 };
      jest.spyOn(userPointsService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ userPoints });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(userPointsService.update).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).not.toHaveBeenCalled();
    });
  });

  describe('Compare relationships', () => {
    describe('compareUser', () => {
      it('Should forward to userService', () => {
        const entity = { id: 123 };
        const entity2 = { id: 456 };
        jest.spyOn(userService, 'compareUser');
        comp.compareUser(entity, entity2);
        expect(userService.compareUser).toHaveBeenCalledWith(entity, entity2);
      });
    });
  });
});
