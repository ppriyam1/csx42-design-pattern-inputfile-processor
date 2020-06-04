package wordPlay.driver;

import wordPlay.util.FileProcessor;

/**
 * @author Preeti Priyam
 *
 */
public class Driver {
	public static void main(String[] args) {

		/*
		 * As the build.xml specifies the arguments as input,output or metrics, in case the
		 * argument value is not given java takes the default value specified in
		 * build.xml. To avoid that, below condition is used
		 */
		if ((args.length != 3) || (args[0].equals("${input}")) || (args[1].equals("${output}")) || (args[2].equals("${metrics}"))) {
			System.err.println("Error: Incorrect number of arguments. Program accepts 3 arguments.");
			System.exit(0);
		}
		System.out.println("Hello World! Lets get started with the assignment");
		FileProcessor fp = null;
		try{
			fp = new FileProcessor(args[0]);
		  String sr = fp.poll();// Poll is a linkedlist that returns each word from the file
			int counter = 0;
		  while(sr != null){ // To loop around each sentence in the file
				char [] ch = sr. toCharArray(); // to store characters in an array
				counter++;
				String result = "";
					result += sr.substring(ch.length-counter);
					result += sr.substring(0, ch.length-counter);
				System.out.println(result);
				sr = fp.poll();

			}
			}
		catch(Exception FileNotFoundException){
			System.out.println("FileNotFound");
			System.exit(0);
		}
		//System.out.println(fp.poll());

	}
}
