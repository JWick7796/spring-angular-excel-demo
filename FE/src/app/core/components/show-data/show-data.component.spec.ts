import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ShowDataComponent } from './show-data.component';
import { HttpClientTestingModule } from '@angular/common/http/testing';

describe('ShowDataComponent', () => {
  let component: ShowDataComponent;
  let fixture: ComponentFixture<ShowDataComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ShowDataComponent],
      imports: [HttpClientTestingModule],
    }).compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ShowDataComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should render Export Button', () => {
    fixture.detectChanges();
    const el = fixture.nativeElement.querySelector('button');
    expect(el.innerText).toContain('Export To Excel');
  });

  it('should render Department Column', () => {
    fixture.detectChanges();
    const element = fixture.nativeElement.querySelector('#dept');
    expect(element.innerText).toContain('Department');
  });

  it('should render Employees Column', () => {
    fixture.detectChanges();
    const element = fixture.nativeElement.querySelector('#emp');
    expect(element.innerText).toContain('Employees');
  });
});
