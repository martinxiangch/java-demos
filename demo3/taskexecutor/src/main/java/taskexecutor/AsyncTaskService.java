package taskexecutor;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class AsyncTaskService {

	@Async
	public void executeAsyncTask(int i) {
		String id = Thread.currentThread().getName() + "-" + Thread.currentThread().getId();
		System.out.println("ThreadID:" + id + ",async:" + i);
	}

	@Async
	public void executeAsyncTaskPlus(int i) {
		String id = Thread.currentThread().getName() + "-" + Thread.currentThread().getId();
		System.out.println("ThreadID:" + id + ",async +1 :" + (i + 1));
	}
}
