import java.util.*;
import java.util.regex.*;

public class ChatBot {
    private Map<String, String> responses;
    private List<String> greetings;
    private List<String> farewells;
    private String name;
    private boolean isRunning;

    public ChatBot(String name) {
        this.name = name;
        this.isRunning = true;
        initializeResponses();
    }

    private void initializeResponses() {
        responses = new HashMap<>();
        greetings = Arrays.asList("hello", "hi", "hey", "greetings", "good morning", "good afternoon", "good evening");
        farewells = Arrays.asList("bye", "goodbye", "see you", "farewell", "exit", "quit");

        // Add some basic responses
        responses.put("how are you", "I'm doing well, thank you for asking! How about you?");
        responses.put("what is your name", "I'm " + name + ", nice to meet you!");
        responses.put("what can you do", "I can chat with you, answer questions, and help with basic tasks. Just ask me anything!");
        responses.put("tell me a joke", "Why don't scientists trust atoms? Because they make up everything!");
        responses.put("who created you", "I was created as a Java programming exercise. I'm still learning and improving!");
    }

    public String processInput(String input) {
        input = input.toLowerCase().trim();

        // Check for greetings
        if (greetings.stream().anyMatch(input::contains)) {
            return "Hello! How can I help you today?";
        }

        // Check for farewells
        if (farewells.stream().anyMatch(input::contains)) {
            isRunning = false;
            return "Goodbye! Have a great day!";
        }

        // Check for exact matches in responses
        for (Map.Entry<String, String> entry : responses.entrySet()) {
            if (input.contains(entry.getKey())) {
                return entry.getValue();
            }
        }

        // Default response for unknown inputs
        return "I'm not sure I understand. Could you rephrase that?";
    }

    public boolean isRunning() {
        return isRunning;
    }

    public String getName() {
        return name;
    }
} 