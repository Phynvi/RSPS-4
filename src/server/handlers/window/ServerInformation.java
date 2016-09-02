package server.handlers.window;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.PrintStream;
import java.util.LinkedList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;

import server.root.server;

public class ServerInformation extends JFrame {
	private static final long serialVersionUID = 1L;
  
	JTabbedPane tabs = new JTabbedPane();

	public static TextAreaPanel DebugPanel, ServerTestsPanel,KernelPanel,DelayPanel,SystemPanel,PlayerMessagesPanel,ChatPanel;
	
	public ServerInformation(){
		super();
		setSize(800,600);		
		
		CreateTabs();
		CreateButtons();		
		CreateListeners();
		
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	private JButton delayButton;
	private JButton debugButton;
	private JButton testsButton;
	private JButton averagesButton;
	
	private void CreateListeners(){		
		delayButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(server.showDelay){
					server.showDelay = false;
					delayButton.setText("Enable Delay Mode");
				}
				else{
					server.showDelay = true;
					delayButton.setText("Disable Delay Mode");
				}
			}			
		});
		
		debugButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(server.debugmode){
					server.debugmode = false;
					debugButton.setText("Enable Debug Mode");
				}
				else{
					server.debugmode = true;
					debugButton.setText("Disable Debug Mode");
				}
			}			
		});
		
		testsButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(!server.showDelay){
					server.ServerTestsStream.println("Delay mode must be enabled to run tests.");
				}
				else{
					server.RUN_TESTS = true;
				}
			}			
		});		
		
		averagesButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(!server.showDelay){
					if(server.showAverage){
						server.showAverage = false;
						averagesButton.setText("Enable Averages");
					}
					else{
						server.DelayStream.println("Delay mode must be enabled to display averages.");
					}
				}
				else{
					if(server.showAverage){
						server.showAverage = false;
						averagesButton.setText("Enable Averages");
					}
					else{
						server.showAverage = true;
						averagesButton.setText("Disable Averages");
					}
				}
			}			
		});		
		
	}
	
	private void CreateButtons(){
		delayButton = new JButton("Enable Delay Mode");
		debugButton = new JButton("Enable Debug Mode");
		testsButton = new JButton("Run Tests");
		averagesButton = new JButton("Enable Averages");
		
		DelayPanel.add(delayButton);
		DelayPanel.add(averagesButton);
		DebugPanel.add(debugButton);
		ServerTestsPanel.add(testsButton);
	}
	
	private void CreateTabs(){
		//each tab added to tabs
		SystemPanel = addTextTab("SYSTEM");
		System.setOut(new PrintStream(SystemPanel.getStream()));
		KernelPanel = addTextTab("KERNEL");
		PlayerMessagesPanel = addTextTab("MESSAGES");
		ChatPanel = addTextTab("CHAT");
		
		DelayPanel = addTextTab("DELAY");
		DelayPanel.setLayout(new FlowLayout());
		
		DebugPanel = addTextTab("DEBUG");
		DebugPanel.setLayout(new FlowLayout());
		
		ServerTestsPanel = addTextTab("TESTS");
		ServerTestsPanel.setLayout(new FlowLayout());
		
		//add component
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
		private JTextArea textArea = new JTextArea(30, 50);
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
