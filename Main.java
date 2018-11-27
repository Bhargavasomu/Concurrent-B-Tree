import java.util.concurrent.TimeUnit;

class Main
{	
	static class LockBasedThread extends Thread 
	{
	    LockBasedBTree tree;
	    int numIterations;
	    String threadName;
	    
	    public LockBasedThread(LockBasedBTree tree, int numIterations, String threadName)  
	    {
	        this.tree = tree;
	        this.numIterations = numIterations;
	        this.threadName = threadName;
	    }
	  
	    @Override
	    public void run()
	    {
            System.out.println("Starting " + threadName);
            if (threadName.equals("A"))
            {
            	for (int i=0; i<numIterations; i++)
            	{
            		System.out.println("Before Insert " + Integer.toString(i+1));
            		try {
						tree.Insert(i+1);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
            		System.out.println("Attempt to Insert " + Integer.toString(i+1));
            	}
            	System.out.println("Over Thread A");
            }
            else if (threadName.equals("B"))
            {
            	for (int i=0; i<numIterations; i++)
            	{
            		System.out.println("Before Check " + Integer.toString(i+1));
            		try {
						tree.Contain(i+1);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
            	}
            	System.out.println("Over Thread B");
            }
            else
            {
            	for (int i=0; i<numIterations; i++)
            	{
            		System.out.println("Before Delete " + Integer.toString(i+1));
            		try {
						tree.Delete(i+1);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
            		System.out.println("Attempt to Delete " + Integer.toString(i+1));
            	}
            }
            return;
	    }
	}
	
	static class LockFreeThread extends Thread 
	{
	    LockBasedBTree tree;
	    int numIterations;
	    String threadName;
	    
	    public LockFreeThread(LockBasedBTree tree, int numIterations, String threadName)  
	    {
	        this.tree = tree;
	        this.numIterations = numIterations;
	        this.threadName = threadName;
	    }
	  
	    @Override
	    public void run()
	    {
            System.out.println("Starting " + threadName);
            if (threadName.equals("A"))
            {
            	for (int i=0; i<numIterations; i++)
            	{
            		System.out.println("Before Insert " + Integer.toString(i+1));
            		try {
						tree.Insert(i+1);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
            		System.out.println("Attempt to Insert " + Integer.toString(i+1));
            	}
            	System.out.println("Over Thread A");
            }
            else if (threadName.equals("B"))
            {
            	for (int i=0; i<numIterations; i++)
            	{
            		System.out.println("Before Check " + Integer.toString(i+1));
            		try {
						tree.Contain(i+1);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
            	}
            	System.out.println("Over Thread B");
            }
            else
            {
            	for (int i=0; i<numIterations; i++)
            	{
            		System.out.println("Before Delete " + Integer.toString(i+1));
            		try {
						tree.Delete(i+1);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
            		System.out.println("Attempt to Delete " + Integer.toString(i+1));
            	}
            }
            return;
	    }
	}
	
    public static void main (String[] args) throws java.lang.Exception
    {
        // create BTree of order 3
//        BTree tree = new BTree(3);
//
//        tree.Insert(5);
//        tree.Insert(6);
//        tree.Insert(7);
//        tree.Insert(8);
//        tree.Insert(9);
//        tree.Insert(10);
//        tree.Delete(7);
//        tree.Delete(8);
//        tree.Show();
    	
    	LockBasedBTree tree1 = new LockBasedBTree(3);
		LockFreeBTree tree2 = new LockFreeBTree(3);
		
        int n = 1000;
        
        // Initiating the Lock Based threads
        LockBasedThread mt1 = new LockBasedThread(tree1, n, "A");
        LockBasedThread mt2 = new LockBasedThread(tree1, n, "B");
        LockBasedThread mt3 = new LockBasedThread(tree1, n, "C");
        
        // Initiating the Lock Free threads
//        LockFreeThread mt1 = new LockFreeThread(tree2, n, "A");
//        LockFreeThread mt2 = new LockFreeThread(tree2, n, "B");
//        LockFreeThread mt3 = new LockFreeThread(tree2, n, "C");  

        // stating threads A,B and C
        mt1.start();
        mt2.start();
        mt3.start();
        
        long startTime = System.nanoTime();
        // waiting for threads A,B and C to complete
        mt1.join();
        mt2.join();
        mt3.join();
        long endTime = System.nanoTime();
        
        long durationNanoSec = endTime - startTime;
        long durationMilliSec = TimeUnit.NANOSECONDS.toMillis(durationNanoSec);
        
        System.out.println(durationMilliSec);
    }
}
