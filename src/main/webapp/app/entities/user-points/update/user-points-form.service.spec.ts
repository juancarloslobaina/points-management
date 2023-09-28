import { TestBed } from '@angular/core/testing';

import { sampleWithRequiredData, sampleWithNewData } from '../user-points.test-samples';

import { UserPointsFormService } from './user-points-form.service';

describe('UserPoints Form Service', () => {
  let service: UserPointsFormService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(UserPointsFormService);
  });

  describe('Service methods', () => {
    describe('createUserPointsFormGroup', () => {
      it('should create a new form with FormControl', () => {
        const formGroup = service.createUserPointsFormGroup();

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            points: expect.any(Object),
            user: expect.any(Object),
          }),
        );
      });

      it('passing IUserPoints should create a new form with FormGroup', () => {
        const formGroup = service.createUserPointsFormGroup(sampleWithRequiredData);

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            points: expect.any(Object),
            user: expect.any(Object),
          }),
        );
      });
    });

    describe('getUserPoints', () => {
      it('should return NewUserPoints for default UserPoints initial value', () => {
        const formGroup = service.createUserPointsFormGroup(sampleWithNewData);

        const userPoints = service.getUserPoints(formGroup) as any;

        expect(userPoints).toMatchObject(sampleWithNewData);
      });

      it('should return NewUserPoints for empty UserPoints initial value', () => {
        const formGroup = service.createUserPointsFormGroup();

        const userPoints = service.getUserPoints(formGroup) as any;

        expect(userPoints).toMatchObject({});
      });

      it('should return IUserPoints', () => {
        const formGroup = service.createUserPointsFormGroup(sampleWithRequiredData);

        const userPoints = service.getUserPoints(formGroup) as any;

        expect(userPoints).toMatchObject(sampleWithRequiredData);
      });
    });

    describe('resetForm', () => {
      it('passing IUserPoints should not enable id FormControl', () => {
        const formGroup = service.createUserPointsFormGroup();
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, sampleWithRequiredData);

        expect(formGroup.controls.id.disabled).toBe(true);
      });

      it('passing NewUserPoints should disable id FormControl', () => {
        const formGroup = service.createUserPointsFormGroup(sampleWithRequiredData);
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, { id: null });

        expect(formGroup.controls.id.disabled).toBe(true);
      });
    });
  });
});
