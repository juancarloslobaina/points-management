import dayjs from 'dayjs/esm';

import { ITransaction, NewTransaction } from './transaction.model';

export const sampleWithRequiredData: ITransaction = {
  id: 9960,
};

export const sampleWithPartialData: ITransaction = {
  id: 27090,
  points: 11681,
  date: dayjs('2023-09-27T19:30'),
  status: 'APROBADA',
};

export const sampleWithFullData: ITransaction = {
  id: 27642,
  points: 24773,
  date: dayjs('2023-09-27T16:36'),
  status: 'APROBADA',
};

export const sampleWithNewData: NewTransaction = {
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
