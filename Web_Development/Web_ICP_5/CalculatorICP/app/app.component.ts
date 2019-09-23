import { Component } from '@angular/core';
import {last} from 'rxjs/operators';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'CalculatorICP';
  subText = '';
  mainText = '';
  operand1: number;
  operand2: number;
  operator = '';
  calcString = '';
  result = false;
  operatorset = false;

  pressKey(Button: string) {
    // check if previous and last keys are operator, if they are then invalid
    if (Button === '/' || Button === '*' || Button === '-' || Button === '+' || Button === '%') {
      const lastButton = this.mainText[this.mainText.length - 1];
      if (lastButton === '/' || lastButton === '*' || lastButton === '-' || lastButton === '+' || lastButton === '%') {
        this.operatorset = true;
      }
      if ((this.operatorset) || (this.mainText === '')) {
        return;
      }
      // when test pass, set current button to operator and main text to operand1
      this.operand1 = parseFloat(this.mainText);
      this.operator = Button;
      this.operatorset = true;
    }
    this.mainText += Button;
  }
  // Clear the screen when clear pressed
  clearScreen() {
    this.mainText = '';
    this.subText = '';
    this.operatorset = false;
  }
  // Set operators between 2 operands
  getResult() {
    this.calcString = this.mainText;
    this.operand2 = parseFloat(this.mainText.split(this.operator)[1]);
    if (this.operator === '/') {
      this.subText = this.mainText;
      this.mainText = (this.operand1 / this.operand2).toString();
      this.subText = this.calcString;
    } else if (this.operator === '*') {
      this.subText = this.mainText;
      this.mainText = (this.operand1 * this.operand2).toString();
      this.subText = this.calcString;
    } else if (this.operator === '+') {
      this.subText = this.mainText;
      this.mainText = (this.operand1 + this.operand2).toString();
      this.subText = this.calcString;
    } else if (this.operator === '-') {
      this.subText = this.mainText;
      this.mainText = (this.operand1 - this.operand2).toString();
      this.subText = this.calcString;
    } else if (this.operator === '%') {
      this.subText = this.mainText;
      this.mainText = (this.operand1 % this.operand2).toString();
      this.subText = this.calcString;
    } else {
      this.subText = 'Invalid input';
    }
    this.result = true;
  }
}
