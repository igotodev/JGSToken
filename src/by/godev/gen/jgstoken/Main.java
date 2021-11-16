package by.godev.gen.jgstoken;

public class Main {
    public static void main(String[] args) {
        JGSToken token = new JGSToken(16, true);
        System.out.println(token.getToken());
        System.out.println("token length: " + token.getTokenLength());
        System.out.println(token.createNewToken(20, false));
        System.out.println("token length: " + token.getTokenLength());
    }
}
