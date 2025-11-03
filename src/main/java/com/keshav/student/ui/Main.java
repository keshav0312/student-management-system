package com.keshav.student.ui;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("1. Add  2. View  3. Update  4. Delete  5. List  0. Exit");
            int c = Integer.parseInt(sc.nextLine());
            if (c == 0) break;
            System.out.println("You chose: " + c);

        }
        sc.close();
    }
}
