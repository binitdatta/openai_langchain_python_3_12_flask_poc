import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule } from '@angular/forms';

import { AppComponent } from './app.component'; // Import AppComponent
import { ChatbotComponent } from './chatbot/chatbot.component'; // Import ChatbotComponent

@NgModule({
  imports: [
    BrowserModule,
    FormsModule,
    AppComponent, // Import standalone AppComponent
    ChatbotComponent // Import standalone ChatbotComponent
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
