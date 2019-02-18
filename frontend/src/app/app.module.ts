import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule } from '@angular/forms';

import { AppComponent } from './app.component';
import { NavbarComponent } from './navbar/navbar.component';
import { AllListComponent } from './all-list/all-list.component';
import { ErrorComponent } from './error/error.component';
import { NewFormComponent } from './new-form/new-form.component';
import { ResultModalComponent } from './new-form/result-modal/result-modal.component';

const appRoutes: Routes = [
  {
    path: 'list',
    component: AllListComponent
  },
  {
    path: 'add',
    component: NewFormComponent
  },
  { path: '',
    redirectTo: '/add',
    pathMatch: 'full'
  },
  { path: 'error',
    component: ErrorComponent
  },
  { path: '**', component: ErrorComponent }
];

@NgModule({
  declarations: [
    AppComponent,
    NavbarComponent,
    AllListComponent,
    ErrorComponent,
    NewFormComponent,
    ResultModalComponent
  ],
  imports: [
    RouterModule.forRoot(appRoutes),
    FormsModule,
    HttpClientModule,
    BrowserModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
