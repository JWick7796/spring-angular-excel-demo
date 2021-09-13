import { ToastrService, ToastrModule } from 'ngx-toastr';
import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import { AcceptFileComponent } from './accept-file.component';
import { HttpClientTestingModule } from '@angular/common/http/testing';

describe('AcceptFileComponent', () => {
  let component: AcceptFileComponent;
  let fixture: ComponentFixture<AcceptFileComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [AcceptFileComponent],
      imports: [HttpClientTestingModule, ToastrModule.forRoot()],
      providers: [],
    }).compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AcceptFileComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should have isHovering false', () => {
    expect(component.isHovering).toEqual(false);
  });

  it('should render Upload Button', () => {
    fixture.detectChanges();
    const el = fixture.nativeElement.querySelector('button');
    expect(el.innerText).toContain('Upload');
  });
});
