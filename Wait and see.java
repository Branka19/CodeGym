package com.codegym.task.task25.task2510;

/* 
All exceptions that occur while the Solution thread is running must be handled by one of these options:
1. If it's an Error, then display "Can't keep running".
2. If it's an Exception, then display "Needs handling".
3. If it's a Throwable, then display "Wait and see".
Implement this logic.
*/
public class Solution extends Thread {

    public Solution() {
        Thread thread = this;
        thread.setUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler()
        {
            @Override
            public void uncaughtException(Thread t, Throwable e){
                if(e instanceof Error)
                System.out.println("Can't keep running");
                else if(e instanceof Exception)
                System.out.println("Needs handling");
                else if(e instanceof Throwable)
                System.out.println("Wait and see");
            }
        });
    }

    public static void main(String[] args) {
        
        }
    }
