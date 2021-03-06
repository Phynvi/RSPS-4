import java.io.*;
import java.util.*;

public class Finder {

public static void main(String args[]) {

  System.out.print("Enter string to find: ");
  Scanner sc = new Scanner(System.in);
  find(sc.nextLine());

}

	public static void find(String delim) {
		File dir = new File("connectedFrom");
		if(dir.exists()) {
			String read;
			try {
				File files[] = dir.listFiles();
				for(int i = 0; i < files.length; i++) {
					File loaded = files[i];
					if(loaded.getName().endsWith(".txt")) {
						System.out.println("Searching " + loaded.getName());
						BufferedReader in = new BufferedReader(new FileReader(loaded));
						StringBuffer load = new StringBuffer();
						while((read = in.readLine()) != null) {
							load.append(read + "\n");
						}
						String delimiter[] = new String(load).split(delim);
						if(delimiter.length > 1) {
							System.out.println("Found " + (delimiter.length - 1) + " time(s) in " + loaded.getName() + "!");
						}
					}
				}
			} catch(Exception e) {
				e.printStackTrace();
			}
		} else {
			System.out.println("error: dir wasn't found!");
		}
	}

	//public static void main(String args[]) {
	//	Finder finder = new Finder();
	//	finder.find("variable");
	//}
}