package com.martin.Maindemo;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.log;

import java.awt.event.ItemEvent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.xml.crypto.Data;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.RequestMapping;

import com.martin.Mybatis.Company;
import com.martin.Mybatis.CompanyService;
import com.martin.Repository.Person;

@SpringBootApplication
@ComponentScan(basePackages = {"com.martin.Repository", "com.martin.Maindemo"})
@MapperScan("com.martin.Mybatis")
public class MartinDemoApplication implements CommandLineRunner {

    @Autowired
    private Person person;

    @Autowired
    private CompanyService companyService;

    public static void main(String[] args) {
        SpringApplication.run(MartinDemoApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        TestCompletableFuture();
        MapReduces();
        CompletableFutureTest2();
        CompletableFutureTest1();
        ConCurrentTest2();
//        SchedulerTest();
        ConCurrentTest1() ;
        TestStream();

        // TODO Auto-generated method stub
        // Log("Person.name="+person.getName());
        // List<Company> allcompany=companyService.findAll();
        // Log("Company Count"+allcompany.size());
        // for (Company company : allcompany) {
        // Log("ID:"+company.getId()+", Name:"+company.getCompany_Name()+",
        // WebSite:"+company.getWebsite());
        // }

    }

    public static void Log(String msg) {
        System.out.println(msg);
    }


    private void TestStream() {
        Stream<Double> randoms = Stream.generate(Math::random).limit(10);


        randoms.forEach(System.out::println);
        randoms.peek(pkitem -> System.out.println("peek: " + pkitem));
        // .forEach(item->System.out.println(item));

        List<Double> list = randoms.collect(Collectors.toList());
        for (Object item : list) {
            System.out.println(item);
        }
    }

    private void ConCurrentTest() throws Exception {
        Random rand = new Random();
        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(10000 + rand.nextInt(1000));
                System.out.println("Complete future 1");   
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 100;
        });
        CompletableFuture<Integer> future2 = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(10000 + rand.nextInt(1000));
                System.out.println("Complete future 2");   
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 200;
        });
//        CompletableFuture<String> f =  future.applyToEither(future2,i -> i.toString());
      
        CompletableFuture<String> f =future.applyToEitherAsync(future2, i -> i.toString());
        System.out.println("Receive Data:"+f.get());   
    }
    
    private void ConCurrentTest1() throws Exception {
        Random rand = new Random();
        CompletableFuture<Integer> future = new CompletableFuture<Integer>();
        CompletableFuture<Integer> future2 = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(10000 + rand.nextInt(1000));
                System.out.println("Complete future 2");   
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 200;
        });
//        CompletableFuture<String> f =  future.applyToEither(future2,i -> i.toString());
      
        CompletableFuture<String> f =future.applyToEitherAsync(future2, i -> i.toString());
        
        Thread.sleep(5000 + rand.nextInt(1000));
        future.complete(100);
        System.out.println("Receive Data:"+f.get());   
    }
    
    ScheduledThreadPoolExecutor exec = new ScheduledThreadPoolExecutor(1);
    
    private void ConCurrentTest2() throws Exception {
        Random rand = new Random();
       
//        exec.schedule(()-> System.out.println(format.format(new Date())+ " , This is a schedule thread") , 5000, TimeUnit.MILLISECONDS);
        CompletableFuture<Integer> future = new CompletableFuture<Integer>();
        CompletableFuture<Integer> timer = new CompletableFuture<Integer>();
        exec.schedule(()->{
            System.out.println(format.format(new Date())+ " , This is a schedule thread");
            timer.complete(200);
        }, 3000+rand.nextInt(1000), TimeUnit.MILLISECONDS);
        
        
//        exec.schedule(new Runnable() {
//            public void run() {
//            System.out.println(format.format(new Date())+ " , This is a schedule thread");
//            timer.complete(200);
//        }}, 5000+rand.nextInt(1000), TimeUnit.MILLISECONDS);

        CompletableFuture<String> f =future.applyToEitherAsync(timer, i -> i.toString());
        
        Thread.sleep(10000 + rand.nextInt(1000));
        future.complete(100);
        System.out.println("Receive Data:"+f.get());   
    }
    
    SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private void SchedulerTest() {
        
//         ScheduledThreadPoolExecutor exec = new ScheduledThreadPoolExecutor(1);
//         exec.schedule(()-> System.out.println(format.format(new Date())+ " , This is a schedule thread") , 5000, TimeUnit.MILLISECONDS);
    }
    class Client extends Thread {
        CompletableFuture<Integer> f;
        Client(String threadName, CompletableFuture<Integer> f) {
            super(threadName);
            this.f = f;
        }
        @Override
        public void run() {
            try {
                System.out.println(this.getName() + ": " + f.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
    }
    
    private void CompletableFutureTest1() {
        CompletableFuture<Integer> future = new CompletableFuture<>();
        new Client("Client1", future).start();
        new Client("Client2", future).start();
        System.out.println("waiting");
        future.complete(100);
    }
    
    private void CompletableFutureTest2() throws Exception, ExecutionException {
        Random rand = new Random();
        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(2000 + rand.nextInt(1000));
                System.out.println("future1");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "future1";
        });
        CompletableFuture<String> future2 = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(2000 );
                System.out.println("future2");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "future2";
        });
        CompletableFuture<String> future3 = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(3000 + rand.nextInt(1000));
                System.out.println("future3");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "future3";
        });
      //  CompletableFuture<Void> f =  CompletableFuture.allOf(future1,future2,future3);
        List<CompletableFuture<String>> list= new ArrayList<CompletableFuture<String>>();
        list.add(future1);
        list.add(future2);
        list.add(future3);
