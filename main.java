import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

//Pid manager to manage process identifiers
//The program assigns a unique pid to each process that is created
//The pid is returned to the pid manager when the process completes execution
//10 processes are executed each with a unique pid

public class Main {
	
     private ArrayList pidList = new ArrayList();
	

     public int allocate_map() {
		
	//initialize pidList with values 300-5000
    	 
		int MIN_PID = 300;
		
		int MAX_PID = 5000;
		
		for (int i= MIN_PID; i <= MAX_PID; i++ ) {
			     pidList.add(i);
		}
	//if pidList failed to initialize (the list is empty) return -1
		
		if(pidList.isEmpty()== true)
		
		return -1;
		
		else 
			return 1;
		
	}
	
	
	public int allocate_pid() {
		
	 //Create random variable to get a random pid from the pidList 
		
		try {
		Random rand = new Random();
		
		int random = rand.nextInt(pidList.size());
		
	    int pid1 =	(int) pidList.get(random);
	    
	 //Once a pid is taken, remove it from the list to let the user know it is not available
	    
	    pidList.remove(random);
		
	    return pid1;
	    
	       
		}
	//If there are no pids available, an error will occur and it will return -1
		catch(Exception e) {
	     
		return -1;
		}
		
	}
	
	
	public void release_pid(int pid) {
		
	//Once a user is done with a pid, this method may be invoked and it will add the pid back to the list 
		
		pidList.add(pid);
		
		
	}
	
	
	
  public static void main(String[] args) {
		
	
	       Threads threads = new Threads();
           threads.start();
	   
         
	  }
	
	

}


class Threads extends Thread {
    public void run(){
    	
    	//Create Pid object to call its methods	
    	
    	Main pid = new Main();
    //	private ReentrantLock mutex = new ReentrantLock();
    	
    	//initialize pidlist
   	 
    	pid.allocate_map();
    	
    	
        try {
           
        	int p;
        	
        	//Create 10 threads
        	
            int n = 10;
            
            for (int i = 0; i < n; i++) {
            	
            //get pid	
            	
            //use the synchronized block to lock the thread and it will be released when the block completes 
            	
            synchronized(pid) {
            	
            p=pid.allocate_pid();
        	
            System.out.println("Thread with pid " + p + " is running");
            
            //thread sleeps for 1 second
            Thread.sleep(1000);
            
            //release pid after 1 second
            pid.release_pid(p);
            
            }
            
          }
            
      }
        
        catch (Exception e) {
           
            System.out.println("Exception");
        }
        
      }
    
    }
