package org.example;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.jasypt.iv.RandomIvGenerator;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;

import java.util.Scanner;

public class JasyptEncryptionTool implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(JasyptEncryptionTool.class, args);
    }

    @Override
    public void run(String... args) {
        StandardPBEStringEncryptor standardPBEStringEncryptor = new StandardPBEStringEncryptor();
        standardPBEStringEncryptor.setPassword("yourpassword");
        standardPBEStringEncryptor.setAlgorithm("PBEWITHHMACSHA512ANDAES_256");
        standardPBEStringEncryptor.setIvGenerator(new RandomIvGenerator());

        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("请输入1进行加密，输入2进行解密，输入0退出：");
            String operation = scanner.nextLine();
            if ("1".equals(operation)) {
                System.out.println("请输入需要加密的内容：");
                String content = scanner.nextLine();
                System.out.println("加密结果：" + encrypt(standardPBEStringEncryptor, content));
            } else if ("2".equals(operation)) {
                System.out.println("请输入需要解密的内容：");
                String content = scanner.nextLine();
                System.out.println("解密结果：" + decrypt(standardPBEStringEncryptor, content));
            } else if ("0".equals(operation)) {
                System.out.println("退出程序");
                break;
            } else {
                System.out.println("无效的操作！");
            }
        }

        scanner.close();
    }

    public static String encrypt(StandardPBEStringEncryptor standardPBEStringEncryptor, String strToEncrypt) {
        try {
            return standardPBEStringEncryptor.encrypt(strToEncrypt);
        } catch (Exception e) {
            System.out.println("Error while encrypting: " + e);
        }
        return null;
    }

    public static String decrypt(StandardPBEStringEncryptor standardPBEStringEncryptor, String strToDecrypt) {
        try {
            return standardPBEStringEncryptor.decrypt(strToDecrypt);
        } catch (Exception e) {
            System.out.println("Error while decrypting: " + e);
        }
        return null;
    }
}
