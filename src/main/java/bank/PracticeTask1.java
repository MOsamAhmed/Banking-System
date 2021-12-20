import java.util.Random;

public class PracticeTask1 {
    
    public static void main(String[] args) {
        
        Random random = new Random();
        
        String alphabets = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String numbers = "0123456789";
//        char c = (char) (random.nextInt(26) + 'k');
        
        String letterPart = "";
        String numberPart = "";
        
        for(int i=0; i<3; i++) {
            letterPart += alphabets.charAt(random.nextInt(alphabets.length()));
            numberPart += numbers.charAt(random.nextInt(numbers.length()));
        }
        
        String plateNumber = letterPart+'-'+numberPart;
        
        System.out.println(plateNumber);
        
    }
    
}
