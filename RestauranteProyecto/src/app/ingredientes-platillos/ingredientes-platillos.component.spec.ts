import { ComponentFixture, TestBed } from '@angular/core/testing';

import { IngredientesPlatillosComponent } from './ingredientes-platillos.component';

describe('IngredientesPlatillosComponent', () => {
  let component: IngredientesPlatillosComponent;
  let fixture: ComponentFixture<IngredientesPlatillosComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ IngredientesPlatillosComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(IngredientesPlatillosComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
