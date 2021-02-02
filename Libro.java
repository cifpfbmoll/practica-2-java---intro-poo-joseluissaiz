import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Libro {

	private String ISBN;
	private String  ISBNRegex = "^(?:ISBN(?:-10)?:? )?(?=[0-9X]{10}$|(?=(?:[0-9]+[- ]){3})[- 0-9X]{13}$)[0-9]{1,5}[- ]?[0-9]+"
			+ "[- ]?[0-9]+[- ]?[0-9X]$";
	Pattern pattern = Pattern.compile(ISBNRegex);
	private String title;
	private String author;
	private String publisher;
	private static List<String[]> allBooksAvailability = new ArrayList<String[]>(); //0 = title, 1 = Total books, 2 = total available
	private static List<Libro> allBooks = new ArrayList<Libro>();

	//Constructor methods
	
	public Libro() {}
	
	public Libro(final Libro Book) {}

	public Libro(
			String ISBN, 
			String title, 
			String author, 
			String publisher, 
			int copyMaxCount, 
			int copyAvailableCount
			){
		this.ISBN = ISBN;
		this.title = title;
		this.author = author;
		this.publisher = publisher;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}
	
	
	//Book functions
	
	public static void addBook(Scanner reader) {
		Libro emptyBook = new Libro();
		System.out.println("Add a new book");
		System.out.println("_____________________________________");
		emptyBook.setISBN(reader);
		emptyBook.setTtile(reader);
		emptyBook.setAuthor(reader);
		emptyBook.setPublisher(reader);
		boolean isBookInList = false;
		for(int i = 0; i < allBooksAvailability.size(); i++) {
			if(allBooksAvailability.get(i)[0].toLowerCase().contentEquals(emptyBook.getTitle().toLowerCase())){
				int bookCount = Integer.parseInt(allBooksAvailability.get(i)[1]);
				bookCount ++;
				allBooksAvailability.get(i)[1] = Integer.toString(bookCount);
				int bookAvailableCount = Integer.parseInt(allBooksAvailability.get(i)[2]);
				bookAvailableCount ++;
				allBooksAvailability.get(i)[2] = Integer.toString(bookAvailableCount);
				isBookInList = true;
				break;
			}
		}
		if(!isBookInList) {
			String[] bookNameSheet = new String[] {emptyBook.getTitle(), "1", "1"};
			allBooksAvailability.add(bookNameSheet);
		}
		getAllBooks().add(emptyBook);
	}
	
	public static void deleteBook(Scanner reader) {
		int bookPosition = -1;
		System.out.println("Delete book");
		System.out.println("_____________________________________");
		bookPosition = Libro.searchBookByISBN(reader);
		if(bookPosition != -1) {
			String bookTitle = Libro.getAllBooks().get(bookPosition).getTitle();
			for (int i = 0; i < getAllBooksAvailability().size(); i++) {
				if(getAllBooksAvailability().get(i)[0].toLowerCase().contentEquals(bookTitle.toLowerCase())) {
					if(getAllBooksAvailability().get(i)[2].contentEquals("1")) {
						getAllBooksAvailability().remove(i);
					} else {
						int booksAvailable = Integer.parseInt(getAllBooksAvailability().get(i)[2]) - 1;
						getAllBooksAvailability().get(i)[2] = Integer.toString(booksAvailable);
					}
				}
			}
			System.out.println("You have deleted the book named " + bookTitle);
			Libro.getAllBooks().remove(bookPosition);
		} else {
			System.out.println("No books are found by the ISBN provided");
		}

	}
	
	public static int searchBookByISBN(Scanner reader) {
		int bookPosition = -1;
		System.out.println("Plase enter the book ISBN");
		String ISBN = reader.nextLine();
		for (int i = 0; i < Libro.getAllBooks().size(); i++) {
			if(Libro.getAllBooks().get(i).getISBN().contentEquals(ISBN)) {
				bookPosition = i;
				break;
			}
		}
		return bookPosition;
	}
	
	public static void searchBookByTitle(Scanner reader) {
		List<String[]> booksInfo = new ArrayList<String[]>();
		System.out.println("Search book by name");
		System.out.println("_____________________________________");
		System.out.println("Enter a book name: ");
		String searchTitle = reader.nextLine();
		for(int i = 0; i < Libro.getAllBooks().size(); i++) {
			if(Libro.getAllBooks().get(i).getTitle().toLowerCase().contains(searchTitle.toLowerCase())) {
				String[] bookInfoString = new String[] {
						Libro.getAllBooks().get(i).getTitle(),
						Libro.getAllBooks().get(i).getISBN(),
						Libro.getAllBooks().get(i).getPublisher(),
						Libro.getAllBooks().get(i).getAuthor()
				};
				booksInfo.add(bookInfoString);
			}
		}
		if(!booksInfo.isEmpty()) {
			for(int i = 0; i < booksInfo.size(); i++) {
				System.out.println("Title: " + booksInfo.get(i)[0]);
				System.out.println("ISBN: " + booksInfo.get(i)[1]);
				System.out.println("Publisher: " + booksInfo.get(i)[2]);
				System.out.println("Author: " + booksInfo.get(i)[3]);
				System.out.println("_______________________________");
			}
			System.out.println("Total results: " + Integer.toString(booksInfo.size()));
		} else {
			System.out.println("No results available for the search: " + searchTitle);
		}
	}
	
	
	//--DTO after this line--
	//Setters for the instance variables
	
	public void setISBN(Scanner reader) {
		boolean validISBN = true;
		String ISBN = "";
	    Matcher matcher = pattern.matcher(ISBN);
	    while (!matcher.matches() || !validISBN){
			System.out.println("Enter the book ISBN: ");
			ISBN = reader.nextLine();
		    matcher = pattern.matcher(ISBN);
		    if(!matcher.matches()) {
		    	System.out.println("No valid ISBN format, please enter a format like n-nnn-nnnnn-n");
		    } else {
			    for (int i = 0; i < Libro.getAllBooks().size(); i++ ) {
			    	if (Libro.getAllBooks().get(i).getISBN().contentEquals(ISBN)) {
			    		System.out.println("This ISBN is already in use");
			    		validISBN = false;
			    		break;
			    	}
			    }
		    }
	    }
		this.ISBN = ISBN;
	}

	public void setTtile(Scanner reader) {
		
		String title = "";
		while (title == "" || title.length() < 4 || title.length() > 30) {
			System.out.println("Enter the book title: ");
			title = reader.nextLine();
			if (title == "" || title.length() < 4 || title.length() > 30) {
				System.out.println("Are you sure that this is a good title?");
			}
		}
		this.title = title;
	}
	
	public void setAuthor(Scanner reader) {
		String author = "";
		while (author == "" || author.length() < 4 || author.length() > 30) {
			System.out.println("Enter the book author: ");
			author = reader.nextLine();
			if (author == "" || author.length() < 4 || author.length() > 30) {
				System.out.println("Are you sure that this is a good author name?");
			}
		}
		this.author = author;
	}
	
	public void setPublisher(Scanner reader) {
		String publisher = "";
		while (publisher == "" || publisher.length() < 4 || publisher.length() > 30) {
			System.out.println("Enter the book publisher: ");
			publisher = reader.nextLine();
			if (publisher == "" || publisher.length() < 4 || publisher.length() > 30) {
				System.out.println("Are you sure that this is a good publisher name?");
			}
		}
		this.publisher = publisher;
	}
	
	
	//Getters for all the variables

	public String getISBN() {
		return this.ISBN;
	}
	
	public String getTitle() {
		return this.title;
	}
	
	public String getAuthor() {
		return this.author;
	}
	
	public String getPublisher() {
		return this.publisher;
	}
	
	public static List<Libro> getAllBooks(){
		return Libro.allBooks;
		
	}
	
	public static List<String[]> getAllBooksAvailability(){
		return Libro.allBooksAvailability;
	}
}
