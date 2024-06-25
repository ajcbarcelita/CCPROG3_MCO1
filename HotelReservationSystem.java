import java.util.*;

public class HotelReservationSystem {
    Scanner sc = new Scanner(System.in);
    private ArrayList<Hotel> hotelList;

    public HotelReservationSystem() {
        hotelList = new ArrayList<Hotel>();
    }

    public Hotel getHotel (int index) {
        return hotelList.get(index);
    }

    public int getHotelAmount() {
        return hotelList.size();
    }

    public void displayHotels() {
        for (int i = 0; i < hotelList.size(); i++) {
            System.out.println((i+1)+ " Hotel Name: " + getHotel(i).getHotelName());
        }
    }

    public void addHotel(Hotel hotel) {
        hotelList.add(hotel);
    }

    public void createHotel() {
        String choice;
        String name;
        String prefix;
        int amount = 0;
        double price;
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
                    System.out.println("[!][!] Name is already taken [!][!]");
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
                    price = sc.nextDouble();
                    } while(price < 100.00);
                    tempHotel = new Hotel(name, amount, prefix, price);
                }
                else {
                    tempHotel = new Hotel(name, amount, prefix);
                }
            } while (!(choice.equalsIgnoreCase("no") || choice.equalsIgnoreCase("yes")));

            addHotel(tempHotel);
    }
    
    public void viewHotel() {
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
            } else {
                System.out.println("Invalid input. Please enter a valid number.");
                sc.next(); // discard invalid input
                continue; // skip to the next iteration
            }
        } while (choiceOption < 1 || choiceOption > 4);

        switch(choiceOption) {
            case 1: highLevelInformation(choiceHotel - 1); break;
            case 2: lowLevelRoomAvailability(choiceHotel - 1); break;
            case 3: lowLevelRoomInformation(choiceHotel - 1); break;
            case 4: lowLevelReservationDetails(choiceHotel - 1); break;
            default:
            System.out.println("Invalid Selection. Please Try Again.");
            break;
        }
    }
    
    public void manageHotel() //possible to move each manage option into its own method
    {
        String name, resID;
        int choiceHotel = 0, choiceOption = 0, amount = 0;
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

                    } while (choiceOption < 1 || choiceOption > 6);

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
                        do{
                            System.out.print("Enter the amount of rooms to add to the Hotel: ");
                            if (sc.hasNextInt()) {
                                amount = sc.nextInt();
                                sc.nextLine();
                            } else {
                                System.out.println("Invalid input. Please enter a valid number.");
                                sc.next(); // discard invalid input
                                continue; // skip to the next iteration
                            }
                            if (amount > 50 || amount < 1) {
                                System.out.println("[!][!] Enter a valid room amount [!][!]");
                            }
                        } while (getHotel(choiceHotel - 1).getRoomAmount() + amount > 50);

                        System.out.print("Type yes if you wish to continue, anything else to abort: ");
                            if ("yes".equalsIgnoreCase(sc.nextLine())){
                                getHotel(choiceHotel - 1).addRooms(amount);
                                System.out.println("Rooms have been added.");
                            }

                            else{
                                System.out.println("Adding of rooms has been aborted.");
                            }
                        break;

                        case 3: // remove room
                            do{
                                System.out.print("Enter the amount of rooms to remove from the Hotel (Empty Rooms: " + getHotel(choiceHotel - 1).countEmptyRooms()+" rooms will be removed starting from the last room): ");
                                if (sc.hasNextInt()) {
                                    amount = sc.nextInt();
                                    sc.nextLine();
                                } else {
                                    System.out.println("Invalid input. Please enter a valid number.");
                                    sc.next(); // discard invalid input
                                    continue; // skip to the next iteration
                                }
                                if (amount > getHotel(choiceHotel - 1).countEmptyRooms() || amount < 1) {
                                    System.out.println("[!][!] Enter a valid room amount [!][!]");
                                }
                            } while (getHotel(choiceHotel - 1).getRoomAmount() - amount < 1);

                            System.out.print("Type yes if you wish to continue, anything else to abort: ");
                            if ("yes".equalsIgnoreCase(sc.nextLine())){
                                getHotel(choiceHotel - 1).removeRooms(amount);
                                System.out.println("Rooms have been removed.");
                            }

                            else{
                                System.out.println("Removing of rooms has been aborted.");
                            }
                        break;

                        case 4: // change PricePerNight
                            if (getHotel(choiceHotel - 1).isHotelEmpty() == true){
                                do{
                                    System.out.print("Enter the new price of the room per night: ");
                                    if (sc.hasNextInt()) {
                                        amount = sc.nextInt();
                                        sc.nextLine();
                                    } else {
                                        System.out.println("Invalid input. Please enter a valid number.");
                                        sc.next(); // discard invalid input
                                        continue; // skip to the next iteration
                                    }
                                    if (amount < 100) {
                                        System.out.println("[!][!] Enter a larger amount [!][!]");
                                    }
                                } while (amount < 100);

                                System.out.print("Type yes if you wish to continue, anything else to abort: ");
                                if ("yes".equalsIgnoreCase(sc.nextLine())){
                                    getHotel(choiceHotel - 1).setRoomPricePerNight(amount);;
                                    System.out.println("Room's price per night has been adjusted.");
                                }

                                else{
                                    System.out.println("Price change aborted.");
                                }
                            }
                            else{
                                System.out.println("Hotel is not empty.");
                            }
                        break;
                        case 5: // remove reservation
                            System.out.println("--------------------------------------------------------------------------------");
                            getHotel(choiceHotel - 1).displayReservations();
                            do{
                                System.out.println("--------------------------------------------------------------------------------");
                                System.out.print("Select a reservation to remove (ID): ");
                                resID = sc.nextLine();
                            } while (getHotel(choiceHotel-1).findRes(resID) == -1);

                            System.out.print("Type yes if you wish to continue, anything else to abort: ");
                            if ("yes".equalsIgnoreCase(sc.nextLine())){
                                getHotel(choiceHotel - 1).getRoom(getHotel(choiceHotel - 1).findRes(resID)).removeReservation(resID);
                                System.out.println("Reservation has been removed.");
                            }

                            else{
                                System.out.println("Removing of Reservation has been aborted.");
                            }

                        break; // come back to after making reservations
                        case 6: // remove Hotel
                        System.out.print("Type yes if you wish to continue, anything else to abort: ");
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

    }
    
    public void highLevelInformation(int hotelIndex) {
        System.out.println("--------------------------------------------------------------------------------");
        System.out.println("\t Hotel Name: " + getHotel(hotelIndex).getHotelName());
        System.out.println("\t Total Number of Rooms: " + getHotel(hotelIndex).getRoomAmount());
        System.out.println("\t Total Estimated Earnings : " + getHotel(hotelIndex).getEstimatedEarnings());
        System.out.println("--------------------------------------------------------------------------------");
        System.out.println("Press Enter to continue");
        sc.nextLine();
    }

    public void lowLevelRoomAvailability(int hotelIndex) {
        int choiceDate = 0;
        do{
            System.out.print("Select a date you would like to see availability of (#): ");
            if (sc.hasNextInt()) {
                choiceDate = sc.nextInt();
                sc.nextLine();
            } else {
                System.out.println("Invalid input. Please enter a valid number.");
                sc.next(); // discard invalid input
                continue; // skip to the next iteration
            }
        } while(choiceDate > 31 || choiceDate < 1);
        System.out.println("--------------------------------------------------------------------------------");
        System.out.println("\t Total Number of Rooms: " + getHotel(hotelIndex).getRoomAmount());
        System.out.println("\t Number of Rooms Empty on " + choiceDate + ": " + getHotel(hotelIndex).checkEmptyRooms(choiceDate));
        System.out.println("\t Number of Rooms Booked on " + choiceDate + ": "+ (getHotel(hotelIndex).getRoomAmount() - getHotel(hotelIndex).checkEmptyRooms(choiceDate)));
        System.out.println("--------------------------------------------------------------------------------");
        System.out.println("Press Enter to continue");
        sc.nextLine();
    }

    public void lowLevelRoomInformation(int hotelIndex) {
        String choiceRoom;
        int roomIndex = 0;
        System.out.println("--------------------------------------------------------------------------------");
        getHotel(hotelIndex).displayRooms();
        do{
            System.out.print("Select a Room Number you would like to view: ");
            choiceRoom = sc.nextLine();
            roomIndex = getHotel(hotelIndex).findRoom(choiceRoom);
        } while (roomIndex == -1);
        System.out.println("--------------------------------------------------------------------------------");

        System.out.println("Room: " + getHotel(hotelIndex).getRoom(roomIndex).getRoomName() + "\tPrice per night: " + getHotel(hotelIndex).getRoomPrice());
        System.out.println("Room Availability: (B = Booked) (X = Available)");
        getHotel(hotelIndex).getRoom(roomIndex).displayRoomStatus();
        System.out.println("--------------------------------------------------------------------------------");
        System.out.println("Press Enter to continue");
        sc.nextLine();
    }

    public void lowLevelReservationDetails(int hotelIndex) {
        String resID;
        System.out.println("--------------------------------------------------------------------------------");
        getHotel(hotelIndex).displayReservations();
        System.out.println("--------------------------------------------------------------------------------");
        do{
            System.out.print("Select a reservation to remove (ID): ");
            resID = sc.nextLine();
        } while (getHotel(hotelIndex).findRes(resID) == -1);
        System.out.println("--------------------------------------------------------------------------------");
        getHotel(hotelIndex).getRoom(getHotel(hotelIndex).findRes(resID)).getReservationList().get(getHotel(hotelIndex).getRoom(getHotel(hotelIndex).findRes(resID)).findRes(resID)).displayReservationDetails();
        System.out.println("--------------------------------------------------------------------------------");
        System.out.println("Press Enter to continue");
        sc.nextLine();
    }

    public void createReservation() {
        if (getHotelAmount() > 0){
            int checkInDate, checkOutDate;
            int choiceHotel = 0, roomIndex = 0;
            String name;
            Room roomBooked;

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
            System.out.print("Enter a name for the Reservation: ");
            name = sc.nextLine();
            System.out.println("--------------------------------------------------------------------------------");

            do{
                do {
                    System.out.print("Enter your check-in date: ");
                    checkInDate = sc.nextInt();
                    sc.nextLine();
                } while (checkInDate < 1 || checkInDate >= 31 );
                do {
                    System.out.print("Enter your check-out date: ");
                    checkOutDate = sc.nextInt();
                    sc.nextLine();
                } while (checkOutDate <= 1 || checkOutDate > 31 );

                roomIndex = getHotel(choiceHotel - 1).AvailableRoomIndex(checkInDate, checkOutDate);

            } while ((checkInDate > checkOutDate ) && roomIndex == -1);
            
            roomIndex = getHotel(choiceHotel-1).AvailableRoomIndex(checkInDate, checkOutDate);

            if(roomIndex == -1) {
                System.out.println("No available rooms for the selected dates.");
            }

            else {
                roomBooked = hotelList.get(choiceHotel-1).getRoom(roomIndex);
                System.out.println("Reservation ID: " + getHotel(choiceHotel-1).getRoom(roomIndex).createReservation(name, checkInDate, checkOutDate, roomBooked));
                System.out.println("Room Booked: "+ roomBooked.getRoomName());
                System.out.println("--------------------------------------------------------------------------------");
                System.out.println("Press Enter to continue");
                sc.nextLine();
            }
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