package Test;

public class ThreadExceptionHandlingExample {

    public static void main(String[] args) {
        // Create a thread with a lambda expression
        Thread thread = new Thread(() -> {
            try {
                // Code that may throw an exception
                processData();
            } catch (Exception e) {
                // Handle exception within the thread
                System.out.println("Exception caught in thread: " + e.getMessage());
            }
        });

        // Set an uncaught exception handler for the thread
        thread.setUncaughtExceptionHandler((t, e) -> {
            // Handle uncaught exceptions from the thread
            System.out.println("Uncaught exception in thread " + t.getName() + ": " + e.getMessage());
            e.printStackTrace();
        });

        // Start the thread
        thread.start();
    }

    // Method that might throw an exception
    private static void processData() throws Exception {
        // Simulating an exception
        throw new Exception("Simulated exception");
    }
}

