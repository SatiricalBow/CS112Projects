package friends;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Driver 
{
	
	static Scanner stdin = new Scanner(System.in);
	
	public static void main(String[] args)throws IOException
	{
		System.out.print("Enter words file name => ");
		String wordsFile = stdin.nextLine();
		
		Scanner sc = new Scanner(new File(wordsFile));
		
		Graph graph = new Graph(sc);
		System.out.println(graph.map);
		
		//System.out.println(Friends.shortestChain(graph, "p301", "p198"));
		//System.out.println(Friends.cliques(graph, "rutgers"));
		System.out.println(Friends.connectors(graph));
	}

}
