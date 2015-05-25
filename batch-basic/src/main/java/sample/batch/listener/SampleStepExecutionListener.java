package sample.batch.listener;

import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.annotation.AfterStep;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.stereotype.Component;

@Component("sampleStepExecutionListener")
public class SampleStepExecutionListener {

	@BeforeStep
	public void beforeStep(StepExecution stepExecution) {
		System.out.println("*** Before Step :Start Time " + stepExecution.getStartTime());
	}
	
	@AfterStep
	public ExitStatus afterStep(StepExecution stepExecution) {
		System.out.println("*** After Step :Commit Count " + stepExecution.getCommitCount());
		return ExitStatus.COMPLETED;
	}
}
