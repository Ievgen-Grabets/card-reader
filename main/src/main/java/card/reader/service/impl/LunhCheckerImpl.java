package card.reader.service.impl;

import card.reader.service.LuhnChecker;
import org.springframework.stereotype.Service;

@Service
public class LunhCheckerImpl implements LuhnChecker {

    @Override
    public boolean check(String cardNumber) {
        int sum = 0;
        boolean alternate = false;
        for (int i = cardNumber.length() - 1; i >= 0; i--) {
            int n = Integer.parseInt(cardNumber.substring(i, i + 1));
            if (alternate) {
                n *= 2;
                if (n > 9) {
                    n = (n % 10) + 1;
                }
            }
            sum += n;
            alternate = !alternate;
        }
        return (sum % 10 == 0);
    }

}
