import java.util.*;

public class HotelReservationSystem {
    private ArrayList<Hotel> hotelList;

    public HotelReservationSystem() {
        hotelList = new ArrayList<Hotel>();
    }

    public String getHotelName(int index) {
        return hotelList.get(index).getHotelName();
    }

    public int getHotelAmount() {
        return hotelList.size();
    }

    public String getHotelPrefix(int index) {
        return hotelList.get(index).getHotelPrefix();
    }

    public void displayHotels() {
        for (int i = 0; i < hotelList.size(); i++) {
            System.out.println((i+1)+ " Hotel Name: " + hotelList.get(i).getHotelName());
        }
    }

    public void addHotel(Hotel hotel) {
        hotelList.add(hotel);
    }

    public void highLevelInformation(int index) {
        
    }


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int choice, choice3;
        String choice2;
        String name;
        String prefix;
        int amount;
        boolean checkDuplicate;
        Hotel tempHotel;
        HotelReservationSystem hrs = new HotelReservationSystem();

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
            System.out.println("--------------------------------------------------------------------------------");

            do{
            checkDuplicate = false;
            System.out.print("\nEnter a name for your Hotel: ");
            name = sc.nextLine();
            
            for(int i = 0; i < hrs.getHotelAmount(); i++) {
                if(hrs.getHotelName(i).equalsIgnoreCase(name)) {
                    checkDuplicate = true;
                    System.out.println("[!][!] Name is already taken [!][!]");
                }
            }
            } while (checkDuplicate);

            do{
            checkDuplicate = false;
            System.out.print("\nEnter a prefix for the rooms in the Hotel: ");
            prefix = sc.nextLine();
            
            for(int i = 0; i < hrs.getHotelAmount(); i++) {
                if(hrs.getHotelPrefix(i).equalsIgnoreCase(prefix)) {
                    checkDuplicate = true;
                    System.out.println("[!][!] Name is already taken [!][!]");
                }
            }
            } while (checkDuplicate);

            do{
            System.out.print("\nEnter the amount of rooms the Hotel will start with: ");
            amount = sc.nextInt();
            if (amount > 50 || amount < 1) {
                System.out.println("[!][!] Enter a valid room amount [!][!]");
            }
            } while (amount > 50 || amount < 1);

            System.out.print("\nWould you like to change the room's price per night from 1299.00? Y/N: ");
            choice2 = sc.nextLine(); // should we ask if they want to change price cause it says "which is set to a default of 1,299.0."

            tempHotel = new Hotel(name, amount, prefix);
            hrs.addHotel(tempHotel);
            break;


            case 2:
            System.out.println("--------------------------------------------------------------------------------");
            hrs.displayHotels();
            System.out.print("\nSelect a Hotel you would like to view: ");
            choice3 = sc.nextInt();
            


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