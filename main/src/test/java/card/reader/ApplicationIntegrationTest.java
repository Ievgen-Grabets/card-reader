package card.reader;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.*;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationIntegrationTest {

    private static final String inputFilePath = "files/input.csv";
    private static final String outputFilePath = "files/output.csv";
    private static final String poolSize = "4";

    @Autowired
    private ApplicationContext ctx;

    @Before
    @After
    public void deleteOutputFile() {
        try {

            File file = new File(outputFilePath);
            if (file.delete()) {
                System.out.println(file.getName() + " is deleted!");
            } else {
                System.out.println("Delete operation is failed.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Test
    public void testRun() throws Exception {
        CommandLineRunner runner = ctx.getBean(CommandLineRunner.class);
        runner.run(inputFilePath, outputFilePath, poolSize);
        File inputF = new File(outputFilePath);
        InputStream inputFS = new FileInputStream(inputF);
        BufferedReader bufferedInputFile = new BufferedReader(new InputStreamReader(inputFS));
        List<String> resultList = bufferedInputFile.lines().collect(Collectors.toList());
        assertTrue(resultList.stream().anyMatch(line -> Objects.equals("Aaa,bbb,5457623898234113,ccc,ddd", line)), "File does not have checked line");
    }

}
