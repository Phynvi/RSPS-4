package server.handlers.window;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;

import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

import server.root.server;

//Taken from http://stackoverflow.com/questions/9776465/how-to-visualize-console-java-in-jframe-jpanel
public class TextAreaOutputStream extends OutputStream {
		private PrintStream printStream;
	
   private final JTextArea textArea;
   private final StringBuilder sb = new StringBuilder();
   private String title;

   public TextAreaOutputStream(final JTextArea textArea, String title) {
      this.textArea = textArea;
      this.title = title;
      sb.append(title + "> ");
      printStream = new PrintStream(this);
   }
   
   public void print(String s){
  	 printStream.print(s);
   }
   
   public void println(String s){
  	 printStream.println(s);
   }
   
   public void println(){
  	 printStream.println();
   }
   
   @Override
   public void flush() {
   }

   @Override
   public void close() {
   }

   @Override
   public void write(int b) throws IOException {

      if (b == '\r')
         return;

      if (b == '\n') {
         final String text = sb.toString() + "\n";
         SwingUtilities.invokeLater(new Runnable() {
            public void run() {
               textArea.append(text);
            }
         });
         sb.setLength(0);
         sb.append(title + "> ");
         return;
      }

      sb.append((char) b);
   }
}