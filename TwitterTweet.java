public class TwitterTweet implements IEncryptable {
    private String username;
    private int numCharacters;
    private String tweet;

    public TwitterTweet(String username, String tweet) {
        this.username = username;
        this.tweet = tweet;
        if (this.hasReachedMaximum() == true) {
            this.numCharacters = -1;
        }
        else {
            this.numCharacters = this.tweet.length();
        }

    }

    public boolean hasReachedMaximum() {
        if (this.tweet.length() > 140) {
            return true;
        }
        else {
            return false;
        }
    }

    public String getTweet() {
        return this.tweet;
    }


    @Override
    public String encrypt() {
        String SecretKey = "";
        String[] keys = tweet.split(" ");
        for(String k: keys){
            SecretKey = SecretKey.concat(arrangement(k)+" ");
        }
        return SecretKey;
    }

    public String arrangement(String key){
        String CharOne;
        int keyLen = key.length();
        CharOne = key.charAt(0)+"eq";
        return key.substring(1,keyLen).concat(CharOne);
    }

    @Override
    public Boolean isOriginal(String Text) {
        String decrypt="";
        String[] keys = Text.split(" ");
        for(String k:keys){
            decrypt = decrypt.concat(decryptKey(k)+" ");
        }
        return
                tweet.equals(decrypt.trim());
    }

    public String decryptKey(String key){
        int keyLen = key.length();
        String remainingWord = key.substring(0,keyLen-3);
        return String.valueOf(key.charAt(keyLen-3)).concat(remainingWord);
    }
}
