package friends;

import java.util.ArrayList;

import structures.Queue;
import structures.Stack;

public class Friends 
{

	/**
	 * Finds the shortest chain of people from p1 to p2.
	 * Chain is returned as a sequence of names starting with p1,
	 * and ending with p2. Each pair (n1,n2) of consecutive names in
	 * the returned chain is an edge in the graph.
	 * 
	 * @param g Graph for which shortest chain is to be found.
	 * @param p1 Person with whom the chain originates
	 * @param p2 Person at whom the chain terminates
	 * @return The shortest chain from p1 to p2. Null or empty array list if there is no
	 *         path from p1 to p2
	 */
	
	
	public static ArrayList<String> shortestChain(Graph g, String p1, String p2) 
	{
		if(g.map.get(p1) == null)
		{
			return new ArrayList<String>();
		}
		if(p1.equals(p2))
		{
			ArrayList<String> list = new ArrayList<>();
			list.add(p1);
			return list;
		}
		int s = g.map.get(p1);
		boolean[] visited = new boolean[g.members.length];
		
		
		
		//System.out.println("\n");
		ArrayList<String> list = new ArrayList<>();
		Queue<Integer> queue = new Queue<>();
		ArrayList<ArrayList<Integer>> que = new ArrayList<>();
		visited[g.map.get(p1)] = true;
		
		Person person = g.members[s];
		
		
		queue.enqueue(s);
		boolean foundString = false;
		
		ArrayList<Integer> caller = new ArrayList<>();
		int c = queue.dequeue();
		caller.add(c);
		que.add(caller);
		for(Friend friend = g.members[c].first; friend != null; friend = friend.next)
		{
			ArrayList<Integer> guy = (ArrayList<Integer>)caller.clone();
			int num = friend.fnum;
			if(!visited[num])
			{
				visited[num] = true;
				queue.enqueue(num);
			}
			guy.add(num);
			que.add(guy);
		}
	
		
		while(!queue.isEmpty())
		{
			//System.out.println(que);
			ArrayList<Integer> temp = new ArrayList<>();
			int b = queue.dequeue();
			for(int i = que.size()-1; i > -1; i --)
			{
				ArrayList<Integer> guy = que.get(i);
				if(guy.contains(b))
				{
					temp=(ArrayList<Integer>)guy.clone();
				}
			}
			
			
			//System.out.println("B: " + que.get(que.indexOf(b)));
			
			for(Friend friend = g.members[b].first; friend != null; friend = friend.next)
			{
				int num = friend.fnum;
				ArrayList<Integer> guy = (ArrayList<Integer>)temp.clone();
				if(!visited[num])
				{
					
				//	System.out.println("Visiting " + g.members[num].name);
					visited[num] = true;
					queue.enqueue(num);
					
					
					
				}
				if(!guy.contains(num))
				{
					guy.add(num);
					que.add(guy);
				}
				
			}
			
			
			
			
		}
		
		int queMin = -1;
		//System.out.println(que);
		for(int i = 0; i < que.size(); i ++)
		{
			
			ArrayList<Integer> boi = que.get(i);
			for (int j = 0; j < boi.size(); j ++)
			{
				
				if(g.members[boi.get(j)].name.equals(p2))
				{
					
					if(queMin == -1)
					{
						queMin = i;
					}
					else
					{
						if(boi.size() < que.get(queMin).size())
						{
							queMin = i;
						}
					}
				}
			}
			
		}
		
		if(queMin != -1)
		{
			for(int i = 0; i < que.get(queMin).size(); i ++)
			{
				list.add(g.members[que.get(queMin).get(i)].name);
			}
		}
	
		return list;
		
	}
	
