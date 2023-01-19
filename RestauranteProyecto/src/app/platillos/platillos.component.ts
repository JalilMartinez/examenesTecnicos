import { Component } from '@angular/core';
import { RestauranteService } from '../restaurante.service';

@Component({
  selector: 'app-platillos',
  templateUrl: './platillos.component.html',
  styleUrls: ['./platillos.component.css']
})
export class PlatillosComponent {

  articulos:any;
  data:any;
  constructor(private restauranteServicio:RestauranteService){}
  ngOnInit(){
    this.restauranteServicio.obtenerPlatillos()
      .subscribe(result=>{
        this.articulos=result
        console.log(this.articulos);
        this.data = Object.values(this.articulos);
        console.log(this.data);
        
        })
  }
}
