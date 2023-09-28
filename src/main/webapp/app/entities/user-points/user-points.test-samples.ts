import { IUserPoints, NewUserPoints } from './user-points.model';

export const sampleWithRequiredData: IUserPoints = {
  id: 27231,
};

export const sampleWithPartialData: IUserPoints = {
  id: 12676,
  points: 3443,
};

export const sampleWithFullData: IUserPoints = {
  id: 19095,
  points: 31612,
};

export const sampleWithNewData: NewUserPoints = {
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
