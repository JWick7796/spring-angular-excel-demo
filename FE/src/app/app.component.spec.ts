import { MatGridListModule, MatGridList } from '@angular/material/grid-list';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { ToastrModule } from 'ngx-toastr';
import { ShowDataComponent } from './core/components/show-data/show-data.component';
import { AcceptFileComponent } from './core/components/accept-file/accept-file.component';
import { MaterialModule } from './shared/modules/material-module/material.module';
import { TestBed, async, ComponentFixture } from '@angular/core/testing';
import { RouterTestingModule } from '@angular/router/testing';
import { AppComponent } from './app.component';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { MatTabsModule, MatTab } from '@angular/material/tabs';

describe('AppComponent', () => {
  let component: AppComponent;
  let fixture: ComponentFixture<AppComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      imports: [
        RouterTestingModule,
        HttpClientTestingModule,
        MaterialModule,
        ToastrModule.forRoot(),
        BrowserAnimationsModule,
      ],
      declarations: [
        AppComponent,
        AcceptFileComponent,
        ShowDataComponent,
        MatGridList,
        MatTab,
      ],
    }).compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AppComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create the app', () => {
    const fixture = TestBed.createComponent(AppComponent);
    const app = fixture.componentInstance;
    expect(app).toBeTruthy();
  });

  it('should render Upload Data tab', async () => {
    fixture.detectChanges();
    const el = fixture.nativeElement.querySelector('.mat-tab-label-active');
    expect(el.innerText).toContain('Upload Data');
  });
});
