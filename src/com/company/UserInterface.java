package com.company;

import java.util.List;
import java.util.Scanner;

public class UserInterface {
    public static void main(String[] args) {
        Functions bf = new Functions("jdbc:mysql://localhost:3306/library?user=root&serverTimezone=UTC");

        Scanner scanner = new Scanner(System.in);
        String date = null;
        String answer = null;
        String name = null;
        String mail = null;
        String address = null;
        boolean flag = true;
        int bookId = 0;
        int id = 0;
        int cnt = 0;

        do {
            System.out.println();
            System.out.println("----Kérem válasszon az álabbi menüpontok közüll----" +
                    "\n( 1 ) - Felhasználók listázása a náluk lévő könyvekkel" +
                    "\n( 2 ) - Egy adott felhasználónál lévő könyvek" +
                    "\n( 3 ) - Könyv ki kölcsonzése " +
                    "\n( 4 ) - Könyv leadása" +
                    "\n( 5 ) - könyvek keresése (szerző vagy cím alapján)" +
                    "\n( 6 ) - Melyik felhasználó hány könyvet kölcsönzött ki" +
                    "\n( 7 ) - Új felhasználó hozzáadása" +
                    "\n( 8 ) - Új könyv hozzáadasa" +
                    "\n( 9 ) - Könyv eltávolítása" +
                    "\n( 10 ) - Felhasználó eltávolítása" +
                    "\n( 0 ) kilépés");

            int menu = scanner.nextInt();

            switch (menu) {
                case 1 -> System.out.println(bf.getUsersWithBorrowedBooks());
                case 2, 6 -> {
                    System.out.println(bf.userList());
                    System.out.print("\nVálasszon egy User Id-t: ");
                    id = scanner.nextInt();
                }
                case 3 -> {
                    System.out.println(bf.userList());
                    System.out.print("Válasszon User ID-t az alábbi listából: ");
                    id = scanner.nextInt();
                    System.out.println(bf.bookList());
                    System.out.print("Válassza ki a kölcsönözni kívánt könyv id-t: ");
                    bookId = scanner.nextInt();
                    System.out.print("Adja meg a kölcsönzés napját: ");
                    date = scanner.next();
                }
                case 4 -> {
                    System.out.println(bf.getUsersWithBorrowedBooks());
                    System.out.print("Válassza ki a visszahozott könyv Borrow ID-t: ");
                    bookId = scanner.nextInt();
                    System.out.print("Adja meg a dátumot: ");
                    date = scanner.next();
                }
                case 5 -> {
                    System.out.println("Cím vagy szerző alapján keressen?");
                    answer = scanner.next();
                    if (answer.equals("cím")) {
                        menu = 5;
                        System.out.println("A keresendő könyv címe: ");
                    } else {
                        System.out.println("A keresendő könyv írója: ");
                        menu = 13;
                    }
                    answer = scanner.next();
                }
                case 7 -> {
                    scanner.nextLine();
                    System.out.println("Név: ");
                    name = scanner.nextLine();
                    System.out.println("E-mail cím: ");
                    mail = scanner.nextLine();
                    System.out.println("Cím: ");
                    address = scanner.nextLine();
                }
                case 8 -> {
                    scanner.nextLine();
                    System.out.println("Könyv címe: ");
                    address = scanner.nextLine();
                    System.out.println("Könyv írója: ");
                    name = scanner.nextLine();
                }
                case 9 -> {
                    System.out.println(bf.bookList());
                    System.out.println();
                    System.out.print("Válasszon egy könv ID-t az alábbi listából: ");
                    bookId = scanner.nextInt();
                }
                case 10 -> {
                    System.out.println(bf.userList());
                    System.out.println();
                    System.out.print("Válasszon egy User ID-t az alábbi listából: ");
                    id = scanner.nextInt();
                }
                case 0 -> flag = false;
            }


            switch (menu) {
                case 2 -> {
                    if (bf.getBorrowedBooksOf(id).isEmpty()) {
                        System.out.println("Ennél a felhasználóná nem található könyv");
                    } else {
                        System.out.println(bf.getBorrowedBooksOf(id));
                    }
                }
                case 3 -> bf.borrowBook(id, bookId, date);
                case 4 -> bf.returnBook(id, date);
                case 13 -> {
                    List<Book> list = bf.searchBookByAuthor(answer);
                    for (Book book : list) {
                        System.out.println(book);
                    }
                }
                case 5 -> {
                    List<Book> list = bf.searchBookByTitle(answer);
                    for (Book book : list) {
                        System.out.println(book);
                    }
                }
                case 6 -> {
                    if (bf.getBorrowedBooksOf(id).isEmpty()) {
                        System.out.println("Ennél a felhasználóná nem található könyv");
                    } else {
                        for (Book book : bf.getBorrowedBooksOf(id)){
                            cnt++;
                        }
                    }
                    System.out.println(cnt+"db könyv van a kiválaszott usernél");
                }
                case 7 -> {
                    bf.addUser(name, mail, address);
                    System.out.println("új felhasználó hozzáadva");
                }
                case 8 -> {
                    bf.addBook(address, name);
                    System.out.println(" könyv sikeresen hozzáadva");
                }
                case 9 -> {
                    bf.removeBookID(bookId);
                    System.out.println("Könyv eltávolítva!");
                }
                case 10 -> {
                    bf.removeUser(id);
                    System.out.println("Felhasználó sikeresen eltávolítva!");
                }
            }
        } while (flag);
    }
}