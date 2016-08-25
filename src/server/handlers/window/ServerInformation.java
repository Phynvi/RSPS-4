package server.handlers.window;

import java.awt.BorderLayout;
import java.util.LinkedList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;

public class ServerInformation extends JFrame {
	private static final long serialVersionUID = 1L;
  
	JTabbedPane tabs = new JTabbedPane();
	
	public ServerInformation(){
		super();
		setSize(800,600);		
		add(tabs);
	}
	
	private LinkedList<TextAreaPanel> textTabs = new LinkedList<TextAreaPanel>();
	
	public TextAreaPanel addTextTab(String name){
		TextAreaPanel a = new TextAreaPanel(name);
		textTabs.add(a);
		tabs.addTab(name, a);
		return a;
	}
	
	public TextAreaPanel getTextTab(String name){
		for(TextAreaPanel t : textTabs){
			if(t.getName().equalsIgnoreCase(name))
				return t;
		}
		return null;
	}
	
	public class TextAreaPanel extends JPanel {
		private static final long serialVersionUID = 1L;
		private JTextArea textArea = new JTextArea(15, 30);
	  private TextAreaOutputStream taOutputStream = null;
	  
		public TextAreaPanel(String name){
			taOutputStream = new TextAreaOutputStream(textArea, name);
			setLayout(new BorderLayout());      
			add(new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, 
	        JScrollPane.HORIZONTAL_SCROLLBAR_NEVER));
		}
		
		public TextAreaOutputStream getStream(){
			return taOutputStream;
		}
		
	}
	
}
