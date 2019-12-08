package betrayalatthetextonthescreen;

/**
 * GUI Class - instantiated for each player (currently 1 player).<br>
 * Constructor initializes JFrame with a scollable text area for output, an editable
 * text field for input from the user, an action listener to accept input, and a parser.<br>
 * <P>
 * Java swing text window made with help from: <br>
 * http://asimdlv.com/java-swing-auto-scrolling-jscrollpane-i-e-chat-window/<br>
 * <P>
 * Instance variables: number of rows and collumns for text area sizing, window size dimensions
 * for window, text area, and parser.<br>
 * Methods: write GUI.<br>
 * <P>
 * @author Pippy Vallone, Trinity Headen, and Michael Elijius
 */
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class GUI extends JFrame{
    private final int NUM_ROWS = 15;
    private final int NUM_COLUMNS = 60;
    private final Dimension WINDOW_SIZE = new Dimension(500, 300);
    private final int WINDOW_XSIZE = 600;
    private final int WINDOW_YSIZE = 400;
    private Parser parser = new Parser(this);
    private final JTextArea textArea = new JTextArea(NUM_COLUMNS, NUM_ROWS);
    
    public GUI(Player player, String name) 
    {
	super("Text Adventure Game " + name);
	JScrollPane scrollPane = new JScrollPane(textArea);
	scrollPane.setPreferredSize(WINDOW_SIZE);
	textArea.setLineWrap(true);
	textArea.setWrapStyleWord(true);
	textArea.setEditable(false);
	scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        final JTextField userInputField = new JTextField(NUM_ROWS);
	userInputField.addActionListener(
            new ActionListener()
            {
                public void actionPerformed(ActionEvent event)
                {
                    String fromUser = userInputField.getText();
                    if (fromUser != null) {
                        textArea.append(fromUser + "\n\n");
                        textArea.setCaretPosition(textArea.getDocument().getLength());
                        userInputField.setText("");
                        String[] userInputArray = parser.parseInput(fromUser);
                        player.parse(userInputArray);
                    }
                }
            }
        );
        this.setLayout(new FlowLayout());
        this.add(userInputField, SwingConstants.CENTER);
        this.add(scrollPane, SwingConstants.CENTER);
        this.setSize(WINDOW_XSIZE, WINDOW_YSIZE);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setVisible(true);
    }
    
    /**
     * Write GUI Method<br>
     * Writes the specified message into the output text area on a new line.<br>
     * @param message String message to be printed.<br>
     */
    public void writeGUI(String message)
    {
        textArea.append(message + "\n");
    }

}
