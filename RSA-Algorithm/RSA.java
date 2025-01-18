import java.io.IOException;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Scanner;


public class RSA {
    // Random number gcd generator
    private final static SecureRandom random = new SecureRandom();

    private BigInteger prime1; // First prime number
    private BigInteger prime2; // Second prime number
    private BigInteger modulus; // Modulus (prime1 * prime2)
    private BigInteger publicKey; // Public key (gcd(publicKey, phi) = 1)
    private BigInteger privateKey; // Private key (publicKey * privateKey = 1 mod phi)

    /**
     * Main method to encrypt and decrypt messages from users.
     * 
     * @exception IOException On input error.
     * @see IOException
     */
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);

        // Encrypt the message and print the result
        System.out.print("Enter a message for encryption: ");
        String plainText = scanner.nextLine();
        System.out.println("Encrypted Message: \n" + encrypt(plainText));

        System.out.println();

        // Decrypt the message and print the result
        System.out.print("Enter a message for decryption: ");
        String encryptedText = scanner.nextLine();
        System.out.println();

        System.out.println("Decrypted Message: \n" + decrypt(encryptedText));

        scanner.close();
    }

    /**
     * Constructor to set up RSA keys based on bit length.
     * 
     * @param bitLength Bit length for generating prime numbers and keys.
     */
    RSA(int bitLength) {
        prime1 = BigInteger.probablePrime(bitLength, random);
        prime2 = BigInteger.probablePrime(bitLength, random);
        BigInteger phi = (prime1.subtract(BigInteger.ONE)).multiply(prime2.subtract(BigInteger.ONE));

        modulus = prime1.multiply(prime2);
        publicKey = BigInteger.probablePrime(bitLength / 2, random);

        // Ensure gcd(phi, publicKey) = 1
        while (phi.gcd(publicKey).compareTo(BigInteger.ONE) > 0 && publicKey.compareTo(phi) < 0) {
            publicKey = publicKey.add(BigInteger.ONE);
        }

        privateKey = publicKey.modInverse(phi); // privateKey * publicKey ≡ 1 (mod phi)
    }

    /**
     * Returns a string representation of the RSA keys and components.
     * 
     * @return A string with RSA details.
     */
    public String toString() {
        StringBuilder details = new StringBuilder();
        details.append("Prime 1: " + prime1 + "\n");
        details.append("Prime 2: " + prime2 + "\n");
        details.append("Modulus (n): " + modulus + "\n");
        details.append("Public Key (e): " + publicKey + "\n");
        details.append("Private Key (d): " + privateKey + "\n");
        return details.toString();
    }

    /**
     * Encrypts a plaintext message.
     * 
     * @param plainText The message to encrypt.
     * @return The encrypted message as a space-separated string.
     */
    public static String encrypt(String plainText) {
        StringBuilder encryptedMessage = new StringBuilder();

        // RSA parameters
        BigInteger modulus = new BigInteger("769750914680484372200078422578788743792190453917708306205411");
        BigInteger publicKey = new BigInteger("823738732813999");

        // Validate input
        if (plainText == null || plainText.isEmpty()) {
            System.err.println("Error: The input message cannot be null or empty.");
            return null;
        }

        try {
            // Encrypt each character in the plainText
            for (char character : plainText.toCharArray()) {
                BigInteger charValue = BigInteger.valueOf(character);
                BigInteger encryptedChar = charValue.modPow(publicKey, modulus);
                encryptedMessage.append(encryptedChar).append(" ");
            }
            return encryptedMessage.toString().trim();
        } catch (Exception e) {
            System.err.println("An error occurred during encryption: " + e.getMessage());
            return null;
        }
    }

    /**
     * Decrypts an encrypted message.
     * 
     * @param encryptedText The message to decrypt.
     * @return The decrypted message.
     */
    public static String decrypt(String encryptedText) {
        BigInteger modulus = new BigInteger("769750914680484372200078422578788743792190453917708306205411");
        BigInteger privateKey = new BigInteger("353343052159423642183327550893401946314922128579120163105999");
        StringBuilder decryptedMessage = new StringBuilder();

        // Split the input string into individual encrypted characters
        String[] encryptedParts = encryptedText.trim().split("\\s+");

        for (String part : encryptedParts) {
            try {
                BigInteger encryptedValue = new BigInteger(part);
                BigInteger decryptedValue = encryptedValue.modPow(privateKey, modulus);
                String character = new String(decryptedValue.toByteArray());
                decryptedMessage.append(character);
            } catch (NumberFormatException e) {
                System.err.println("Invalid input encountered: " + part);
                continue; // Skip invalid input
            }
        }
        return decryptedMessage.toString();
    }
}
