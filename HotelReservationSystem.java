import java.util.*;

/**
 * HotelReservationSystem is a class that manages an arraylist of hotels and provides methods for creating, viewing, managing hotels and creating reservations.
 */
public class HotelReservationSystem {
    Scanner sc = new Scanner(System.in);
    private ArrayList<Hotel> hotelList;

    /**
     * Constructor for the Hotel Reservation System.
     */
    public HotelReservationSystem() {
        hotelList = new ArrayList<Hotel>();
    }

    /**
     * Returns the Hotel at the given index.
     * 
     * @param index the index of the hotel to return
     * @return Hotel at the given index
     */
    public Hotel getHotel (int index) {
        return hotelList.get(index);
    }

    /**
     * Returns the amount of Hotels present in the Reservation System
     * 
     * @return number of Hotels/size of hotelList arrayList
     */
    public int getHotelAmount() {
        return hotelList.size();
    }

    /**
     * Prints out the names of each Hotel in the Reservation System along with a corresponding index.
     */
    public void displayHotels() {
        for (int i = 0; i < hotelList.size(); i++) {
            System.out.println("\t"+(i+1)+ " Hotel Name: " + getHotel(i).getHotelName());
        }
    }

    /**
     * Adds a new Hotel into the Reservation System.
     * 
     * @param hotel the hotel to add
     */
    public void addHotel(Hotel hotel) {
        hotelList.add(hotel);
    }

    /**
     * 
     */
    public void createHotel() // encapsulated 
    {
        String choice;
        String name;
        String prefix;
        int amount = 0;
        double price = 0;
        boolean checkDuplicate = false;
        Hotel tempHotel;
        System.out.println("--------------------------------------------------------------------------------");

            do{
            checkDuplicate = false;
            System.out.print("Enter a name for your Hotel: ");
            name = sc.nextLine();
            
            for(int i = 0; i < getHotelAmount(); i++) {
                if(getHotel(i).getHotelName().equalsIgnoreCase(name)) {
                    checkDuplicate = true;
                    System.out.println("[!][!] Name is already taken [!][!]");
                }
            }
            } while (checkDuplicate);

            do{
            checkDuplicate = false;
            System.out.print("Enter a prefix for the rooms in the Hotel: ");
            prefix = sc.nextLine();
            
            for(int i = 0; i < getHotelAmount(); i++) {
                if(getHotel(i).getHotelPrefix().equalsIgnoreCase(prefix)) {
                    checkDuplicate = true;
                    System.out.println("[!][!] Prefix is already taken [!][!]");
                }
            }
            } while (checkDuplicate);

            do {
                System.out.print("Enter the amount of rooms the Hotel will start with: ");
                if (sc.hasNextInt()) {
                    amount = sc.nextInt();
                    sc.nextLine(); // consume newline left-over
                } else {
                    System.out.println("[!][!] Invalid input. Please enter a valid number. [!][!]");
                    sc.next(); // discard invalid input
                    continue; // skip to the next iteration
                }
            
                if (amount > 50 || amount < 1) {
                    System.out.println("[!][!] Enter a valid room amount (between 1 and 50) [!][!]");
                }
            } while (amount > 50 || amount < 1);

            do{
                System.out.print("Would you like to change the room's price per night from 1299.00? Yes or No: ");
                choice = sc.nextLine(); // should we ask if they want to change price cause it says "which is set to a default of 1,299.0."
                if (choice.equalsIgnoreCase("yes")) {
                    do{
                    System.out.print("Enter the new price per night of a room: ");
                        if (sc.hasNextDouble()) {
                            price = sc.nextDouble();
                            sc.nextLine();
                        } else {
                            System.out.println("Invalid input. Please enter a valid number.");
                            sc.next(); // discard invalid input
                            continue; // skip to the next iteration
                        }

                    if (price < 100) {
                        System.out.println("[!][!] Enter a valid price (at least 100.00) [!][!]");
                    }
                    } while(price < 100.00);
                    tempHotel = new Hotel(name, amount, prefix, price);
                }
                else {
                    tempHotel = new Hotel(name, amount, prefix);
                }
            } while (!(choice.equalsIgnoreCase("no") || choice.equalsIgnoreCase("yes")));

            addHotel(tempHotel);
    }
    
