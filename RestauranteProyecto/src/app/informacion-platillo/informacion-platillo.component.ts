import { Component, OnInit, Input } from '@angular/core';
import { PrincipalComponent } from '../principal/principal.component';
import { RestauranteService } from '../restaurante.service';

@Component({
  selector: 'app-informacion-platillo',
  template:'Message:<app-principal></app-principal>',
  templateUrl: './informacion-platillo.component.html',
  styleUrls: ['./informacion-platillo.component.css']
})
export class InformacionPlatilloComponent implements OnInit {
  @Input() id:any;
  articulos:any;
  constructor(private restauranteServicio:RestauranteService) { }
  
  ngOnInit() {
    this.restauranteServicio.obtenerPlatilloPorId(this.id)
      .subscribe(result=>{
        this.articulos=result
        console.log(this.articulos);
        
        })
  }
  
 

 
}
