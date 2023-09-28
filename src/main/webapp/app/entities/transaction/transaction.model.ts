import dayjs from 'dayjs/esm';
import { IUser } from 'app/entities/user/user.model';
import { Status } from 'app/entities/enumerations/status.model';

export interface ITransaction {
  id: number;
  points?: number | null;
  date?: dayjs.Dayjs | null;
  status?: keyof typeof Status | null;
  userFrom?: Pick<IUser, 'id' | 'login'> | null;
  userTo?: Pick<IUser, 'id' | 'login'> | null;
}

export type NewTransaction = Omit<ITransaction, 'id'> & { id: null };
