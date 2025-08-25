// Learning on SupplyAsync Method
/*
    We are learning here why we are Using CompletableFuture in my project
    purpose:  Don't waste a Thread in waiting state go and serve new request
    In normal (blocking) code:
        You already have data before moving ahead
        but thread have to wait for the data to come
        then move forward
    In completableFuture(async) code(Non Blocking code):
        here you don't have to wait for the data to come
        thread will be placed into the pool for serving other request

        ->So the main Trick here is:
           *) you don't have to operate on the data immediately
           *) we have operation which will do when data arrives
              operations are thenApply,thenAccept,thenCombine,etc)
 */
/*
    Below is the Example
    1) create a method which simulates db
    2) in main method call the db method from 2 users
    3) create a object which will wait for both the requests to complete
    4) once both values come join both and print it

 */
/*
     Concept I learnt today is you can't access a method which is non static
     why Because when you run a program JVM identifies only static Methods

     JVM  ------calls----> static methods

     Normal methods without static Fields are considered as Non static methods
     can only be accessed using objects of a class or Specifiying static to the method

 */
import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;

public class LearnCompletableFuture
{
    static CompletableFuture<String> getData(String a)
    {
        //supplyAsync is a method that can run in a backgroud that expects a mthos and return a result
       return CompletableFuture.supplyAsync
     (
               supplyMethod(a)
     );
    }
    static Supplier<String> supplyMethod(String name)
    {
        long start=System.currentTimeMillis();
        try{
            Thread.sleep(1000);
        }
        catch (Exception e){}
        return () -> name.equalsIgnoreCase("Akash")? "data of Akash": "data of Mahima";
    }
    public static void main(String[] args)
    {
        long start=System.currentTimeMillis();
        CompletableFuture<String> userA=getData("Akash");
        CompletableFuture<String> userB=getData("Mahima");

        //this is required to program to wait to complete both the operation
        CompletableFuture<Void> operation=CompletableFuture.allOf(userA,userB);

        operation.join()
        long end=System.currentTimeMillis();

        System.out.println(userA.join());
        System.out.println(userB.join());
        System.out.println("Time taken is "+(start-end));
     }
}

/*
output: this program will take only 2 seconds to complete both operations run parllely
data of Akash
data of Mahima
Time taken is -2048
 */