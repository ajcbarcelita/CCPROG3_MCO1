import java.util.ArrayList;
import java.util.Scanner;

public class HotelReservationSystem {
    private ArrayList<Hotel> hotelList;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int choice;

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
            break;
            case 2:
            break;
            case 3:
            break;
            case 4:
            break;
            case 5:
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