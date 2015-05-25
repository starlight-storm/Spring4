package sample.batch.step;

import java.util.List;

import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;


@Component("itemWriter")
public class EntryItemWriter implements ItemWriter<Object> {
	
	public void write(List<? extends Object> data) throws Exception {
		System.out.println(data);
	}
}
