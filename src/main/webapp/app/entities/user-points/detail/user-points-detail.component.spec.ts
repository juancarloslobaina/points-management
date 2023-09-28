import { TestBed } from '@angular/core/testing';
import { provideRouter, withComponentInputBinding } from '@angular/router';
import { RouterTestingHarness, RouterTestingModule } from '@angular/router/testing';
import { of } from 'rxjs';

import { UserPointsDetailComponent } from './user-points-detail.component';

describe('UserPoints Management Detail Component', () => {
  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [UserPointsDetailComponent, RouterTestingModule.withRoutes([], { bindToComponentInputs: true })],
      providers: [
        provideRouter(
          [
            {
              path: '**',
              component: UserPointsDetailComponent,
              resolve: { userPoints: () => of({ id: 123 }) },
            },
          ],
          withComponentInputBinding(),
        ),
      ],
    })
      .overrideTemplate(UserPointsDetailComponent, '')
      .compileComponents();
  });

  describe('OnInit', () => {
    it('Should load userPoints on init', async () => {
      const harness = await RouterTestingHarness.create();
      const instance = await harness.navigateByUrl('/', UserPointsDetailComponent);

      // THEN
      expect(instance.userPoints).toEqual(expect.objectContaining({ id: 123 }));
    });
  });
});
