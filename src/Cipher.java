package ca.yorku.eecs.kryptonote;

public class Cipher
{
    private String key;
    public static final String ALPHABET = " ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    private String makePad(String note)
    {
        String pad = this.key;
        for (; pad.length() < note.length(); )
        {
            pad += this.key;
        }
        return pad;
    }

    //constructor: Construct an instance having the given key
    public Cipher(String k)
    {
        //should simply set the key attribute
        this.key = k;
    }

    //methods
    public String encrypt(String note)
    {
        //encrypt given string and return ciphertext
        String pad = makePad(note);
        String result = "";

        for(int i = 0; i < note.length(); i++)
        {
            String c = note.substring(i, i + 1);
            int position = ALPHABET.indexOf(c);
            int shift = Integer.parseInt(pad.substring(i, i + 1));
            int newPosition = (position + shift) % ALPHABET.length();
            result = result + ALPHABET.substring(newPosition, newPosition + 1);
        }
        return result;
    }

    public String decrypt(String note)
    {
        //decrypt given string and return plaintext
        String pad = makePad(note);
        String result = "";

        for(int i = 0; i < note.length(); i++)
        {
            String c = note.substring(i, i + 1);
            int position = ALPHABET.indexOf(c);
            int shift = Integer.parseInt(pad.substring(i, i + 1));
            int newPosition = (position - shift) % ALPHABET.length(); //subtracting rather than adding
            result = result + ALPHABET.substring(newPosition, newPosition + 1);
        }
        return result;
    }

    //testing
    public static void main(String[] args)
    {
        String k = "1234";
        Cipher c = new Cipher(k);
        String result = c.decrypt("UJLWAKVDBBWITV");
        System.out.println(result);
    }

}