	/**
	 * Finds all cliques of students in a given school.
	 * 
	 * Returns an array list of array lists - each constituent array list contains
	 * the names of all students in a clique.
	 * 
	 * @param g Graph for which cliques are to be found.
	 * @param school Name of school
	 * @return Array list of clique array lists. Null or empty array list if there is no student in the
	 *         given school
	 */
	/*private static void bfs(Graph g, String school,int i,boolean[] visited, Queue<Integer> queue, ArrayList<ArrayList<Integer>> intList)
	{
		
		if(!visited[i])
		{
			while(!queue.isEmpty())
			{
				int b = queue.dequeue();
				ArrayList<Integer> boi = new ArrayList<Integer>();
				boi.add(b);
				for(Friend friend = g.members[b].first; friend != null; friend = friend.next)
				{
					int num = friend.fnum;
					
					if(!visited[num])
					{
						queue.enqueue(num);
						boi.add(num);
					}
					visited[num] = true;
				}
				
				intList.add(boi);
				System.out.println(boi);
				
			}
		}
	}
	*/
	public static ArrayList<ArrayList<String>> cliques(Graph g, String school)
	{
		ArrayList<ArrayList<String>> cliquesList = new ArrayList<ArrayList<String>>();
		if(g.map.size() == 0)
		{
			return new ArrayList<ArrayList<String>>();
		}
		boolean[] visited = new boolean[g.members.length];
		ArrayList<ArrayList<Integer>> que = new ArrayList<>();
		
		for(int h = 0; h < g.members.length; h ++)
		{
			int s = 0;
			if(g.members[h].school != null && g.members[h].school.equals(school))
			{
				s = h;
			}
			else
				continue;
			//System.out.println("\n");
			ArrayList<String> list = new ArrayList<>();
			Queue<Integer> queue = new Queue<>();
			
			if(visited[s])
				continue;
			visited[s] = true;
			
			Person person = g.members[s];
			
			
			queue.enqueue(s);
			boolean foundString = false;
			
			ArrayList<Integer> caller = new ArrayList<>();
			int c = queue.dequeue();
			caller.add(c);
			que.add(caller);
			ArrayList<Integer> dam = (ArrayList<Integer>)caller.clone();
			for(Friend friend = g.members[c].first; friend != null; friend = friend.next)
			{
				
				int num = friend.fnum;
				if(!visited[num])
				{
					visited[num] = true;
					if(g.members[num].school != null && g.members[num].school.equals(school))
						queue.enqueue(num);
				}
				if(g.members[num].school != null && g.members[num].school.equals(school))
				{
					dam.add(num);
					
				}
			}
			que.add(dam);
		
			
			while(!queue.isEmpty())
			{
				//System.out.println(que);
				ArrayList<Integer> temp = new ArrayList<>();
				int b = queue.dequeue();
				for(int i = que.size()-1; i > -1; i --)
				{
					ArrayList<Integer> guy = que.get(i);
					if(guy.contains(b))
					{
						temp=(ArrayList<Integer>)guy.clone();
					}
				}
				
				
				//System.out.println("B: " + que.get(que.indexOf(b)));
				ArrayList<Integer> guy = (ArrayList<Integer>)temp.clone();
				for(Friend friend = g.members[b].first; friend != null; friend = friend.next)
				{
					int num = friend.fnum;
					
					if(!visited[num])
					{
						
					//	System.out.println("Visiting " + g.members[num].name);
						visited[num] = true;
						if(g.members[num].school != null && g.members[num].school.equals(school))
							queue.enqueue(num);
					}
					if(!guy.contains(num))
					{
						if(g.members[num].school != null && g.members[num].school.equals(school))
						{
							guy.add(num);
							
						}
					}
					
				}
				
				que.add(guy);
				
				
				
				
			}
		}
		
		System.out.println(que);
		for(int i = 0; i < que.size(); i ++)
		{
			ArrayList<Integer> boi = que.get(i);
			if(i+1 < que.size() && que.get(i+1).contains(boi.get(boi.size()-1)))
			{
				continue;
			}
			else
			{
				ArrayList<String> guy = new ArrayList<String>();
				for(int j = 0; j < boi.size(); j ++)
				{
					guy.add(g.members[boi.get(j)].name);
				}
				cliquesList.add(guy);
			}
			
		}
		
		
		return cliquesList;
	}
	
	/**
	 * Finds and returns all connectors in the graph.
	 * 
	 * @param g Graph for which connectors needs to be found.
	 * @return Names of all connectors. Null or empty array list if there are no connectors.
	 */
	public static ArrayList<String> connectors(Graph g) 
	{
		if(g.members.length <= 2)
		{
			return new ArrayList<String>();
		}
		boolean[] visited = new boolean[g.members.length];
		int[] visitedNum = new int[g.members.length];
		for(int i = 0; i < visitedNum.length; i ++)
		{
			visitedNum[i] = 0;
		}
		ArrayList<String> list = new ArrayList<String>();
		for(int i = 0; i < g.members.length; i ++)
		{
			if(!visited[i])
			{
				dfs(g,visited,list,i, visitedNum);
			}
			else
			{
				visitedNum[i] ++;
			}
		}
		
		for (int i = 0; i < visitedNum.length; i ++)
		{
			System.out.println(g.members[i].name + ": " + visitedNum[i]);
		}
		

		for(int i = 0; i < visitedNum.length; i ++)
		{
			if(visitedNum[i] == 1)
			{
				if(!list.contains(g.members[(g.members[i].first.fnum)].name))
					list.add(g.members[(g.members[i].first.fnum)].name);
			}
		}
		
		return list;
		
	}
	private static void dfs(Graph g, boolean[] visited, ArrayList<String> list, int i, int[] visitedNum)
	{
		visited[i] = true;
		for(Friend friend = g.members[i].first; friend != null; friend = friend.next)
		{
			int num = friend.fnum;
			if(!visited[num])
			{
				
				dfs(g,visited,list,num,visitedNum);
			}
			else
			{
				visitedNum[num] ++;
			}
		}
	}
	
}

