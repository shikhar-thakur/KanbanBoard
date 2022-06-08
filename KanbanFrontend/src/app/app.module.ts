import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './login/login.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import {MatCardModule} from '@angular/material/card';
import {MatFormFieldModule} from '@angular/material/form-field';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import {MatIconModule} from '@angular/material/icon';
import { RegisterComponent } from './register/register.component';
import { MatInputModule} from '@angular/material/input';
import { HomePgComponent } from './home-pg/home-pg.component';
import {MatButtonModule} from '@angular/material/button';
import {MatMenuModule} from '@angular/material/menu';
import {MatSidenavModule} from '@angular/material/sidenav';
import {MatListModule} from '@angular/material/list';
import {  MatToolbarModule } from '@angular/material/toolbar';
import {MatSelectModule} from '@angular/material/select';
import {MatDatepickerModule} from '@angular/material/datepicker';
import {MatGridListModule} from '@angular/material/grid-list';
import {DragDropModule} from '@angular/cdk/drag-drop';
import { HttpClientModule,HttpClient } from '@angular/common/http';
import { UpdateUserComponent } from './update-user/update-user.component';
import { TaskViewComponent } from './task-view/task-view.component';
import {MatProgressBarModule} from '@angular/material/progress-bar';
import { FooterComponent } from './footer/footer.component';
import {TranslateLoader, TranslateModule} from '@ngx-translate/core';
import {TranslateHttpLoader} from '@ngx-translate/http-loader';
// import { PageNotFoundComponent } from './page-not-found/page-not-found.component';
import { CookieService } from 'ngx-cookie-service';
import {MatSnackBarModule} from '@angular/material/snack-bar';
import {MatTooltipModule} from '@angular/material/tooltip';
import {MatSliderModule} from '@angular/material/slider';
import { NotificationComponent } from './notification/notification.component';
import { ArchiveComponent } from './archive/archive.component';
@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    RegisterComponent,
    HomePgComponent,
    UpdateUserComponent,
    TaskViewComponent,
    FooterComponent,
    NotificationComponent,
    ArchiveComponent,
    
 
    // PageNotFoundComponent,
    //AddTaskComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    MatToolbarModule,
    BrowserAnimationsModule,
    MatCardModule,
    FormsModule,
    MatButtonModule,
    MatInputModule,
    ReactiveFormsModule,
    MatIconModule,
    MatMenuModule,
    MatFormFieldModule,
    MatSelectModule,
    MatDatepickerModule,
    MatGridListModule,
    DragDropModule,
    HttpClientModule,
    MatSnackBarModule,
    MatSidenavModule,
    MatProgressBarModule,
    MatTooltipModule,
    MatSliderModule,
    MatListModule,

     // ngx-translate and the loader module
     HttpClientModule,
     TranslateModule.forRoot({
         loader: {
             provide: TranslateLoader,
             useFactory: HttpLoaderFactory,
             deps: [HttpClient]
         }
     })
  ],
  providers: [CookieService],
  bootstrap: [AppComponent]
})
export class AppModule { }

export function HttpLoaderFactory(http: HttpClient): TranslateHttpLoader {
  return new TranslateHttpLoader(http);
}
