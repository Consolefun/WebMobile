import { Component } from '@angular/core';


@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styles: [
    '.background {background:#38cc0c; color: white}',
    'li .fa-home{color: rgb(29, 126, 207)}',
    'li .fa-hamburger{color: rgb(14, 68, 87)}',
    'li .fa-user-friends{color: rgb(9, 64, 110)}',
    'ul.nav a:hover { color: #74fcff  }',
    '.img-responsive{height: auto; width: 55px; max-height: 50px; max-width: 250px;}',
    '.navbar-brand{padding-left:0;padding-top: 0px;}',
    '.img-responsive.raised{box-shadow: 0 0 4px 0 #349920}',
    '.img-responsive.raised:active, .img-responsive.raised.active{background: #33a6cc; box-shadow: none; margin-bottom: 4px; margin-left:1px;margin-top: 1px}',
  ]
})
export class HeaderComponent {
  constructor() {}

  }
