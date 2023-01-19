import { Component, Input } from '@angular/core';
import { RestauranteService } from '../restaurante.service';

@Component({
  selector: 'app-ingredientes-platillos',
  templateUrl: './ingredientes-platillos.component.html',
  styleUrls: ['./ingredientes-platillos.component.css']
})
export class IngredientesPlatillosComponent {
  @Input() id:any;
  articulos:any;
  data:any;
  constructor(private restauranteServicio:RestauranteService) { }
  
  ngOnInit() {
    this.restauranteServicio.obtenerPlatilloPorIngrediente(this.id)
      .subscribe(result=>{
        this.articulos=result
        console.log(this.articulos);
        this.data = Object.values(this.articulos);
        console.log(this.data);
        })
  }



 
  
}
