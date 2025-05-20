import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class ChatBotGUI extends JFrame {
    private JTextArea chatArea;
    private JTextField inputField;
    private JButton sendButton;
    private ChatBot chatbot;
    private static final Color BACKGROUND_COLOR = new Color(240, 240, 240);
    private static final Color BOT_MESSAGE_COLOR = new Color(220, 237, 255);
    private static final Color USER_MESSAGE_COLOR = new Color(255, 255, 255);
    private static final Font MESSAGE_FONT = new Font("Segoe UI", Font.PLAIN, 14);
    private static final Font INPUT_FONT = new Font("Segoe UI", Font.PLAIN, 14);

    public ChatBotGUI() {
        chatbot = new ChatBot("JavaBot");
        initializeGUI();
    }

    private void initializeGUI() {
        setTitle("JavaBot - AI Chatbot");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 800);
        setLocationRelativeTo(null);
        setBackground(BACKGROUND_COLOR);

        // Main panel with padding
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        mainPanel.setBackground(BACKGROUND_COLOR);

        // Chat area
        chatArea = new JTextArea();
        chatArea.setEditable(false);
        chatArea.setFont(MESSAGE_FONT);
        chatArea.setLineWrap(true);
        chatArea.setWrapStyleWord(true);
        chatArea.setBackground(BACKGROUND_COLOR);
        
        JScrollPane scrollPane = new JScrollPane(chatArea);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        scrollPane.setBackground(BACKGROUND_COLOR);

        // Input panel
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new BorderLayout(10, 0));
        inputPanel.setBackground(BACKGROUND_COLOR);

        inputField = new JTextField();
        inputField.setFont(INPUT_FONT);
        inputField.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(200, 200, 200)),
            BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));

        sendButton = new JButton("Send");
        sendButton.setFont(new Font("Segoe UI", Font.BOLD, 14));
        sendButton.setBackground(new Color(0, 120, 215));
        sendButton.setForeground(Color.WHITE);
        sendButton.setFocusPainted(false);
        sendButton.setBorder(BorderFactory.createEmptyBorder(5, 15, 5, 15));

        inputPanel.add(inputField, BorderLayout.CENTER);
        inputPanel.add(sendButton, BorderLayout.EAST);

        mainPanel.add(scrollPane, BorderLayout.CENTER);
        mainPanel.add(inputPanel, BorderLayout.SOUTH);

        // Add action listeners
        ActionListener sendAction = e -> sendMessage();
        sendButton.addActionListener(sendAction);
        inputField.addActionListener(sendAction);

        // Add welcome message
        addBotMessage("Hello! I'm " + chatbot.getName() + ". How can I help you today?");

        add(mainPanel);
    }

    private void sendMessage() {
        String message = inputField.getText().trim();
        if (!message.isEmpty()) {
            addUserMessage(message);
            String response = chatbot.processInput(message);
            addBotMessage(response);
            inputField.setText("");
            
            if (!chatbot.isRunning()) {
                inputField.setEnabled(false);
                sendButton.setEnabled(false);
            }
        }
    }

    private void addUserMessage(String message) {
        String time = LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm"));
        chatArea.append(String.format("\n[%s] You: %s\n", time, message));
        chatArea.setCaretPosition(chatArea.getDocument().getLength());
    }

    private void addBotMessage(String message) {
        String time = LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm"));
        chatArea.append(String.format("\n[%s] %s: %s\n", time, chatbot.getName(), message));
        chatArea.setCaretPosition(chatArea.getDocument().getLength());
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception e) {
                e.printStackTrace();
            }
            new ChatBotGUI().setVisible(true);
        });
    }
} 