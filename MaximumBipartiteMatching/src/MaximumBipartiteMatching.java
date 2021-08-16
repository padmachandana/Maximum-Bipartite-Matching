import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class MaximumBipartiteMatching {
	int top=-1,size=10;
		int[] s=new int[size];
		
   static class Graph {
        int jobs,job;
        int applicants;
       public  int adjMatrix[][];
       
        public Graph(int applicants, int jobs) {
            this.jobs = jobs;
            this.applicants = applicants;
            adjMatrix = new int[applicants][jobs];
        }

        public void canDoJob(int applicant, int job) {
            adjMatrix[applicant][job] = 1;
        }
    }
    public void push(int element) {
   		if(top==size-1)
   			System.out.println("Stack is full");
   		else {
   			s[++top]=element;
   		}
   	}
 
    public int maxMatching(Graph graph) {
        int applicants = graph.applicants;
        int jobs = graph.jobs;
        int i;

        int assign[] = new int[jobs+2];   
        for ( i = 0; i < jobs; i++)
            assign[i] = -1;   
        int jobCount = 0;

        for (int applicant = 0; applicant < applicants; applicant++) {    
            boolean visited[] = new boolean[jobs];
            if (bipartiteMatch(graph, applicant, visited, assign)) {
                jobCount++;  
            }
        }
        return jobCount;
    }

    boolean  bipartiteMatch(Graph graph, int applicant, boolean visited[], int assign[]) {
        for (int job = 0; job < graph.jobs; job++) {
            if (graph.adjMatrix[applicant][job] == 1 && !visited[job]) {
                visited[job] = true;
                int assignedApplicant = assign[job];
                if (assignedApplicant < 0 || bipartiteMatch(graph, assignedApplicant, visited, assign)) {
                    assign[job] = applicant;    
                     push(job);
                    return true;
                }
            }
        }
        return false;
    }
    public static void main(String[] args) {
        int applicants = 6;
        int jobs = 6;
        Graph graph = new Graph(applicants, jobs);
        graph.canDoJob(0, 0);
        graph.canDoJob(0, 1);
        graph.canDoJob(0, 2);
        graph.canDoJob(1, 0);
        graph.canDoJob(2, 1);
        graph.canDoJob(3, 4);
        graph.canDoJob(4, 3);
        graph.canDoJob(4, 4);
        graph.canDoJob(5, 5);
System.out.println("\n**************************  Maximum Bipartite Matching for applicants and Jobs  ***********************");
MaximumBipartiteMatching m = new MaximumBipartiteMatching();
System.out.println("\nMaximum number of applicants that could" +
        " get jobs are: " + m.maxMatching(graph));
System.out.println("\nJobs allocated to Applicants \n\nApplicants  Jobs");
        String data[][]=new String[6][6];
        String[] companies= {"Amazon","Adobe","Boach","Google","Infosis","Microscoft"};
		String[] names= {"Alex","Anil","Chase","Pam","Sam","Jade"};
    
      for(int i=0;i<=5;i++) {
        	System.out.println(i+"       -->  "+m.s[i+2]);
        	for(int j=0;j<2;j++) {
        		if(j==0)
        	      data[i][j]=names[i];
        		else
        			data[i][j]=companies[m.s[i+2]];
        	}
      }
          
      JFrame f=new JFrame("Bipartite Matching");
      JLabel namelabel= new JLabel("Maximum Bipartite Matching for applicants and Jobs ");
      namelabel.setBounds(10, 20, 300, 100);
      String column[]={"Applicants","Companies"}; 
      JTable jt=new JTable(data,column);
      JScrollPane js=new JScrollPane(jt); 
      js.setBounds(30,100,300,100);
      f.add(js);
      f.add(namelabel);
      f.setSize(400,500);
      f.setLayout(null); 
      f.setVisible(true);
      }
       
    }
