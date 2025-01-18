import { Component } from '@angular/core';
import { ChatbotComponent } from './chatbot/chatbot.component';
import { CommonModule } from '@angular/common'; // Import CommonModule

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
  standalone: true,
  imports: [CommonModule, ChatbotComponent] // Import ChatbotComponent here
})
export class AppComponent {
  isChatbotVisible = false;

  toggleChatbot() {
    this.isChatbotVisible = !this.isChatbotVisible;
  }

  closeChatbot() {
    this.isChatbotVisible = false;
  }
}
