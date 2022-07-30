package com;
import java.util.List;
import java.util.Scanner;

public class Main {
	
		public static void displayMenu() {
			String menu = " Virtual Key Repository \n"	
					+ " Developed by Prakash Murugesan \n"
					+ "\n Select any option number from below and press Enter \n"
					+ "1) Display menu for File operations\n"
					+ "2) View all files inside \"files\" folder\n"
					+ "3) Exit program\n";
			System.out.println(menu);

		}

		public static void displayFileMenuOptions() {
			String fileMenu = "\n Select any option number from below and press Enter \n"
					+ "1) Search for a file from \"files\" folder\n" + "2) Delete a file from \"files\" folder\n"
					+ "3) Add a file to \"files\" folder\n" + "4) Show Previous Menu\n" + "5) Exit program\n";

			System.out.println(fileMenu);
		}
	public static void handleWelcomeScreenInput() {
		boolean running = true;
		Scanner sc = new Scanner(System.in);
		do {
			try {
				Main.displayMenu();
				int input = sc.nextInt();

				switch (input) {
				case 1:
					Main.handleFileMenuOptions();
				break;
				case 2:
					FileOperations.displayAllFiles("files");
				break;
				case 3:
					System.out.println("Program exited successfully.");
					running = false;
					sc.close();
					System.exit(0);
					break;
				default:
					System.out.println("Please select a valid option from above.");
				}
			} catch (Exception e) {
				System.out.println(e.getClass().getName());
				handleWelcomeScreenInput();
			} 
		} while (running == true);
	}
	
	public static void handleFileMenuOptions() {
		boolean running = true;
		Scanner sc = new Scanner(System.in);
		do {
			try {
				Main.displayFileMenuOptions();
				FileOperations.createMainFolder();
				
				int input = sc.nextInt();
				switch (input) {
				case 1:
					System.out.println("Enter the name of the file to be searched from \"files\" folder");
					String fileName = sc.next();
					
					FileOperations.createMainFolder();
					FileOperations.displayFileLocations(fileName, "files");
					break;
					
				case 2:
					System.out.println("Enter the name of the file to be deleted from \"files\" folder");
					String fileToDelete = sc.next();
					
					FileOperations.createMainFolder();
					List<String> filesToDelete = FileOperations.displayFileLocations(fileToDelete, "files");
					
					String deletionPrompt = "\nSelect index of which file to delete?"
							+ "\n(Enter 0 if you want to delete all elements)";
					System.out.println(deletionPrompt);
				
					int idx = sc.nextInt();
					
					if (idx != 0) {
						FileOperations.deleteFileRecursively(filesToDelete.get(idx - 1));
					} else {
					for (String path : filesToDelete) {
							FileOperations.deleteFileRecursively(path);
						}
					}
					

					break;
				case 3:
					System.out.println("Enter the name of the file to be added to the \"files\" folder");
					String fileToAdd = sc.next();
					
					FileOperations.createFile(fileToAdd, sc);
					break;
					
				case 4:
					// Go to Previous menu
					return;
				case 5:
					// Exit
					System.out.println("Program exited successfully.");
					running = false;
					sc.close();
					System.exit(0);
				default:
					System.out.println("Please select a valid option from above.");
				}
			} catch (Exception e) {
				System.out.println(e.getClass().getName());
				handleFileMenuOptions();
			}
		} while (running == true);
	}
public static void main(String[] args) {
		
		
		FileOperations.createMainFolder();
		
	    Main.handleWelcomeScreenInput();
	}
}