    public void viewHotel() // viewing  
    {
        int choiceHotel = 0, choiceOption = 0;
        System.out.println("--------------------------------------------------------------------------------");
        displayHotels();
        
        do{
            System.out.print("Select a Hotel you would like to view (#): ");
            if (sc.hasNextInt()) {
                choiceHotel = sc.nextInt();
                sc.nextLine();
            } else {
                System.out.println("Invalid input. Please enter a valid number.");
                sc.next(); // discard invalid input
                continue; // skip to the next iteration
            }

            if (choiceHotel > getHotelAmount() || choiceHotel <= 0) {
                System.out.println("Invalid input. Please enter a number within the choices.");
            }

        } while (choiceHotel > getHotelAmount() || choiceHotel <= 0);
        System.out.println("--------------------------------------------------------------------------------");

        do{
            System.out.println("\n\t 1. Hotel Name, Room Amount, Earnings for the Month");
            System.out.println("\t --  Low Level  --");
            System.out.println("\t 2. Booked and Available Rooms");
            System.out.println("\t 3. Specific Room Information");
            System.out.println("\t 4. Specific Reservation Information");
            System.out.println("--------------------------------------------------------------------------------");
            System.out.print("Which kind of information would you like to view: ");
            if (sc.hasNextInt()) {
                choiceOption = sc.nextInt();
                sc.nextLine();
            }
            else {
                System.out.println("Invalid input. Please enter a valid number.");
                sc.next(); // discard invalid input
                continue; // skip to the next iteration
            }

            switch(choiceOption) {
                case 1: highLevelInformation(choiceHotel - 1); break;
                case 2: getHotel(choiceHotel-1).lowLevelRoomAvailability(); break;
                case 3: getHotel(choiceHotel-1).lowLevelRoomInformation(); break;
                case 4: getHotel(choiceHotel-1).lowLevelReservationDetails(); break;
                default:
                System.out.println("Invalid Selection. Please Try Again.");
                break;
            }
        } while (choiceOption < 1 || choiceOption > 4);
    }
    
    public void manageHotel() //test each again as they have their own methods
    {
        String name;
        int choiceHotel = 0, choiceOption = 0;
        Boolean checkDuplicate;
        System.out.println("--------------------------------------------------------------------------------");
        displayHotels();
            do{
                System.out.println("--------------------------------------------------------------------------------");
                System.out.print("Select a Hotel you would like to manage (#): ");
                if (sc.hasNextInt()) {
                    choiceHotel = sc.nextInt();
                    sc.nextLine();
                } else {
                    System.out.println("Invalid input. Please enter a valid number.");
                    sc.next(); // discard invalid input
                    continue; // skip to the next iteration
                }
            } while (choiceHotel > getHotelAmount() || choiceHotel <= 0);

                    System.out.println("--------------------------------------------------------------------------------");
                    do{
                        System.out.println("\t 1. Change the name of the Hotel");
                        System.out.println("\t 2. Add room/s to the Hotel");
                        System.out.println("\t 3. Remove EMPTY room/s from the Hotel");
                        System.out.println("\t 4. Update the base price of the rooms in the Hotel");
                        System.out.println("\t 5. Remove a Reservation");
                        System.out.println("\t 6. Remove the Hotel");
                        System.out.println("--------------------------------------------------------------------------------");
                        System.out.print("Enter your selection (#): ");
                        
                        if (sc.hasNextInt()) {
                            choiceOption = sc.nextInt();
                            sc.nextLine();
                        } else {
                            System.out.println("Invalid input. Please enter a valid number.");
                            sc.next(); // discard invalid input
                            continue; // skip to the next iteration
                        }

                        switch(choiceOption) {

                            case 1: // rename room
                            do{
                                checkDuplicate = false;
                                System.out.print("Enter a name for your Hotel: ");
                                name = sc.nextLine();
                                for(int i = 0; i < getHotelAmount(); i++) {
                                    if(getHotel(i).getHotelName().equalsIgnoreCase(name)) {
                                    checkDuplicate = true;
                                    System.out.println("[!][!] Name is already taken [!][!]");
                                    }
                                }
                            } while (checkDuplicate);

                            System.out.print("Type yes if you wish to continue, anything else to abort: ");
                            if ("yes".equalsIgnoreCase(sc.nextLine())){
                                getHotel(choiceHotel - 1).setHotelName(name);
                                System.out.println("Hotel has been renamed.");
                            }

                            else{
                                System.out.println("Renaming of Hotel has been aborted.");
                            }
                            break;

                            case 2: // add room
                            getHotel(choiceHotel-1).addRooms();
                            break;

                            case 3: // remove room
                            getHotel(choiceHotel-1).removeRooms();
                            break;

                            case 4: // change PricePerNight
                            getHotel(choiceHotel-1).changePricePerNight();
                            break;
                            case 5: // remove reservation
                            getHotel(choiceHotel-1).removeReservation();
                            break; // come back to after making reservations
                            case 6: // remove Hotel
                            if (getHotel(choiceHotel - 1).isHotelEmpty() == true) {
                            System.out.print("Type yes if you wish to continue, anything else to abort: ");
                            }

                            else {
                                System.out.println("Hotel is not empty, type yes if you wish to continue, anything else to abort: ");
                            }
                                if ("yes".equalsIgnoreCase(sc.nextLine())){
                                    hotelList.remove(choiceHotel - 1);
                                    System.out.println("Hotel has been Removed.");
                                }

                                else{
                                    System.out.println("Hotel Removal Aborted.");
                                }
                            break;
                            default:
                            System.out.println("Invalid Selection. Please Try Again.");
                            break;
                        }
                    } while (choiceOption < 1 || choiceOption > 6);

    }
    
