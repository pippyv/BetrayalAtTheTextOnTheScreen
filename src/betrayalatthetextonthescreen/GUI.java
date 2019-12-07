
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
    
    public void writeGUI(String message)
    {
        textArea.append(message + "\n");
    }

}
