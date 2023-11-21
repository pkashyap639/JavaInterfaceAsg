
public class SMSMessage implements IEncryptable{

    private String from;
    private String to;
    private String message;

    public SMSMessage(String from, String to, String message) {
        this.from = from;
        this.to = to;
        this.message = message;
    }

    public void sendMessage() {
        //@NOTE: Do not change this function!
        System.out.println("The following message has been sent by " + this.encrypt() + " to " + this.to);
        System.out.println("\t" + this.message);
    }


    @Override
    public String encrypt() {
        String sender = from.replaceAll("-","");
        StringBuilder codedNumber = new StringBuilder();
        for(char c: sender.toCharArray()){
           String morseCode =  MorseCodeUtilites.getMorseCode(c);
           codedNumber.append(morseCode);
        }
        return codedNumber.toString();
    }

    @Override
    public Boolean isOriginal(String Text) {
        String[] PhoneNumber = new String[10];
        String number = "";
        int index = 0;
        for(int i=0;i<Text.length();i+=5){
            PhoneNumber[index] = (Text.substring(i,i+5));
            index+=1;
        }
        for(int j=0;j<PhoneNumber.length;j++){
            if(j==3 || j==6){
                number = number.concat("-"+String.valueOf(MorseCodeUtilites.getNumber(PhoneNumber[j])));
            }
            else{
                number = number.concat(String.valueOf(MorseCodeUtilites.getNumber(PhoneNumber[j])));
            }
        }
        return number.equals(from);
    }
}
