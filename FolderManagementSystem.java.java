import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// Class representing a folder in the structure
class FolderNode {
    String name; // Name of the folder
    List<FolderNode> subfolders; // List of subfolders

    public FolderNode(String name) {
        this.name = name;
        this.subfolders = new ArrayList<>();
    }

    public void addSubfolder(FolderNode subfolder) {
        subfolders.add(subfolder);
    }

    public void printStructure(String prefix) {
        System.out.println(prefix + name);
        for (FolderNode subfolder : subfolders) {
            subfolder.printStructure(prefix + "  ");
        }
    }
}

// Main application class
public class FolderManagementSystem {
    private static FolderNode root; // Root folder of the structure

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== Folder Management System ===");
        System.out.print("Enter the name of the root folder: ");
        String rootName = scanner.nextLine();
        root = new FolderNode(rootName); // Create the root folder

        boolean running = true;

        while (running) {
            System.out.println("\nMenu:");
            System.out.println("1. Add a subfolder");
            System.out.println("2. Display folder structure");
            System.out.println("3. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            switch (choice) {
                case 1 -> addSubfolder(scanner);
                case 2 -> {
                    System.out.println("\nCurrent Folder Structure:");
                    root.printStructure("");
                }
                case 3 -> {
                    running = false;
                    System.out.println("Exiting. Goodbye!");
                }
                default -> System.out.println("Invalid option. Please try again.");
            }
        }

        scanner.close();
    }

    private static void addSubfolder(Scanner scanner) {
        System.out.print("Enter the name of the parent folder: ");
        String parentName = scanner.nextLine();
        FolderNode parentNode = findFolder(root, parentName);

        if (parentNode != null) {
            System.out.print("Enter the name of the subfolder: ");
            String subfolderName = scanner.nextLine();
            parentNode.addSubfolder(new FolderNode(subfolderName));
            System.out.println("Subfolder added successfully.");
        } else {
            System.out.println("Parent folder not found. Please try again.");
        }
    }

    private static FolderNode findFolder(FolderNode node, String name) {
        if (node.name.equalsIgnoreCase(name)) {
            return node;
        }
        for (FolderNode subfolder : node.subfolders) {
            FolderNode foundFolder = findFolder(subfolder, name);
            if (foundFolder != null) {
                return foundFolder;
            }
        }
        return null;
    }
}
