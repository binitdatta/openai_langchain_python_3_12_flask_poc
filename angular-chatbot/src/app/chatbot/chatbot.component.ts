import { Component, EventEmitter, Output } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpClient, HttpClientModule } from '@angular/common/http';
import { CommonModule } from '@angular/common'; // Import CommonModule for *ngFor

@Component({
  selector: 'app-chatbot',
  standalone: true,
  imports: [FormsModule, HttpClientModule, CommonModule], // Include CommonModule
  templateUrl: './chatbot.component.html',
  styleUrls: ['./chatbot.component.css']
})
export class ChatbotComponent {
  @Output() close = new EventEmitter<void>(); // Emit an event when the chatbot is closed
  userInput = ''; // Stores the user's input
  messages: { text: string; isUser: boolean }[] = []; // Stores chat messages
  apiUrl = 'http://localhost:8992/jira'; // Backend API URL
  isWaitingForResponse = false; // Indicates if the chatbot is waiting for a response

  constructor(private http: HttpClient) { }

  // Method to close the chatbot
  closeChatbot() {
    this.close.emit();
  }

  // Method to send the user input to the backend API
  sendMessage() {
    if (!this.userInput.trim()) {
      return; // Prevent empty messages
    }

    // Add user message to the chat
    const userMessage = this.userInput;
    console.log(userMessage)
    this.messages.push({ text: userMessage, isUser: true });
    this.userInput = '';

    // Show "thinking..." message
    this.isWaitingForResponse = true;
    this.messages.push({ text: 'Chatbot is thinking...', isUser: false });

    // Make API call
    this.http.post<{ answer: string }>(this.apiUrl, { question: userMessage }).subscribe(
      (response) => {
        // Replace "thinking..." with the actual response
        this.messages[this.messages.length - 1] = { text: response.answer, isUser: false };
        this.isWaitingForResponse = false;
      },
      (error) => {
        // Replace "thinking..." with an error message
        console.error('Error from API:', error);
        this.messages[this.messages.length - 1] = { text: 'An error occurred. Please try again.', isUser: false };
        this.isWaitingForResponse = false;
      }
    );
  }
}
