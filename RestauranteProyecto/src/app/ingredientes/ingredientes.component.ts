import { Component } from '@angular/core';
import { RestauranteService } from '../restaurante.service';

@Component({
  selector: 'app-ingredientes',
  templateUrl: './ingredientes.component.html',
  styleUrls: ['./ingredientes.component.css']
})
export class IngredientesComponent {
  articulos:any;
  data:any;
  informacion=false;
  ingrediente="";
  constructor(private restauranteServicio:RestauranteService){}
  ngOnInit(){
    this.restauranteServicio.obtenerIngredientes()
      .subscribe(result=>{
        this.articulos=result
        this.data = Object.values(this.articulos);
        })
  }
  regresa(){
    this.informacion=false;
  }
  informacionPlatillo(ingrediente: string){
    this.informacion=true;
    this.ingrediente=ingrediente;
    console.log(this.ingrediente);
    
  }

}
