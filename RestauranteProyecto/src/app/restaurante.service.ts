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
}
