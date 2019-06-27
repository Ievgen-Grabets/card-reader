package card.reader.service;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

public interface ReaderService {

    void read(String fileInput, String fileOutput,  int poolSize) throws IOException, ExecutionException, InterruptedException;

}
