import {Component, ElementRef, OnInit, ViewChild} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {HttpParams} from '@angular/common/http';
import {Observable} from 'rxjs';

@Component({
  selector: 'app-search-recipe',
  templateUrl: './search-recipe.component.html',
  styleUrls: ['./search-recipe.component.css']
})

export class SearchRecipeComponent implements OnInit {
  @ViewChild('recipe') recipes: ElementRef;
  @ViewChild('place') places: ElementRef;
  recipeValue: any;
  placeValue: any;
  // venueList: Object = []; original
  venueList = [];
  recipeList = [];
  currentLat: any;
  currentLong: any;
  geolocationPosition: any;

  constructor(private _http: HttpClient) {
  }
  private productsObservable: Observable<any[]>;

  ngOnInit() {

    window.navigator.geolocation.getCurrentPosition(
      position => {
        this.geolocationPosition = position;
        this.currentLat = position.coords.latitude;
        this.currentLong = position.coords.longitude;
      });
  }

  getVenues() {

    this.recipeValue = this.recipes.nativeElement.value;
    this.placeValue = this.places.nativeElement.value;

    if (this.recipeValue !== null) {
      /**
       * Write code to get recipe
       */
      this._http.get('https://api.edamam.com/search?app_id=ab7db5b3&app_key=2e34052b90a00c43574878368cd759f8&from=0&to=3&calories=591-722&health=alcohol-free&q=' + this.recipeValue)
        .subscribe((data: any) => {
          for (let i = 0; i < data.hits.length; i++) {
            // this.recipeList[i] = data.hits[i].recipe;
            this.recipeList[i] =  {
              'name' : data.hits[i].recipe.label,
              'url'  : data.hits[i].recipe.url,
              'icon' : data.hits[i].recipe.image
            };
          }
        });

    }

    if (this.placeValue != null && this.placeValue !== '' && this.recipeValue != null && this.recipeValue !== '') {
      /**
       * Write code to get place
       */
      this._http.get('https://api.foursquare.com/v2/venues/explore?near=' + this.placeValue + '&client_id=T5HCHZFNYQ4QB35G2LAM3MWD1BZJOLY4I0TNFGINREXPZYMU&client_secret=CXMKIC0KAYWVGLYV5XBSKKMXA3I3BMY3VB2VUK0ZJIXMZFJF&v=20192509&limit=5&query=' + this.recipeValue)
        .subscribe((data: any) => {
          for (let i = 0; i < data.response.groups[0].items.length; i++) {
            this.venueList[i] = data.response.groups[0].items[i].venue;
            const name = this.venueList[i].name;
            const location = this.venueList[i].location.formattedAddress[i];
          }
        });
    }
  }
}
