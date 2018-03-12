package taskexecutor;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class taskmain {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(TaskConfig.class);
        AsyncTaskService async = context.getBean(AsyncTaskService.class);
        for (int i = 0; i < 10; i++) {
            async.executeAsyncTask(i);
            async.executeAsyncTaskPlus(i);
        }
        context.close();
    }

}
