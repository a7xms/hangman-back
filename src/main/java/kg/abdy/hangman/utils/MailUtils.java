package kg.abdy.hangman.utils;


import kg.abdy.hangman.models.VerificationToken;

public class MailUtils {

    public static String constructConfirmMailMessage(VerificationToken token) {
        String message =
                """
                Hello, %s %s
                                        
                You registered an account on Hangman, 
                before being able to use your account you need to verify that this is your email address 
                by clicking here: http://localhost:3000/confirm/email/%s
                                              
                Kind Regards, Hangman.
                """;

        return String.format(message,
                token.getUser().getFirstName(),
                token.getUser().getLastName(),
                token.getToken());

    }
}
