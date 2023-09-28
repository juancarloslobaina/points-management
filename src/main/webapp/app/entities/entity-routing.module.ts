import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

@NgModule({
  imports: [
    RouterModule.forChild([
      {
        path: 'transaction',
        data: { pageTitle: 'pointsManagementApp.transaction.home.title' },
        loadChildren: () => import('./transaction/transaction.routes'),
      },
      {
        path: 'user-points',
        data: { pageTitle: 'pointsManagementApp.userPoints.home.title' },
        loadChildren: () => import('./user-points/user-points.routes'),
      },
      /* jhipster-needle-add-entity-route - JHipster will add entity modules routes here */
    ]),
  ],
})
export class EntityRoutingModule {}
