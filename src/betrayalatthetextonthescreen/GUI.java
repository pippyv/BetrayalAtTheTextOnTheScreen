
package betrayalatthetextonthescreen;

/**
 * Java swing text window
 *   borrowed heavily from:
 *    http://asimdlv.com/java-swing-auto-scrolling-jscrollpane-i-e-chat-window/
 * 
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
    private final int NUM_ROWS = 30;
    private final int NUM_COLUMNS = 60;
    private final Dimension WINDOW_SIZE = new Dimension(500, 300);
    private final int WINDOW_XSIZE = 600;
    private final int WINDOW_YSIZE = 400;
    private Parser parser = new Parser();
    private final JTextArea textArea = new JTextArea(NUM_COLUMNS, NUM_ROWS);
    
    public GUI() {
	super("Text Adventure Game");
	// We create a TextArea object

	// We put the TextArea object in a Scrollable Pane
	JScrollPane scrollPane = new JScrollPane(textArea);

	// In order to ensure the scroll Pane object appears in your window,
	// set a preferred size to it!
	scrollPane.setPreferredSize(WINDOW_SIZE);

	// Lines will be wrapped if they are too long to fit within the
	// allocated width
	textArea.setLineWrap(true);

	// Lines will be wrapped at word boundaries (whitespace) if they are
	// too long to fit within the allocated width
	textArea.setWrapStyleWord(true);

	// Assuming this is the chat client's window where we read text sent out
	// and received, we don't want our Text Area to be editable!
	textArea.setEditable(false);

	// We also want a vertical scroll bar on our pane, as text is added to it
	scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

	// Now let's just add a Text Field for user input, and make sure our
	// text area stays on the last line as subsequent lines are
	// added and auto-scrolls

	final JTextField userInputField = new JTextField(NUM_ROWS);
        
	userInputField.addActionListener(
            new ActionListener(){
                public void actionPerformed(ActionEvent event){

                    //We get the text from the textfield
                    String fromUser = userInputField.getText();

                    if (fromUser != null) {
                        //We append the text from the user
                        textArea.append("Command: " + fromUser + "\n");

                        //The pane auto-scrolls with each new response added
                        textArea.setCaretPosition(textArea.getDocument().getLength());

                        //We reset our text field to "" each time the user presses Enter
                        userInputField.setText("");

                    // ToDo call application code from here
                        String[] userInputArray = parser.parseInput(fromUser);
                        BetrayalAtTheTextOnTheScreen.parse(userInputArray);
                    }
                }
            }
        );

        this.setLayout(new FlowLayout());
        //adds and centers the text field to the frame
        this.add(userInputField, SwingConstants.CENTER);
        //adds and centers the scroll pane to the frame
        this.add(scrollPane, SwingConstants.CENTER);

        // Set size, default close operation, resizability boolean and overall contents visibility
        this.setSize(WINDOW_XSIZE, WINDOW_YSIZE);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setVisible(true);
    }
    
    public void writeGUI(String message)
    {
        textArea.append(message + "\n");
    }

}
