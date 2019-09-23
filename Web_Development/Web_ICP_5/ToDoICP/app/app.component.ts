import { Component } from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  item: string;
  // define list of items
  items_list = [];
  complete_list = [];


  // Write code to push new item
  submitNewItem() {
    this.items_list.push(this.item);
    this.item = '';
  }

  // Write code to complete item
  completeItem(index) {
    // push item that recently added into complete list and take it out of the item list
    this.complete_list.push(this.items_list[index]);
    this.items_list.splice(index, 1);
  }

  // Write code to delete item
  deleteItem(index) {
    // Delete one item at a time
    this.items_list.splice(index, 1);
  }


}
