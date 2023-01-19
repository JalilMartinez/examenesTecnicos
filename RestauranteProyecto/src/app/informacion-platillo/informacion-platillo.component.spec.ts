import { ComponentFixture, TestBed } from '@angular/core/testing';

import { InformacionPlatilloComponent } from './informacion-platillo.component';

describe('InformacionPlatilloComponent', () => {
  let component: InformacionPlatilloComponent;
  let fixture: ComponentFixture<InformacionPlatilloComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ InformacionPlatilloComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(InformacionPlatilloComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
