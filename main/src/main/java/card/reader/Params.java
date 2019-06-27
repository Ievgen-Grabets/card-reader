package card.reader;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Builder
@Getter
public class Params {
    private String fileInput;
    private String fileOutput;
    int poolSize;
}