package src.hrs.controller;

import java.util.ArrayList;
import java.util.Scanner;

import src.hrs.model.HRS;
import src.hrs.model.Hotel;
import src.hrs.model.Room;
import src.hrs.view.HRSView;

public class HRSController {
    private HRS HRS;
    private HRSView HRSView;

    public HRSController () {
        this.HRS = new HRS();
        this.HRSView = new HRSView();
    }

    public int getHotelAmount() {
        return HRS.getHotelAmount();
    }

    public ArrayList<Hotel> getHotelList() {
        return HRS.getHotelList();
    }

    public void displayHotels(ArrayList<Hotel> hotelList) {
        HRSView.displayHotels(hotelList);
    }

    public static void main (String[] args) {
        HRSController HRSController = new HRSController();
        Scanner sc = new Scanner(System.in);
        int choice1 = 0, choice3 = 0, choice4 = 0, amount = 0;
        boolean checkDuplicate;
        String name, choice2;
        double price = 0;


        System.out.println("\nHotel Reservation System.\n\n");

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
                choice1 = sc.nextInt();
                sc.nextLine();
            } else {
                System.out.println("Invalid input. Please enter a valid number.");
                sc.next(); // discard invalid input
                continue; // skip to the next iteration
            }

            switch(choice1){
                case 1: // create
                    do{
                        checkDuplicate = false;
                        System.out.print("Enter a name for your Hotel: ");
                        name = sc.nextLine();

                        for(int i = 0; i < HRSController.HRS.getHotelAmount(); i++) {
                            if(HRSController.HRS.getHotel(i).getHotelName().equalsIgnoreCase(name)) {
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
                            choice2 = sc.nextLine();
                            if (choice2.equalsIgnoreCase("yes")) {
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
                                HRSController.HRS.createHotel(name, amount, price);
                            }
                            else {
                                HRSController.HRS.createHotel(name, amount);
                            }
                        } while (!(choice2.equalsIgnoreCase("no") || choice2.equalsIgnoreCase("yes")));

                break;

                case 2: // view
                    if (HRSController.HRS.getHotelAmount() > 0){
                        System.out.println("--------------------------------------------------------------------------------");
                        HRSController.displayHotels(HRSController.getHotelList());
                        do{
                            System.out.print("Select a Hotel you would like to view (#): ");
                            if (sc.hasNextInt()) {
                                choice1 = sc.nextInt();
                                sc.nextLine();
                            } else {
                                System.out.println("Invalid input. Please enter a valid number.");
                                sc.next(); // discard invalid input
                                continue; // skip to the next iteration
                            }
                
                            if (choice1 > HRSController.HRS.getHotelAmount() || choice1 <= 0) {
                                System.out.println("Invalid input. Please enter a number within the choices.");
                            }
                
                        } while (choice1 > HRSController.HRS.getHotelAmount() || choice1 <= 0);
                        System.out.println("--------------------------------------------------------------------------------");
                
                        HotelController hotelController = new HotelController(HRSController.HRS.getHotel(choice1-1));

                        do{
                            System.out.println("\n\t 1. Hotel Name, Room Amount, Earnings for the Month");
                            System.out.println("\t --  Low Level  --");
                            System.out.println("\t 2. Booked and Available Rooms");
                            System.out.println("\t 3. Specific Room Information");
                            System.out.println("\t 4. Specific Reservation Information");
                            System.out.println("--------------------------------------------------------------------------------");
                            System.out.print("Which kind of information would you like to view: ");
                            if (sc.hasNextInt()) {
                                choice3 = sc.nextInt();
                                sc.nextLine();
                            }
                            else {
                                System.out.println("Invalid input. Please enter a valid number.");
                                sc.next(); // discard invalid input
                                continue; // skip to the next iteration
                            }
                
                            switch(choice3) {
                                case 1: HRSController.HRSView.highLevelInformation(HRSController.HRS.getHotel(choice1 - 1)); break;
                                case 2:
                                    do{
                                        System.out.print("Select a date you would like to see availability of (#): ");
                                        if (sc.hasNextInt()) {
                                            choice4 = sc.nextInt();
                                            sc.nextLine();
                                        } else {
                                            System.out.println("Invalid input. Please enter a valid number.");
                                            sc.next(); // discard invalid input
                                            continue; // skip to the next iteration
                                        }
                                    } while(choice4 > 31 || choice4 < 1);
                                    HRSController.HRSView.lowLevelRoomAvailability(HRSController.HRS.getHotel(choice1 - 1), choice4);
                                    System.out.println("Press Enter to continue");
                                    sc.nextLine(); 
                                break;
                                case 3:
                                    System.out.println("--------------------------------------------------------------------------------");
                                    hotelController.displayRooms();
                                    do{
                                        System.out.print("Select a Room Number you would like to view: ");
                                        choice2 = sc.nextLine();
                                        choice4 = hotelController.findRoom(choice2);
                                    } while (choice4 == -1);
                                    RoomController roomController = new RoomController(hotelController.getRoom(choice4));
                                    System.out.println("--------------------------------------------------------------------------------");
                                
                                    System.out.println("Room: " + roomController.getRoomName() + "\tPrice per night: " + hotelController.getRoomPrice());
                                    System.out.println("Room Availability: (B = Booked) (X = Available)");
                                    roomController.displayRoomStatus();
                                    System.out.println("--------------------------------------------------------------------------------");
                                    System.out.println("Press Enter to continue");
                                    sc.nextLine();
                                    break;
                                case 4: 
                                    if(!hotelController.isHotelEmpty()) {
                                        System.out.println("--------------------------------------------------------------------------------");
                                        hotelController.displayReservations();
                                        System.out.println("--------------------------------------------------------------------------------");
                                        do{
                                            System.out.print("Select a reservation to view (ID): ");
                                            choice2 = sc.nextLine();
                                        } while (hotelController.findRes(choice2) == -1);
                                        System.out.println("--------------------------------------------------------------------------------");
                                        ReservationController reservationController = new ReservationController(hotelController.getRoom(hotelController.findRes(choice2)).getReservationList().get(hotelController.getRoom(hotelController.findRes(choice2)).findRes(choice2)));
                                        reservationController.displayReservationDetails(hotelController.getRoom(hotelController.findRes(choice2)).getReservationList().get(hotelController.getRoom(hotelController.findRes(choice2)).findRes(choice2)));
                                        System.out.println("--------------------------------------------------------------------------------");
                                    }
                                    else {
                                        System.out.println("There are no reservations to view");
                                    }
                                    System.out.println("Press Enter to continue");
                                    sc.nextLine();
                                break;
                                    default:
                                    System.out.println("Invalid Selection. Please Try Again.");
                                    break;
                                }
                        } while (choice3 < 1 || choice3 > 4);;
                    }
                    else {
                        System.out.println("No hotels have been created yet.");
                    }
                break;

                case 3: // manage
                    if (HRSController.getHotelAmount() > 0){
                        System.out.println("--------------------------------------------------------------------------------");
                        HRSController.displayHotels(HRSController.getHotelList());
                            do{
                                System.out.println("--------------------------------------------------------------------------------");
                                System.out.print("Select a Hotel you would like to manage (#): ");
                                if (sc.hasNextInt()) {
                                    choice1 = sc.nextInt();
                                    sc.nextLine();
                                } else {
                                    System.out.println("Invalid input. Please enter a valid number.");
                                    sc.next(); // discard invalid input
                                    continue; // skip to the next iteration
                                }
                            } while (choice1 > HRSController.getHotelAmount() || choice1 <= 0);
                            HotelController hotelController = new HotelController(HRSController.HRS.getHotel(choice1-1));
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
                                            choice3 = sc.nextInt();
                                            sc.nextLine();
                                        } else {
                                            System.out.println("Invalid input. Please enter a valid number.");
                                            sc.next(); // discard invalid input
                                            continue; // skip to the next iteration
                                        }
                
                                        switch(choice3) {
                
                                            case 1: // rename room
                                            do{
                                                checkDuplicate = false;
                                                System.out.print("Enter a name for your Hotel: ");
                                                name = sc.nextLine();
                                                for(int i = 0; i < HRSController.getHotelAmount(); i++) {
                                                    if(hotelController.getHotelName().equalsIgnoreCase(name)) {
                                                    checkDuplicate = true;
                                                    System.out.println("[!][!] Name is already taken [!][!]");
                                                    }
                                                }
                                            } while (checkDuplicate);
                
                                            System.out.print("Type yes if you wish to continue, anything else to abort: ");
                                            if ("yes".equalsIgnoreCase(sc.nextLine())){
                                                hotelController.setHotelName(name);
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
                                                if (amount > 50 || amount < 1 || amount+hotelController.getRoomAmount() > 50) {
                                                    System.out.println("[!][!] Too many rooms [!][!]");
                                                }
                                            } while (hotelController.getRoomAmount() + amount > 50);
                                
                                            System.out.print("Type yes if you wish to continue, anything else to abort: ");
                                                if ("yes".equalsIgnoreCase(sc.nextLine())){
                                                    hotelController.addRooms(amount);
                                                    System.out.println("Rooms have been added.");
                                                }
                                
                                                else{
                                                    System.out.println("Adding of rooms has been aborted.");
                                                }
                
                
                                            break;
                
                                            case 3: // remove room
                                            do{
                                                System.out.print("Enter the amount of rooms to remove from the Hotel (Empty Rooms: " + hotelController.countEmptyRooms()+" rooms will be removed starting from the last room): ");
                                                if (sc.hasNextInt()) {
                                                    amount = sc.nextInt();
                                                    sc.nextLine();
                                                } 
                                                
                                                else {
                                                    System.out.println("Invalid input. Please enter a valid number.");
                                                    sc.next(); // discard invalid input
                                                    continue; // skip to the next iteration
                                                }
                
                                                if (amount > hotelController.countEmptyRooms() || amount < 1 || hotelController.countEmptyRooms()-amount<1) {
                                                    System.out.println("[!][!] Enter a valid room amount [!][!]");
                                                }
                                            } while (amount > hotelController.countEmptyRooms() || amount < 1 || hotelController.countEmptyRooms()-amount<1);
                                
                                            System.out.print("Type yes if you wish to continue, anything else to abort: ");
                                            if ("yes".equalsIgnoreCase(sc.nextLine())){
                                                for (int i = hotelController.countEmptyRooms()-1, ctr = 0; i >= 0 && ctr < amount; i--) {
                                                    hotelController.removeRoom(i);
                                                }
                                                System.out.println("Rooms have been removed.");
                                            }
                                
                                            else{
                                                System.out.println("Removing of rooms has been aborted.");
                                            }
                                            break;
                
                                            case 4: // change PricePerNight
                                            if (hotelController.isHotelEmpty() == true){
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
                                                    hotelController.setRoomPricePerNight(amount);
                                                    System.out.println("Room's price per night has been adjusted.");
                                                }
                                            
                                                else{
                                                    System.out.println("Price change aborted.");
                                                }
                                            }
                                            else{
                                                System.out.println("Hotel is not empty.");
                                            }
                                        
                                            System.out.println("Press Enter to continue");
                                            sc.nextLine();
                                            break;
                
                                            case 5: // remove reservation
                                            System.out.println("--------------------------------------------------------------------------------");
                                            hotelController.displayReservations();
                                            do{
                                                System.out.println("--------------------------------------------------------------------------------");
                                                System.out.print("Select a reservation to remove (ID): ");
                                                choice2 = sc.nextLine();
                                            } while (hotelController.findRes(choice2) == -1);
                                        
                                            System.out.print("Type yes if you wish to continue, anything else to abort: ");
                                            if ("yes".equalsIgnoreCase(sc.nextLine())){
                                                hotelController.removeReservation(choice2);
                                                System.out.println("Reservation has been removed.");
                                            }
                                        
                                            else{
                                                System.out.println("Removing of Reservation has been aborted.");
                                            }
                                            break; // come back to after making reservations
                
                                            case 6: // remove Hotel
                                            if (hotelController.isHotelEmpty() == true) {
                                            System.out.print("Type yes if you wish to continue, anything else to abort: ");
                                            }
                
                                            else {
                                                System.out.println("Hotel is not empty, type yes if you wish to continue, anything else to abort: ");
                                            }
                                                if ("yes".equalsIgnoreCase(sc.nextLine())){
                                                    HRSController.getHotelList().remove(choice1 - 1);
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
                                    } while (choice3 < 1 || choice3 > 6);
                    }
                    else {
                        System.out.println("No hotels have been created yet.");
                    }
                break;

                case 4: // reserve
                if (HRSController.getHotelAmount() > 0){
                    int checkInDate = 0, checkOutDate = 0;
                    int roomIndex = 0;
                    String nameRes;
                    Room roomBooked;
                            
                    System.out.println("--------------------------------------------------------------------------------");
                    HRSController.displayHotels(HRSController.getHotelList());
                    do{
                        System.out.println("--------------------------------------------------------------------------------");
                        System.out.print("Select a Hotel you would like to get a reservation in (#): ");
                        if (sc.hasNextInt()) {
                            choice1 = sc.nextInt();
                            sc.nextLine();
                        } else {
                            System.out.println("Invalid input. Please enter a valid number.");
                            sc.next(); // discard invalid input
                            continue; // skip to the next iteration
                        }
                    } while (choice1 > HRSController.getHotelAmount() || choice1 <= 0);
                    HotelController hotelController = new HotelController(HRSController.HRS.getHotel(choice1-1));
                    System.out.print("Enter a name for the Reservation: ");
                    nameRes = sc.nextLine();
                    System.out.println("--------------------------------------------------------------------------------");
                
                    do{
                        do {
                            System.out.print("Enter your check-in date: ");
                            if (sc.hasNextInt()) {
                                checkInDate = sc.nextInt();
                                sc.nextLine();
                            } else {
                                System.out.println("Invalid input. Please enter a valid number.");
                                sc.next(); // discard invalid input
                                continue; // skip to the next iteration
                            }
                        } while (checkInDate < 1 || checkInDate >= 31 );

                        do {
                            System.out.print("Enter your check-out date: ");
                            if (sc.hasNextInt()) {
                                checkOutDate = sc.nextInt();
                                sc.nextLine();
                            } else {
                                System.out.println("Invalid input. Please enter a valid number.");
                                sc.next(); // discard invalid input
                                continue; // skip to the next iteration
                            }
                        } while (checkOutDate <= 1 || checkOutDate > 31);
                    
                        roomIndex = hotelController.AvailableRoomIndex(checkInDate, checkOutDate);
                    
                    } while ((checkInDate > checkOutDate) || roomIndex == -1);

                    if(roomIndex == -1) {
                        System.out.println("No available rooms for the selected dates.");
                    }
                
                    else {
                        roomBooked = hotelController.getRoom(roomIndex);
                        System.out.println("Reservation ID: " + hotelController.getRoom(roomIndex).createReservation(nameRes, checkInDate, checkOutDate, roomBooked));
                        System.out.println("Room Booked: "+ roomBooked.getRoomName());
                        System.out.println("--------------------------------------------------------------------------------");
                        System.out.println("Press Enter to continue");
                        sc.nextLine();
                    };
                }
                else {
                    System.out.println("No hotels have been created yet.");
                }
                break;

                case 5: // exit
                    System.out.println("--------------------------------------------------------------------------------");
                    System.out.println("Exiting System... Goodbye!");
                break;

            default:
            System.out.println("Invalid Selection. Please Try Again.");
            break;
            }
        } while(choice1 != 5);

        sc.close();
        return;
    }
}
