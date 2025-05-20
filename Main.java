import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ChatBot chatbot = new ChatBot("JavaBot");

        System.out.println("Welcome! I'm " + chatbot.getName() + ". Type 'quit' to exit.");
        System.out.println("How can I help you today?");

        while (chatbot.isRunning()) {
            System.out.print("You: ");
            String userInput = scanner.nextLine();
            
            if (userInput.trim().isEmpty()) {
                System.out.println("Please say something!");
                continue;
            }

            String response = chatbot.processInput(userInput);
            System.out.println(chatbot.getName() + ": " + response);
        }

        scanner.close();
    }
} 