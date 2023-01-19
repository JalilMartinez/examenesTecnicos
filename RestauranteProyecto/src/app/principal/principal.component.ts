import { Component } from '@angular/core';
import { RestauranteService } from '../restaurante.service';

@Component({
  selector: 'app-principal',
  templateUrl: './principal.component.html',
  styleUrls: ['./principal.component.css']
})
export class PrincipalComponent {
  articulos:any;
  constructor(private restauranteServicio:RestauranteService){}
  ngOnInit(){
    this.restauranteServicio.obtenerPlatilloRandom()
      .subscribe(result=>{
        this.articulos=result
        console.log(this.articulos);
        
        })
  }
}
