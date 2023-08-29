import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;

public class Driver {

	public static void main(String[] args) {
		ArrayList<String> dict = new ArrayList<>();
		try
		{
			File file = new File("Dict.txt");
			Scanner fileReader = new Scanner(file);
			while(fileReader.hasNextLine())
			{
				dict.add(fileReader.nextLine());
			}
			
			fileReader.close();
		}
		catch(FileNotFoundException e)
		{
			System.out.println("File Not Found!");
			e.printStackTrace();
		}
		Graph buildgraph = new Graph();
		buildgraph.addEdge(dict);
		System.out.println(buildgraph.toString());
		while(true)
		{
			Scanner input = new Scanner(System.in);
			System.out.println("Enter starting word: ");
			String start = input.nextLine();
			System.out.println("Enter ending word: ");
			String end = input.nextLine();
			buildgraph.findShortestDistance(start, end);
			System.out.println("Do you want to quit?");
			String quit = input.nextLine();
			if(quit.equals("y"))
			{
				break;
			}
			else
			{
				continue;
			}
				
		}

	}
}
