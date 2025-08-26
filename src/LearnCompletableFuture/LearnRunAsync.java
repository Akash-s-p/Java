package LearnCompletableFuture;

import java.util.concurrent.CompletableFuture;

/*
    Here I will be learning runAsync() method present in CompletableFuture
    purpose: runAsync method expects a method and does not return anything and does some work
 */
/*
     1) create a method which does some Operation and do not return anything
     2) call that method which will do something in the Background
 */
public class LearnRunAsync {
    static CompletableFuture<String> doSomething() {
        try {
            Thread.sleep(1000);
        } catch (Exception e) {
        }
        return CompletableFuture.runAsync(
                () -> System.out.println("Hello this is akash")
        ).thenApply(
                data -> data + "hello"
        );
    }

    public static void main(String[] args) {
        CompletableFuture<String> afterManipulation = doSomething();
        afterManipulation.join();
    }
}
