package sample.batch.exception;

public class BatchSkipException extends RuntimeException {
	private static final long serialVersionUID = 1L;

    public BatchSkipException() {
    }

    public BatchSkipException(String msg) {
        super(msg);
    }

    public BatchSkipException(Throwable th) {
        super(th);
    }

    public BatchSkipException(String msg, Throwable th) {
        super(msg, th);
    }
}
