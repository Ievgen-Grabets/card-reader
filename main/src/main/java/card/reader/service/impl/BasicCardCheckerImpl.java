package card.reader.service.impl;

import card.reader.service.CardChecker;
import card.reader.service.LuhnChecker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Objects;
import java.util.Scanner;

@Service
public class BasicCardCheckerImpl implements CardChecker {

    @Autowired
    private LuhnChecker luhnChecker;

    @Override
    public boolean isValid(String line) {
        Scanner scanner = new Scanner(line);
        String number = scanner.findInLine("\\d{16}");
        scanner.close();
        if(Objects.isNull(number)){
            return false;
        }
        return luhnChecker.check(number);
    }
}
