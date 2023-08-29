import java.util.*;
import java.util.Map.Entry;

public class Graph 
{
	private HashMap<String, ArrayList<String>> graph = new HashMap<>();
	
	void addVertex(String vertex)
	{
		graph.put(vertex, new ArrayList<String>());
	}
	
	void addEdge(ArrayList<String> list)
	{
		for(int i = 0; i < list.size(); i++)
		{
			String temp = list.get(i);
			for(int j = i+1;  j < list.size(); j++)
			{
				String current = list.get(j);
				if(temp.length() == current.length() && isAdjacent(temp, current))
				{
					if(!graph.containsKey(temp)) //checks if there is a mapped key of the same kind
					{
						addVertex(temp); //creates a vertex if there is not one
					}
					
					if(!graph.containsKey(current))
					{
						addVertex(current);
					}
					
					graph.get(temp).add(current); //adds the "destination" to the source
					graph.get(current).add(temp);
				}
			}
		}
	}
	
	void findShortestDistance(String start, String end)
	{
		Queue<String> queue = new LinkedList<>();
	   	queue.add(start);
	   	ArrayList<String> visited = new ArrayList<>();
//	   	visited.add(start);
	   	HashMap<String, String> predecessor = new HashMap<>();
	   	predecessor.put(null, start);
	   	while(!queue.isEmpty())
	   	{
	   		String temp = queue.remove();
			System.err.println("in loop..." + " current: " + temp);
			visited.add(temp);
	   		if(temp.equals(end))
	   		{
	   		   	String current = end;
	   		   	while(true)
	   		   	{
	   		   		if(current == null)
	   		   		{
	   		   			break;
	   		   		}
	   		   		System.out.println(current + " ");
	   		   		current = predecessor.get(current);
	   		   	}
	   		   	
	   			return;
	   		}
	   		
	   		for(ArrayList<String> current : graph.values())
	   		{
	   			for(String c : current)
	   			{
		   			if(!visited.contains(c) && temp.length() == c.length() &&  isAdjacent(temp, c) && !queue.contains(c))
		   			{
		   				queue.add(c);
		   				visited.add(temp);
		   				predecessor.put(c, temp);
		   			}
	   			}
	   		}
	   	}
	}
	
	boolean isAdjacent(String temp, String current)
	{
		int count = 0;
		for(int i = 0; i < temp.length(); i++)
		{
			if(temp.charAt(i) != current.charAt(i))
			{
				count++;
				if(count > 1)
					return false;
			}
		}
		return count <= 1;
	}
	
	public String toString()
	{
		StringBuilder builder = new StringBuilder();
		for(String vertex : graph.keySet())
		{
			builder.append(vertex.toString() + ": ");
			for(String node: graph.get(vertex))
			{
				builder.append(node.toString() + " ");
			}
			builder.append("\n");
		}
		return builder.toString();
	}
	
}
