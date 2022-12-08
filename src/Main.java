import java.util.Scanner;
public class Main {
    static char[] alphabet = "abcdefghijklmnopqrstuvwxyz".toCharArray();

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String plainText;
        String cipherText;
        String key;

        System.out.println("Enter Plain Text: ");
        plainText = input.nextLine();
        plainText = plainText.toLowerCase();
        plainText = plainText.replaceAll(" ","");

        System.out.println("Enter Key Text: ");
        key = input.nextLine();
        key = key.toLowerCase();
        key = key.replaceAll(" ", "");

        cipherText = encrypt(plainText, key);
        System.out.println("Encrypted: " + cipherText);

        System.out.println("Decrypted: "+ decrypt(cipherText,key));

    }
    public static  String encrypt(String plainText, String key){
        char[] plainTextArray = plainText.toCharArray();
        StringBuilder cipherText = new StringBuilder();
        StringBuilder fullKey = new StringBuilder();
        for(int i=0; i<plainText.length(); i+=key.length()){
            fullKey.append(key);
        }
        char[] repeatedKey = fullKey.toString().toCharArray();
        char [] keyArray = new char[plainText.length()];
        System.arraycopy(repeatedKey,0,keyArray,0,plainText.length());
        for(int j =0; j<keyArray.length; j++){
            int index1 = findIndex(plainTextArray[j], alphabet);
            int index2 = findIndex(keyArray[j], alphabet);
            int sum = (index1+index2)%26;
            cipherText.append(alphabet[sum]);
        }
        return cipherText.toString();
    }
    public static  String decrypt(String cipherText, String key){
        char[] cipherTextArray = cipherText.toCharArray();
        StringBuilder plainText = new StringBuilder();
        StringBuilder fullKey = new StringBuilder();
        for(int i=0; i<cipherText.length(); i+=key.length()){
            fullKey.append(key);
        }
        char[] repeatedKey = fullKey.toString().toCharArray();
        char [] keyArray = new char[cipherText.length()];
        System.arraycopy(repeatedKey,0,keyArray,0,cipherText.length());
        for(int j =0; j<keyArray.length; j++){
            int index1 = findIndex(cipherTextArray[j], alphabet);
            int index2 = findIndex(keyArray[j], alphabet);
            int sum = (26 + (index1-index2))%26;
            plainText.append(alphabet[sum]);
        }
        return plainText.toString();
    }
    public static int findIndex(char letter, char[] alphabet){
        for(int i =0; i<alphabet.length; i++){
            if(letter == alphabet[i]){
                return i;
            }
        }
        return -1;
    }
}