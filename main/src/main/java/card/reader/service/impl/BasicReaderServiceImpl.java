package card.reader.service.impl;

import card.reader.service.CardChecker;
import card.reader.service.ReaderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.io.*;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.stream.Collectors;

@Service
public class BasicReaderServiceImpl implements ReaderService {

    @Autowired
    private CardChecker cardChecker;

    public void read(String fileInput, String fileOutput, int poolSize) throws IOException, ExecutionException, InterruptedException {

        File inputF = new File(fileInput);
        InputStream inputFS = new FileInputStream(inputF);
        BufferedReader bufferedInputFile = new BufferedReader(new InputStreamReader(inputFS));
        ForkJoinPool customThreadPool = new ForkJoinPool(4);

        List<String> resultList = customThreadPool.submit(() -> readCardNumbers(bufferedInputFile)).get();

        PrintWriter writer = new PrintWriter(new File(fileOutput));
        resultList.forEach(writer::write);
        writer.close();
        System.out.println("Success!");

    }

    private List<String> readCardNumbers(BufferedReader bufferedInputFile) {
        return bufferedInputFile.lines()
                .parallel()
                .filter(cardChecker::isValid)
                .collect(Collectors.toList());
    }

}
