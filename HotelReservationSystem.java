import java.util.*;

public class HotelReservationSystem {
    private ArrayList<Hotel> hotelList;

    public HotelReservationSystem() {
        hotelList = new ArrayList<Hotel>();
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int choice;
        String name;
        String prefix;
        int amount;
        boolean checkDupli;

        System.out.println(" _   _       _       _  ______                               _   _             ");
        System.out.println("| | | |     | |     | | | ___ \\                             | | (_)            ");
        System.out.println("| |_| | ___ | |_ ___| | | |_/ /___  ___  ___ _ ____   ____ _| |_ _  ___  _ __  ");
        System.out.println("|  _  |/ _ \\| __/ _ \\ | |    // _ \\/ __|/ _ \\ '__\\ \\ / / _` | __| |/ _ \\| '_ \\ ");
        System.out.println("| | | | (_) | ||  __/ | | |\\ \\  __/\\__ \\  __/ |   \\ V / (_| | |_| | (_) | | | |");
        System.out.println("\\_| |_/\\___/ \\__\\___|_| \\_| \\_\\___||___/\\___|_|    \\_/ \\__,_|\\__|_|\\___/|_| |_|");

        System.out.println("\n\n\t\t\t\t 1. Create Hotel");
        System.out.println("\n\t\t\t\t  2. View Hotel");
        System.out.println("\n\t\t\t\t 3. Manage Hotel");
        System.out.println("\n\t\t\t       4. Simulate Booking");
        System.out.println("\n\t\t\t\t   5. Exit System");

        System.out.println("--------------------------------------------------------------------------------");
        do{
        System.out.print("\nEnter Your Selection: ");
        choice = sc.nextInt();

        switch(choice){
            case 1:
            do{
            System.out.println("--------------------------------------------------------------------------------");
            System.out.print("\nEnter a name for your Hotel: ");
            name = sc.nextLine();
            } while (true);
            // check if name is already used

            System.out.print("\nEnter a prefix for the rooms in the Hotel: ");
            prefix = sc.nextLine();
            do{
            System.out.print("\nEnter the amount of rooms the Hotel will start with: ");
            amount = sc.nextInt();
            if (amount > 50 || amount < 1) {
                System.out.println("[!][!] Enter a valid Room amount [!][!]");
            }
            } while (amount > 50 || amount < 1);

            break;
            case 2:
            System.out.println("--------------------------------------------------------------------------------");
            // print hotel names
            System.out.print("\nSelect a Hotel you would like to view: ");
            break;
            case 3:
            System.out.println("--------------------------------------------------------------------------------");
            // print hotel names
            System.out.print("\nSelect a Hotel you would like to manage: ");
            break;
            case 4:
            System.out.println("--------------------------------------------------------------------------------");
            // print hotel names
            System.out.print("\nSelect a Hotel you would like to get a reservation in: ");
            break;
            case 5:
            System.out.println("--------------------------------------------------------------------------------");
            System.out.println("Exiting System... Goodbye!");
            break;
            default:
            System.out.println("Invalid Selection. Please Try Again.");
            break;
        }
        } while(choice != 5);

        sc.close();
        return;

        
    }
}