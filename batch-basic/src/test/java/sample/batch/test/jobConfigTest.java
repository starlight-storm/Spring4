package sample.batch.test;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@ContextConfiguration(locations={"batch-context.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class jobConfigTest {
	@Autowired
	private JobLauncher jobLauncher;

	@Autowired
	private Job job;

	@Test
	public void testJobLauncher() throws Exception {
		assertNotNull(jobLauncher);
	}

	@Test
	public void testRun() throws Exception {
		jobLauncher.run(job, new JobParameters());
	}
}
