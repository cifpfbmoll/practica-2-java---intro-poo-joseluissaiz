import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Persona {
	private String userName;
	private String userSurname;
	private String userNIF;
	private String userPassword;
	private static List<Persona> staffList = new ArrayList<Persona>();

	//Constructor methods

	public Persona(String userName, String userSurname, String userNIF, String userPassword) {
		this.userName = userName;
		this.userSurname = userSurname;
		this.userNIF = userNIF;
		this.userPassword = userPassword;
	}
	
	public Persona(final Persona User) {}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}

	
	//User functions
	
	public static void addUser(Scanner reader) {
		Persona newUser = new Persona("","","","");
		System.out.println("Create new user");
		System.out.println("______________________");
		newUser.setUserName(reader);
		newUser.setUserSurname(reader);
		newUser.setUserNIF(reader);
		newUser.setUserPassword(reader);
		staffList.add(newUser);
	}

	public static void deleteUser(Scanner reader) {
		boolean userExists = false;
		System.out.println("Enter the user NIF: ");
		String userNIF = reader.nextLine();
		for(int i = 0; i < Persona.getStaffList().size(); i++) {
			if (getStaffList().get(i).getUserNIF().toLowerCase().contentEquals(userNIF.toLowerCase())) {
				userExists = true;
				System.out.println("You have removed the user of " + getStaffList().get(i).getUserName());
				getStaffList().remove(i);
				break;
			}
		}
		if (!userExists) {
			System.out.println("There are no users with the DNI " + userNIF);
		}
	}
	
	
	//--DTO after this line--
	//Setters for the instance variables
	
	public void setUserName(Scanner reader) {
		System.out.println("Enter name: ");
		String userName = reader.nextLine();
		this.userName = userName;
	}
	
	public void setUserSurname(Scanner reader) {
		System.out.println("Enter surname: ");
		String userSurname = reader.nextLine();
		this.userSurname = userSurname;
	}

	public void setUserNIF(Scanner reader) {
		boolean validNIF = false;
		String userNIF = "";
		while(!validNIF || userNIF == "") {
			validNIF = true;
			System.out.println("Enter NIF: ");
			userNIF = reader.nextLine();
			for(int i = 0; i < getStaffList().size(); i++) {
				if(getStaffList().get(i).getUserNIF().toLowerCase().contentEquals(userNIF.toLowerCase())) {
					validNIF = false;
					System.out.println("It already exists an user with this NIF");
					break;
				}
			}
		}
		this.userNIF = userNIF;
	}
	
	public void setUserPassword(Scanner reader) {
		String userPassword = "";
		while(userPassword.length() < 8) {
			System.out.println("Enter a new password: ");
			userPassword = reader.nextLine();
			if (userPassword.length() < 8) {
				System.out.println("Your password must have 8 characters");
			}
		}
		this.userPassword = userPassword;
	}
	
	
	//Getters for all the variables
	
	public String getUserName() {
		return this.userName;
	}
	
	public String getUserSurname() {
		return this.userSurname;
	}
	
	public String getUserNIF() {
		return this.userNIF;
	}
	
	public String getUserPassword() {
		return this.userPassword;
	}
	
	public static List<Persona> getStaffList(){
		return staffList;
	}
}
