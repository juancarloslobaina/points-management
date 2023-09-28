import { Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { ASC } from 'app/config/navigation.constants';
import { UserPointsComponent } from './list/user-points.component';
import { UserPointsDetailComponent } from './detail/user-points-detail.component';
import { UserPointsUpdateComponent } from './update/user-points-update.component';
import UserPointsResolve from './route/user-points-routing-resolve.service';

const userPointsRoute: Routes = [
  {
    path: '',
    component: UserPointsComponent,
    data: {
      defaultSort: 'id,' + ASC,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: UserPointsDetailComponent,
    resolve: {
      userPoints: UserPointsResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: UserPointsUpdateComponent,
    resolve: {
      userPoints: UserPointsResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: UserPointsUpdateComponent,
    resolve: {
      userPoints: UserPointsResolve,
    },
    canActivate: [UserRouteAccessService],
  },
];

export default userPointsRoute;
