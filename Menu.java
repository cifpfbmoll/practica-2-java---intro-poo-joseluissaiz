import java.util.Scanner;

public class Menu {
	public static Scanner reader = new Scanner(System.in);
	private static Biblioteca Library = new Biblioteca();
	
	//Interface menus
	
	public static void main(String[] args) {
		Library.setLibraryName(reader);
		showMainMenu();
	}

	public static void showMainMenu() {
		String choice = "";
		while (choice != "9") {
			clearScreen();
			System.out.println("Library manager v1.0");
			System.out.println("______________________________");
			System.out.println("Select one option please");
			System.out.println("");
			System.out.println("1- Library manager");
			System.out.println("2- User manager");
			System.out.println("3- Book manager");
			System.out.println("9- Exit");
			System.out.println("______________________________");
			System.out.println("Choice: ");
			choice = reader.nextLine();
			switch(choice) {
				case "1":
					showLibraryMenu();
					break;
				case "2":
					showUserMenu();
					break;
				case "3":
					showBookMenu();
					break;
				case "9":
					System.exit(0);
			}
		}
	}
	
	public static void showLibraryMenu() {
		String choice = "";
		while(choice != "9") {
			clearScreen();
			System.out.println("Library manager v1.0");
			System.out.println("______________________________");
			System.out.println("Select one option please");
			System.out.println("");
			System.out.println("1- List all books");
			System.out.println("2- List all available books");
			System.out.println("3- List all staff");
			System.out.println("9- Back");
			System.out.println("______________________________");
			System.out.println("Choice: ");
			choice = Menu.reader.nextLine();
			switch(choice) {
				case "1":
					Library.showAllBooks();
					reader.nextLine();
					break;
				case "2":
					Library.showAvailableBooks();
					reader.nextLine();
					break;
				case "3":
					Library.showStaffList();
					reader.nextLine();
					break;
				case "9":
					showMainMenu();
					break;
			}
		}
	}
	
	public static void showUserMenu() {
		String choice = "";
		while(choice != "9") {
			clearScreen();
			System.out.println("Library manager v1.0");
			System.out.println("______________________________");
			System.out.println("Select one option please");
			System.out.println("");
			System.out.println("1- Create new user");
			System.out.println("2- Delete user");
			System.out.println("9- Back");
			System.out.println("______________________________");
			System.out.println("Choice: ");
			choice = Menu.reader.nextLine();
			switch(choice) {
				case "1":
					Persona.addUser(reader);
					Library.setLibraryStaffList(Persona.getStaffList());
					reader.nextLine();
					break;
				case "2":
					Persona.deleteUser(reader);
					Library.setLibraryStaffList(Persona.getStaffList());
					reader.nextLine();
				case "9":
					showMainMenu();
					break;
			}
		}
	}
	
	public static void showBookMenu() {
		String choice = "";
		while(choice != "9") {
			clearScreen();
			System.out.println("Library manager v1.0");
			System.out.println("______________________________");
			System.out.println("Select one option please");
			System.out.println("");
			System.out.println("1- Add new book");
			System.out.println("2- Delete book");
			System.out.println("3- Search book by title");
			System.out.println("9- Back");
			System.out.println("______________________________");
			System.out.println("Choice: ");
			choice = Menu.reader.nextLine();
			switch(choice) {
				case "1":
					Libro.addBook(reader);
					Library.setLibraryBookList(Libro.getAllBooks());
					reader.nextLine();
					break;
				case "2":
					Libro.deleteBook(reader);
					Library.setLibraryBookList(Libro.getAllBooks());
					reader.nextLine();
					break;
				case "3":
					Libro.searchBookByTitle(reader);
					reader.nextLine();
					break;
				case "9":
					showMainMenu();
					break;
			}
		}
	}
	
	
	//functionality
	
	public static void clearScreen() {  
		for (int i = 0; i < 50; ++i) System.out.println(); 
	}  
	
	
}
