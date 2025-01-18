# RSA Algorithm

An implementation of the RSA encryption and decryption algorithm in Java. The program allows users to encrypt plaintext messages and decrypt encrypted messages using the RSA cryptographic technique.

## Features

- **Key Generation**: Generates large prime numbers and calculates public and private keys.
- **Encryption**: Converts plaintext messages into encrypted text using a public key.
- **Decryption**: Deciphers encrypted text back into the original message using a private key.

## How It Works

1. Generate two large prime numbers (`p` and `q`).
2. Compute:
   - Modulus: `n = p * q`
   - Totient: `φ(n) = (p - 1) * (q - 1)`
3. Select a public key `e` such that `1 < e < φ(n)` and `gcd(e, φ(n)) = 1`.
4. Calculate the private key `d` such that `(e * d) % φ(n) = 1`.
5. Encrypt each character of the plaintext message using:
   
   C = (M^e) % n
   
6. Decrypt each character of the ciphertext using:
   
   M = (C^d) % n
   

## Prerequisites

- Java 8 or later

## Usage

1. Clone the repository:
   ```bash
   git clone https://github.com/VEDITHREDDY26/RSA-Algorithm.git
   cd RSA-Algorithm
   ```

2. Compile the Java program:
   ```bash
   javac RSA.java
   ```

3. Run the program:
   ```bash
   java RSA
   ```

4. Follow the on-screen instructions to encrypt or decrypt messages.

## Example

- **Input**: `HELLO`
- **Encrypted Message**: `492412 85378 85378 678972 679605`
- **Decrypted Message**: `HELLO`

## Repository Structure

- `RSA.java`: The Java implementation of the RSA algorithm.
- `README.md`: This documentation file.



## Author

Developed by [Vedith Reddy](https://github.com/VEDITHREDDY26).
