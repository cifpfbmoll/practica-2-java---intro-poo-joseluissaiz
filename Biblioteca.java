import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Biblioteca {
	private String libraryName;
	private List<Libro> bookList = new ArrayList<Libro>();
	private List<Persona> staffList = new ArrayList<Persona>();
	
	//Constructor methods

	public Biblioteca(){}
	
	public Biblioteca(String libraryName, List<Libro> bookList, List<Persona> staffList) {
		this.libraryName = libraryName;
		this.bookList = bookList;
		this.staffList = staffList;
	}
	
	public Biblioteca(final Biblioteca Library) {}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}
	
	
	//Library functions
	
	public void showAllBooks() {
		System.out.println("Listing all books in " + this.getlibraryName());
		System.out.println("____________________________________");
		for(int i = 0; i < this.getBookList().size(); i++) {
			System.out.println("Title: " + this.getBookList().get(i).getTitle());
			System.out.println("ISBN: " + this.getBookList().get(i).getISBN());
			System.out.println("Author: " + this.getBookList().get(i).getAuthor());
			System.out.println("Publisher: " + this.getBookList().get(i).getPublisher());
			System.out.println("______________________________");
		}
		System.out.println("Total books :" + this.getBookList().size());
	}

	public void showAvailableBooks() {
		System.out.println("Disponible books");
		System.out.println("____________________________________");
		for (int i = 0; i < Libro.getAllBooksAvailability().size(); i++) {
			System.out.println("Title: " + Libro.getAllBooksAvailability().get(i)[0]);
			System.out.println("Total available: " + Libro.getAllBooksAvailability().get(i)[2]);
		}
	}
	
	public void showStaffList() {
		System.out.println("This is the staff of " + this.getlibraryName());
		System.out.println("_________________________________");
		for(int i = 0; i < this.getStaffList().size(); i++) {
			System.out.println("Name: " + this.getStaffList().get(i).getUserName());
			System.out.println("Surname: " + this.getStaffList().get(i).getUserSurname());
			System.out.println("DNI: " + this.getStaffList().get(i).getUserNIF());
			System.out.println("____________________________");
		}
		System.out.println("Total staff: " + this.getStaffList().size());
	}
	
	
	//--DTO after this line--
	//Setters for the instance variables
	
	public void setLibraryName(Scanner reader) {
		String libraryName = " ";
		while (libraryName.substring(0, 1) == " " || libraryName == "") {
			System.out.println("Set the Library name: ");
			libraryName = reader.nextLine();
			if (libraryName.substring(0, 1) == " " || libraryName == "") {
				System.out.println("Are you sure that this is a good library name?");
			}
		}
		String libraryNameFormatted = libraryName.substring(0, 1).toUpperCase() + libraryName.substring(1);
		this.libraryName = libraryNameFormatted;
	}
	
	public void setLibraryBookList(List<Libro> bookList) {
		this.bookList = bookList;
	}
	
	public void setLibraryStaffList(List<Persona> staffList) {
		this.staffList = staffList;
	}
	
	
	//Getters for all the variables
	
	public String getlibraryName() {
		return this.libraryName;
	}
	
	public List<Libro> getBookList(){
		return this.bookList;
	}
	
	public List<Persona> getStaffList(){
		return this.staffList;
	}
}
