import { IUser } from 'app/entities/user/user.model';

export interface IUserPoints {
  id: number;
  points?: number | null;
  user?: Pick<IUser, 'id' | 'login'> | null;
}

export type NewUserPoints = Omit<IUserPoints, 'id'> & { id: null };