//        list.add(future1);
//        list.add(future2);
//        list.add(future3);
        CompletableFuture<List<String>> f=sequenceAsync(list);
        long start = System.nanoTime();
        System.out.println(format.format(new Date())+"---- beginning" );
        try {
            
            String message=String.join(",", f.get(2000,TimeUnit.MILLISECONDS));
            long duration = (System.nanoTime() - start) / 1_000_000;
            System.out.println("Done in " + duration + " msecs, result:" + message);
         
        } catch (TimeoutException e2) {
            System.out.println(e2.getMessage());
        }catch (Exception e3) {
            System.out.println(e3.getMessage());
        }
       // CompletableFuture<Object> f =  CompletableFuture.anyOf(future1,future2);
        long duration1 = (System.nanoTime() - start) / 1_000_000;
        System.out.println("Done in " + duration1 + " msecs" );
    }

    
    public static <T> CompletableFuture<List<T>> sequence(List<CompletableFuture<T>> futures) {
        CompletableFuture<Void> allDoneFuture =  CompletableFuture.allOf(futures.toArray(new CompletableFuture[futures.size()]));
        return allDoneFuture.thenApply(v -> futures.stream().map(CompletableFuture::join).collect(Collectors.<T>toList()));
    }
    
    public static <T> CompletableFuture<List<T>> sequenceAsync(List<CompletableFuture<T>> futures) {
     return CompletableFuture.supplyAsync(()->futures.parallelStream().map(CompletableFuture::join).collect(Collectors.<T>toList()));
    }

    
    public static <T> CompletableFuture<List<T>> sequence(Stream<CompletableFuture<T>> futures) {
        List<CompletableFuture<T>> futureList = futures.filter(f -> f != null).collect(Collectors.toList());
        return sequence(futureList);
    }
 
    private void MapReduces() {
        List<Integer> costBeforeTax = Arrays.asList(100, 200, 300, 400, 500);
        List<Integer> temp1= costBeforeTax.parallelStream().filter(item-> item>=300).map(item-> item+10).collect(Collectors.<Integer>toList());
     //   List<Integer> temp2= costBeforeTax.parallelStream().filter(item-> item>=300).map(item-> item+10).collect(toList());
        temp1.forEach(item->System.out.println(item));
        double bill = (double) costBeforeTax.stream().map((cost) -> cost +10)
                .reduce((sum, cost) -> sum + cost).get();
        System.out.println("Total : " + bill);

    }

    private void TestCompletableFuture() {
        Stream<Double> randoms = Stream.generate(Math::random).limit(10);
        List<CompletableFuture<Double>> list= randoms.map(item-> CompletableFuture.supplyAsync(()->item+1))
                .collect(Collectors.toList());
          
        list.forEach(item-> item.thenAccept(obj->System.out.println(obj)));
        
    }
    
}
