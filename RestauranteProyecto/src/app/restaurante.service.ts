import { Injectable } from '@angular/core';
import { HttpClient } from "@angular/common/http";
@Injectable({
  providedIn: 'root'
})
export class RestauranteService {

  constructor(private http:HttpClient) { }

  obtenerPlatilloRandom(){
    return this.http.get('http://www.themealdb.com/api/json/v1/1/random.php');
  }
  obtenerPlatillos(){
    return this.http.get('http://www.themealdb.com/api/json/v1/1/categories.php');
  }
  obtenerIngredientes(){
    return this.http.get('http://www.themealdb.com/api/json/v1/1/list.php?i=list');
  }
  obtenerPlatilloPorId(id: number){
    return this.http.get('http://www.themealdb.com/api/json/v1/1/lookup.php?i='+id);
  }
  obtenerPlatilloPorIngrediente(id: string){
    console.log(id);
    
    return this.http.get('http://www.themealdb.com/api/json/v1/1/filter.php?i='+id);
  }
  
}
