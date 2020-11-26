import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import './vendor';
import { PresenceSharedModule } from 'app/shared/shared.module';
import { PresenceCoreModule } from 'app/core/core.module';
import { PresenceAppRoutingModule } from './app-routing.module';
import { PresenceHomeModule } from './home/home.module';
import { PresenceEntityModule } from './entities/entity.module';
// jhipster-needle-angular-add-module-import JHipster will add new module here
import { MainComponent } from './layouts/main/main.component';
import { NavbarComponent } from './layouts/navbar/navbar.component';
import { FooterComponent } from './layouts/footer/footer.component';
import { PageRibbonComponent } from './layouts/profiles/page-ribbon.component';
import { ErrorComponent } from './layouts/error/error.component';

@NgModule({
  imports: [
    BrowserModule,
    PresenceSharedModule,
    PresenceCoreModule,
    PresenceHomeModule,
    // jhipster-needle-angular-add-module JHipster will add new module here
    PresenceEntityModule,
    PresenceAppRoutingModule,
  ],
  declarations: [MainComponent, NavbarComponent, ErrorComponent, PageRibbonComponent, FooterComponent],
  bootstrap: [MainComponent],
})
export class PresenceAppModule {}
