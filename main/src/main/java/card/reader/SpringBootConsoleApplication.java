package card.reader;

import card.reader.service.ReaderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class SpringBootConsoleApplication implements CommandLineRunner {

    private ReaderService readerService;

    @Autowired
    public SpringBootConsoleApplication(ReaderService readerService) {
        this.readerService = readerService;
    }

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(SpringBootConsoleApplication.class);
        app.setBannerMode(Banner.Mode.OFF);
        app.run(args);
    }

    @Override
    public void run(String... args) {
        try {
            Params params = parseParams(args);
            readerService.read(params.getFileInput(), params.getFileOutput(), params.poolSize);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private Params parseParams(String... args) throws Exception {
        if (args.length < 2) {
            throw new Exception("Wrongs params");
        }
        String fileInput = args[0];
        String fileOutput = args[1];
        int poolSize = 4;
        if (args.length > 2) {
            try {
                poolSize = Integer.parseInt(args[2]);
            } catch (NumberFormatException e) {
                throw new Exception("Wrongs params. Pull size must be integer");
            }
        }
        return Params.builder()
                .fileInput(fileInput)
                .fileOutput(fileOutput)
                .poolSize(poolSize)
                .build();
    }



}