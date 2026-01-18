package com.library.library_management;

import java.util.Scanner;

import com.library.dao.BookDAO;
import com.library.dao.MemberDAO;
import com.library.dao.IssueReturnDAO;
import com.library.model.Book;
import com.library.model.Member;

public class App {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        BookDAO bookDAO = new BookDAO();
        MemberDAO memberDAO = new MemberDAO();
        IssueReturnDAO issueDAO = new IssueReturnDAO();

        int choice;

        do {
            System.out.println("\n====== LIBRARY MANAGEMENT SYSTEM ======");
            System.out.println("1. Add Book");
            System.out.println("2. View Books");
            System.out.println("3. Add Member");
            System.out.println("4. View Members");
            System.out.println("5. Issue Book");
            System.out.println("6. Return Book");
            System.out.println("7. Exit");
            System.out.print("Enter your choice: ");

            choice = sc.nextInt();
            sc.nextLine(); // clear buffer

            switch (choice) {

                case 1:
                    Book book = new Book();

                    System.out.print("Enter Book Title: ");
                    book.setTitle(sc.nextLine());

                    System.out.print("Enter Author Name: ");
                    book.setAuthor(sc.nextLine());

                    System.out.print("Enter Publisher: ");
                    book.setPublisher(sc.nextLine());

                    System.out.print("Enter Category ID: ");
                    book.setCategoryId(sc.nextInt());

                    bookDAO.addBook(book);
                    break;

                case 2:
                    bookDAO.viewBooks();
                    break;

                case 3:
                    Member member = new Member();

                    System.out.print("Enter Name: ");
                    member.setName(sc.nextLine());

                    System.out.print("Enter Email: ");
                    member.setEmail(sc.nextLine());

                    System.out.print("Enter Phone: ");
                    member.setPhone(sc.nextLine());

                    System.out.print("Enter Address: ");
                    member.setAddress(sc.nextLine());

                    memberDAO.addMember(member);
                    break;

                case 4:
                    memberDAO.viewMembers();
                    break;

                case 5:
                    System.out.print("Enter Book ID: ");
                    int bookId = sc.nextInt();

                    System.out.print("Enter Member ID: ");
                    int memberId = sc.nextInt();

                    issueDAO.issueBook(bookId, memberId);
                    break;

                case 6:
                    System.out.print("Enter Transaction ID: ");
                    int transactionId = sc.nextInt();

                    issueDAO.returnBook(transactionId);
                    break;

                case 7:
                    System.out.println("Exiting system. Thank you!");
                    break;

                default:
                    System.out.println("Invalid choice. Try again.");
            }

        } while (choice != 7);

        sc.close();
    }
}
