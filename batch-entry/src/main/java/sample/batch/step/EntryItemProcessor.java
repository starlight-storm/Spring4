package sample.batch.step;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Component("itemProcessor")
public class EntryItemProcessor implements
		ItemProcessor<String, String> {

	public String process(String message)
			throws Exception {
		return message + "!!";
	}
}
