package sample.batch.listener;

import org.springframework.batch.core.annotation.AfterChunk;
import org.springframework.batch.core.annotation.BeforeChunk;
import org.springframework.stereotype.Component;

@Component
public class SampleChunkListener {

	@BeforeChunk
	public void beforeChunk() {
		System.out.println("*** before Chunk");
	}
	
	@AfterChunk
	public void afterChunk() {
		System.out.println("*** after Chunk");
	}
}
