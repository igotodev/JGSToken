package by.godev.gen.jgstoken;

import java.util.Objects;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class JGSToken {
    private String token;
    private int len;
    private boolean withSpecialChars;

    private static final char[] LETTER_U;
    private static final char[] LETTER_L;
    private static final char[] SYMBOL;

    static {
        LETTER_U = new char[]{'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H',
                'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P',
                'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
        LETTER_L = new char[]{'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h',
                'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p',
                'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
        SYMBOL = new char[]{'!', '@', '#', '$', '%', '&', '?'};

    }

    public JGSToken(int len, boolean withSpecialChars) {
        this.len = len;
        this.withSpecialChars = withSpecialChars;

        if (withSpecialChars) {
            this.token = createToken(len);
        }
        if (!withSpecialChars) {
            this.token = createTokenDL(len);
        }
    }

    // Default
    public JGSToken() {
        this.len = 25;
        this.withSpecialChars = true;
        this.token = createToken(len);
    }

    // Create new token with same param
    public String createNewToken() {
        return this.token = new JGSToken(this.len, this.withSpecialChars).getToken();
    }

    // Create new token with new param
    public String createNewToken(int len, boolean withSpecialChars) {
        return this.token = new JGSToken(len, withSpecialChars).getToken();
    }

    public String getToken() {
        return this.token;
    }

    public int getTokenLength() {
        return this.token.length();
    }

    private String createToken(int len) {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < len; i++) {
            int choice = getDigit(4);

            switch (choice) {
                case 0: {
                    sb.append(getDigit(10));
                    break;
                }
                case 1: {
                    sb.append(getCharUp());
                    break;
                }
                case 2: {
                    sb.append(getCharLow());
                    break;
                }
                case 3: {
                    sb.append(getSymbol());
                    break;
                }
            }
        }

        return sb.toString();
    }

    private String createTokenDL(int len) {
        StringBuilder sb = new StringBuilder();

        while (sb.length() < len) {
            int choice = getDigit(3);

            switch (choice) {
                case 0:
                    sb.append(getDigit(10));
                    break;
                case 1:
                    sb.append(getCharUp());
                    break;
                case 2:
                    sb.append(getCharLow());
                    break;
            }
        }

        return sb.toString();
    }

    private char getCharUp() {
        int g = getDigit(LETTER_U.length);
        return LETTER_U[g];
    }

    private char getCharLow() {
        int g = getDigit(LETTER_L.length);
        return LETTER_L[g];
    }

    private char getSymbol() {
        int g = getDigit(SYMBOL.length);
        return SYMBOL[g];
    }

    private int getDigit(int len) {
        Random rand = new Random();
        rand.setSeed(newRandSeed());
        return rand.nextInt(len);
    }

    private long newRandSeed() {
        return ThreadLocalRandom.current().nextLong();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        JGSToken token1 = (JGSToken) o;
        return Objects.equals(token, token1.token);
    }

    @Override
    public int hashCode() {
        return Objects.hash(token);
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + '{' +
                "token='" + this.token + '\'' +
                ", len=" + this.len +
                ", withSpecialChars=" + this.withSpecialChars +
                '}';
    }

}
