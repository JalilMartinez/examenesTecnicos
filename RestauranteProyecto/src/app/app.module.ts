import { HttpClientModule } from '@angular/common/http';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';
import { RouterModule, Routes } from '@angular/router';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './login/login.component';
import { PrincipalComponent } from './principal/principal.component';
import { IngredientesComponent } from './ingredientes/ingredientes.component';
import { PlatillosComponent } from './platillos/platillos.component';
import { PerfilComponent } from './perfil/perfil.component';
import { BarraComponent } from './barra/barra.component';
import { InformacionPlatilloComponent } from './informacion-platillo/informacion-platillo.component';
import { IngredientesPlatillosComponent } from './ingredientes-platillos/ingredientes-platillos.component';

const appRoutes:Routes=[
  {path:'', component:LoginComponent},
  {path:'principal', component:PrincipalComponent},
  {path:'principal', component:PrincipalComponent},
  {path:'ingredientes', component:IngredientesComponent},
  {path:'platillos',component:PlatillosComponent},
  {path:'perfil', component:PerfilComponent},
  {path:'informacionPlatillo',component:InformacionPlatilloComponent},
  {path:'ingredientesPlatillos', component:IngredientesPlatillosComponent}
]

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    PrincipalComponent,
    IngredientesComponent,
    PlatillosComponent,
    PerfilComponent,
    BarraComponent,
    InformacionPlatilloComponent,
    IngredientesPlatillosComponent
  ],
  imports: [
    FormsModule,
    BrowserModule,
    AppRoutingModule,
    RouterModule.forRoot(appRoutes),
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
