package io.spring.taskfaildemo;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.task.configuration.EnableTask;
import org.springframework.cloud.task.listener.annotation.AfterTask;
import org.springframework.cloud.task.listener.annotation.FailedTask;
import org.springframework.cloud.task.repository.TaskExecution;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;

@SpringBootApplication
@EnableTask
public class TaskfaildemoApplication {

	@Autowired
	private DataSource dataSource;

	public static void main(String[] args) {
		SpringApplication.run(TaskfaildemoApplication.class, args);
	}


	@Bean
	public CommandLineRunner commandLineRunner() {
		return args -> {
			System.out.println("Sleeping long...");
			Thread.sleep(2000);
			System.out.println(System.getProperty("user.dir"));
			JdbcTemplate jdbcTemplate = new JdbcTemplate(this.dataSource);

			String sql = "SELECT count(*) FROM task_lock";
			boolean result = false;

//			int count = jdbcTemplate.queryForObject(
//					sql, null, Integer.class);
//			System.out.println("================>" + count);
//			System.out.println("updateCount = " + jdbcTemplate.update("UPDATE TASK_LOCK SET CREATED_DATE=? WHERE REGION=? AND LOCK_KEY=? AND CLIENT_ID=?",
//					new Date(), "DEFAULT", "3676d55f-8449-3cbe-adfc-614c1b1b62fc", 29));


//			throw new IllegalStateException("WHOOPS");
//			BufferedReader br = new BufferedReader(new FileReader(System.getProperty("user.dir")+"/shutdown.pid"));
//			String pid = br.readLine();
//			Runtime.getRuntime().exec("kill -2 " + pid);
//			Thread.sleep(5000);
		};
	}

	@AfterTask
	public void afterTask(TaskExecution taskExecution) {
		System.out.println("HELLO");
	}

	@FailedTask
	public void afterTask(TaskExecution taskExecution, Exception exception) {
		System.out.println("exception****");
	}
}
