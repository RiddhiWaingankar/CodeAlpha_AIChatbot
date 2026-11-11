import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

public class AIChatbot extends JFrame {
    private JTextArea chatArea;
    private JTextField inputField;
    private JButton sendButton;
    private Map<String, String> knowledgeBase;

    public AIChatbot() {
        // Setup JFrame Window
        setTitle("CodeAlpha AI Chatbot - Task 3");
        setSize(450, 550);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Initialize Knowledge Base (NLP / Rule-based responses)
        knowledgeBase = new HashMap<>();
        initializeKnowledgeBase();

        // UI Components
        chatArea = new JTextArea();
        chatArea.setEditable(false);
        chatArea.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        chatArea.setLineWrap(true);
        chatArea.setWrapStyleWord(true);
        JScrollPane scrollPane = new JScrollPane(chatArea);

        inputField = new JTextField();
        inputField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        
        sendButton = new JButton("Send");
        sendButton.setFont(new Font("Segoe UI", Font.BOLD, 14));
        sendButton.setBackground(new Color(0, 122, 255));
        sendButton.setForeground(Color.WHITE);

        // Panel for Input & Button
        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.add(inputField, BorderLayout.CENTER);
        bottomPanel.add(sendButton, BorderLayout.EAST);

        // Add to Frame
        add(scrollPane, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);

        // Event Listeners
        sendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                processUserMessage();
            }
        });

        inputField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                processUserMessage();
            }
        });

        // Welcome Message
        chatArea.append("Bot: Hello! I am your AI assistant. How can I help you today with your internship or Java queries?\n\n");
    }

    private void initializeKnowledgeBase() {
        // Greetings
        knowledgeBase.put("hi", "Hello! Welcome to the CodeAlpha AI Chatbot.");
        knowledgeBase.put("hello", "Hi there! How can I assist you today?");
        knowledgeBase.put("hey", "Hey! Ready to talk or code?");
        
        // Internship Info
        knowledgeBase.put("codealpha", "CodeAlpha is an internship program offering hands-on projects in Java, Web Development, AI, and Python.");
        knowledgeBase.put("task", "You are currently working on Task 3: Artificial Intelligence Chatbot!");
        
        // Java & Tech Queries
        knowledgeBase.put("java", "Java is a high-level, class-based, object-oriented programming language designed to have fewer implementation dependencies.");
        knowledgeBase.put("nlp", "Natural Language Processing (NLP) is a branch of AI that helps computers understand, interpret, and manipulate human language.");
        knowledgeBase.put("help", "You can ask me about Java, CodeAlpha, NLP, or just chat with me!");
        
        // Farewells
        knowledgeBase.put("bye", "Goodbye! Best of luck with your internship submission. Have a great day!");
        knowledgeBase.put("exit", "Shutting down chat. Goodbye!");
    }

    private void processUserMessage() {
        String userText = inputField.getText().trim();
        if (userText.isEmpty()) return;

        chatArea.append("You: " + userText + "\n");
        inputField.setText("");

        String response = getBotResponse(userText.toLowerCase());
        chatArea.append("Bot: " + response + "\n\n");
        
        // Auto scroll to bottom
        chatArea.setCaretPosition(chatArea.getDocument().getLength());
    }

    private String getBotResponse(String input) {
        // Simple NLP Token matching / Rule-based lookup
        for (String key : knowledgeBase.keySet()) {
            if (input.contains(key)) {
                return knowledgeBase.get(key);
            }
        }
        
        // Fallback response if no match found
        return "I'm sorry, I didn't quite catch that. Try asking me about 'Java', 'CodeAlpha', 'NLP', or say 'help'.";
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new AIChatbot().setVisible(true);
            }
        });
    }
}