    public void highLevelInformation(int hotelIndex) // display
    {
        System.out.println("--------------------------------------------------------------------------------");
        System.out.println("\t Hotel Name: " + getHotel(hotelIndex).getHotelName());
        System.out.println("\t Total Number of Rooms: " + getHotel(hotelIndex).getRoomAmount());
        System.out.println("\t Total Estimated Earnings : " + getHotel(hotelIndex).getEstimatedEarnings());
        System.out.println("--------------------------------------------------------------------------------");
        System.out.println("Press Enter to continue");
        sc.nextLine();
    }

    public void createReservation() {
        if (getHotelAmount() > 0){
            int choiceHotel = 0;

            System.out.println("--------------------------------------------------------------------------------");
            displayHotels();
            do{
                System.out.println("--------------------------------------------------------------------------------");
                System.out.print("Select a Hotel you would like to get a reservation in (#): ");
                if (sc.hasNextInt()) {
                    choiceHotel = sc.nextInt();
                    sc.nextLine();
                } else {
                    System.out.println("Invalid input. Please enter a valid number.");
                    sc.next(); // discard invalid input
                    continue; // skip to the next iteration
                }
            } while (choiceHotel > getHotelAmount() || choiceHotel <= 0);
            
            getHotel(choiceHotel-1).createReservation();

        }
        else {
            System.out.println("No hotels have been created yet.");
        }
    }


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int choice = 0;

        HotelReservationSystem hrs = new HotelReservationSystem();

        System.out.println(" _   _       _       _  ______                               _   _             ");
        System.out.println("| | | |     | |     | | | ___ \\                             | | (_)            ");
        System.out.println("| |_| | ___ | |_ ___| | | |_/ /___  ___  ___ _ ____   ____ _| |_ _  ___  _ __  ");
        System.out.println("|  _  |/ _ \\| __/ _ \\ | |    // _ \\/ __|/ _ \\ '__\\ \\ / / _` | __| |/ _ \\| '_ \\ ");
        System.out.println("| | | | (_) | ||  __/ | | |\\ \\  __/\\__ \\  __/ |   \\ V / (_| | |_| | (_) | | | |");
        System.out.println("\\_| |_/\\___/ \\__\\___|_| \\_| \\_\\___||___/\\___|_|    \\_/ \\__,_|\\__|_|\\___/|_| |_|");

        do{
            System.out.println("--------------------------------------------------------------------------------");
            System.out.println("\t\t\t\t 1. Create Hotel");
            System.out.println("\t\t\t\t 2. View Hotel");
            System.out.println("\t\t\t\t 3. Manage Hotel");
            System.out.println("\t\t\t\t 4. Simulate Booking");
            System.out.println("\t\t\t\t 5. Exit System");
            System.out.println("--------------------------------------------------------------------------------");
            System.out.print("Enter Your Selection (#): ");

            if (sc.hasNextInt()) {
                choice = sc.nextInt();
                sc.nextLine();
            } else {
                System.out.println("Invalid input. Please enter a valid number.");
                sc.next(); // discard invalid input
                continue; // skip to the next iteration
            }

            switch(choice){
                case 1:
                    hrs.createHotel();
                break;

                case 2:
                    if (hrs.getHotelAmount() > 0){
                        hrs.viewHotel();
                    }
                    else {
                        System.out.println("No hotels have been created yet.");
                    }
                break;

                case 3:
                    if (hrs.getHotelAmount() > 0){
                        hrs.manageHotel();
                    }
                    else {
                        System.out.println("No hotels have been created yet.");
                    }
                break;

                case 4:
                if (hrs.getHotelAmount() > 0){
                    hrs.createReservation();
                }
                else {
                    System.out.println("No hotels have been created yet.");
                }
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