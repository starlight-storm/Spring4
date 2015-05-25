package sample.batch.step;

import org.springframework.batch.item.ItemReader;
import org.springframework.stereotype.Component;

import sample.batch.exception.BatchSkipException;

/**
 * {@link ItemReader} with hard-coded input data.
 */

@Component("itemReader")
public class EntryItemReader implements ItemReader<String> {
	
	private String[] input = {"Hello World", "hoge",  "hoge", "hoge", "こんにちわ。世界", null};
	
	private int index = 0;
	
	/**
	 * Reads next record from input
	 */
	public String read() throws Exception { 
		
		String message = input[index++];
			
		if(message == null) {
			return null;
		}
		if (message.equals("hoge")) {
			throw new BatchSkipException("おかしなデータです ["
					+ message + "]");
		}
		return message;
	}
}
