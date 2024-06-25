import java.util.*; //for arraylist

/**
 * Represents a hotel with a list of rooms.
 * 
 * A hotel has a name, a prefix for its rooms, and a list of rooms. Each room has a unique name, a price per night, and a status (available or booked).
 * 
 * The hotel provides methods to create rooms, add or remove rooms, change the price per night, and manage reservations.
 */ 
public class Hotel {
    Scanner sc = new Scanner(System.in);
    private String hotelName;
    private ArrayList<Room> roomList;
    private String roomPrefix;
    private int roomAmount;
    private double roomPricePerNight = 1299.00;

    /** Creates a new hotel with the given name, room amount, and prefix.
     * 
     * @param hotelName the name of the hotel
     * @param roomAmount the initial number of rooms
     * @param prefix the prefix for the room names
     */
    public Hotel(String hotelName, int roomAmount, String prefix) {
        this.hotelName = hotelName;
        roomList = new ArrayList<Room>();
        this.roomAmount = roomAmount;
        this.roomPrefix = prefix;
        createRooms();
    }

    /**
     * Creates a new hotel with the given name, room amount, prefix, and price per night.
     * 
     * @param hotelName the name of the hotel
     * @param roomAmount the initial number of rooms
     * @param prefix the prefix for the room names
     * @param pricePerNight the price per night for each room
     */
    public Hotel(String hotelName, int roomAmount, String prefix,double price) {
        this.hotelName = hotelName;
        roomList = new ArrayList<Room>();
        this.roomAmount = roomAmount;
        this.roomPrefix = prefix;
        this.roomPricePerNight = price;
        createRooms();
    }

    /**
     * Gets the name of the Hotel.
     * 
     * @return the hotel name
     */
    public String getHotelName() {
        return this.hotelName;
    }

    /**
     * Gets the prefix for the room names.
     * 
     * @return the room prefix
     */
    public String getHotelPrefix() {
        return this.roomPrefix;
    }

    /**
     * Gets the list of rooms in the Hotel.
     * 
     * @return the list of rooms
     */
    public ArrayList<Room> getRoomList() {
        return this.roomList;
    }

    /**
     * Gets the amount of rooms in the Hotel.
     * 
     * @return the amount of rooms
     */
    public int getRoomAmount() {
        return roomAmount;
    }

    /**
     * Gets the room in the specified index of the List.
     * 
     * @param index the index of the room
     * @return the room
     */
    public Room getRoom(int index) {
        if (index < roomAmount || index >= 0){
            return roomList.get(index);
        }
        else{
            return null;
        }
    }

    /**
     * Gets the price of the room per night.
     * 
     * @return room's price per night
     */
    public double getRoomPrice() {
        return roomPricePerNight;
    }

    /**
     * Creates the amount of rooms specified when the Hotel is Instantiated. 
     */

    /**
     * Creates the rooms for this hotel.
     * 
     * This method creates a new room for each number in the range from 1 to roomAmount, using the roomPrefix and roomPricePerNight fields to initialize the room name and price.
     * The rooms are added to the roomList field. 
     */
    public void createRooms() {
        for (int i = 1; i <= roomAmount; i++) {
            Room tempRoom = new Room(roomPrefix + i, roomPricePerNight);
            roomList.add(tempRoom);
        }
    }

    /**
     * Counts the number of empty rooms in this hotel.
     * 
     * @return the number of empty rooms in this hotel
     */ 
    public int countEmptyRooms() {
        int ctr = 0;
        for (int i = 0; i < roomAmount; i++) {
            if (roomList.get(i).isRoomEmpty() == true){
                ctr++;
            }
        }
        return ctr;
    }

    /**
     * Adds rooms to this hotel.
     * 
     * This method prompts the user to enter the number of rooms to add, and then adds that many rooms to the hotel.
     * The rooms are given names based on the roomPrefix field and the current size of the roomList field.
     */
    public void addRooms() {
        int amount = 0;
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
            if (amount > 50 || amount < 1 || amount+roomAmount > 50) {
                System.out.println("[!][!] Too many rooms [!][!]");
            }
        } while (roomAmount + amount > 50);

        System.out.print("Type yes if you wish to continue, anything else to abort: ");
            if ("yes".equalsIgnoreCase(sc.nextLine())){
                for (int i = 0; i < amount; i++) {
                    Room tempRoom = new Room(roomPrefix + (roomAmount + i), roomPricePerNight);
                    roomList.add(tempRoom);
                    roomAmount++;
                }
                System.out.println("Rooms have been added.");
            }

            else{
                System.out.println("Adding of rooms has been aborted.");
            }
        
    }

    /**
     * 
     * Removes rooms from this hotel.
     * 
     * This method removes up to amount empty rooms from the hotel, starting from the last room.
     * If there are not enough empty rooms to remove, the method does nothing. 
     */
    public void removeRooms() {
        int amount = 0, ctr = 0;
        if (countEmptyRooms() == 0) {
            System.out.println("There are no EMPTY rooms to remove.");
        }
        else {
            do{
                System.out.print("Enter the amount of rooms to remove from the Hotel (Empty Rooms: " + countEmptyRooms()+" rooms will be removed starting from the last room): ");
                if (sc.hasNextInt()) {
                    amount = sc.nextInt();
                    sc.nextLine();
                } else {
                    System.out.println("Invalid input. Please enter a valid number.");
                    sc.next(); // discard invalid input
                    continue; // skip to the next iteration
                }
                if (amount > countEmptyRooms() || amount < 1 || countEmptyRooms()-amount<1) {
                    System.out.println("[!][!] Enter a valid room amount [!][!]");
                }
            } while (getRoomAmount() - amount < 1);

            System.out.print("Type yes if you wish to continue, anything else to abort: ");
            if ("yes".equalsIgnoreCase(sc.nextLine())){
                for (int i = roomAmount-1; i >= 0 && ctr < amount; i--) {
                    if(roomList.get(i).isRoomEmpty()) {
                        roomList.remove(i);
                        ctr++;
                        roomAmount--;
                    }
                }
                System.out.println("Rooms have been removed.");
            }

            else{
                System.out.println("Removing of rooms has been aborted.");
            }
        }
    }
    
    /**
     * Changes the price per night for all rooms in this hotel.
     * 
     * This method prompts the user to enter a new price per night, and then sets the roomPricePerNight field to that value.
     * It also sets the price per night for all rooms in the roomList field.
     */
    public void changePricePerNight() {
        int amount = 0;
        if (isHotelEmpty() == true){
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
                setRoomPricePerNight(amount);
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
    }

    /**
     * Removes a reservation from a room in this hotel.
     * 
     * This method first displays all reservations in the hotel.
     * Then it prompts the user to select a reservation to remove, and removes that reservation from the corresponding room. 
     */
    public void removeReservation() {
        String resID;
        System.out.println("--------------------------------------------------------------------------------");
        displayReservations();
        do{
            System.out.println("--------------------------------------------------------------------------------");
            System.out.print("Select a reservation to remove (ID): ");
            resID = sc.nextLine();
        } while (findRes(resID) == -1);

        System.out.print("Type yes if you wish to continue, anything else to abort: ");
        if ("yes".equalsIgnoreCase(sc.nextLine())){
            getRoom(findRes(resID)).removeReservation(resID);
            System.out.println("Reservation has been removed.");
        }

        else{
            System.out.println("Removing of Reservation has been aborted.");
        }

    }

    /**
     * Displays the availability of rooms in this hotel on a given date.
     * 
     * This method prompts the user to enter a date, and then displays the total number of rooms in the hotel,
     * the number of empty rooms on the given date, and the number of booked rooms on the given date.
     * */
    public void lowLevelRoomAvailability() 
    {
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
        System.out.println("\t Total Number of Rooms: " + getRoomAmount());
        System.out.println("\t Number of Rooms Empty on " + choiceDate + ": " + checkEmptyRooms(choiceDate));
        System.out.println("\t Number of Rooms Booked on " + choiceDate + ": "+ (getRoomAmount() - checkEmptyRooms(choiceDate)));
        System.out.println("--------------------------------------------------------------------------------");
        System.out.println("Press Enter to continue");
        sc.nextLine();
    }

    /**
     * Displays information about a room in this hotel.
     * 
     * This method first displays all rooms in the hotel.
     * Then it prompts the user to select a room to view, and displays the name, price per night, and availability of that room.
     */
    public void lowLevelRoomInformation() {
        String choiceRoom;
        int roomIndex = 0;
        System.out.println("--------------------------------------------------------------------------------");
        displayRooms();
        do{
            System.out.print("Select a Room Number you would like to view: ");
            choiceRoom = sc.nextLine();
            roomIndex = findRoom(choiceRoom);
        } while (roomIndex == -1);
        System.out.println("--------------------------------------------------------------------------------");

        System.out.println("Room: " + getRoom(roomIndex).getRoomName() + "\tPrice per night: " + getRoomPrice());
        System.out.println("Room Availability: (B = Booked) (X = Available)");
        getRoom(roomIndex).displayRoomStatus();
        System.out.println("--------------------------------------------------------------------------------");
        System.out.println("Press Enter to continue");
        sc.nextLine();
    }

    /**
     * Displays details about a reservation in this hotel.
     * 
     * This method first displays all reservations in the hotel.
     * Then it prompts the user to select a reservation to view, and displays the details of that reservation.
     */
    public void lowLevelReservationDetails() {
        String resID;
        if(!isHotelEmpty()) {
            System.out.println("--------------------------------------------------------------------------------");
            displayReservations();
            System.out.println("--------------------------------------------------------------------------------");
            do{
                System.out.print("Select a reservation to view (ID): ");
                resID = sc.nextLine();
            } while (findRes(resID) == -1);
            System.out.println("--------------------------------------------------------------------------------");
            getRoom(findRes(resID)).getReservationList().get(getRoom(findRes(resID)).findRes(resID)).displayReservationDetails();
            System.out.println("--------------------------------------------------------------------------------");
        }
        else {
            System.out.println("There are no reservations to view");
        }
        System.out.println("Press Enter to continue");
        sc.nextLine();
    }

    /**
     * Creates a new reservation in a room in this hotel.
     * 
     * This method prompts the user to enter a name and check-in and check-out dates for the reservation.
     * It then finds an available room for the given dates and creates a new reservation for that room.
     */ 
    public void createReservation() {
        int checkInDate, checkOutDate;
        int roomIndex = 0;
        String name;
        Room roomBooked;
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
                } while (checkOutDate <= 1 || checkOutDate > 31);

                roomIndex = AvailableRoomIndex(checkInDate, checkOutDate);

            } while ((checkInDate > checkOutDate) || roomIndex == -1);
            
            roomIndex = AvailableRoomIndex(checkInDate, checkOutDate);

            if(roomIndex == -1) {
                System.out.println("No available rooms for the selected dates.");
            }

            else {
                roomBooked = getRoom(roomIndex);
                System.out.println("Reservation ID: " + getRoom(roomIndex).createReservation(name, checkInDate, checkOutDate, roomBooked));
                System.out.println("Room Booked: "+ roomBooked.getRoomName());
                System.out.println("--------------------------------------------------------------------------------");
                System.out.println("Press Enter to continue");
                sc.nextLine();
            }
    }

    /**
     * Finds the index of a room with the given name in this hotel.
     * 
     * @param roomName the name of the room to find
     * @return the index of the room with the given name, or -1 if no such room exists
     */
    public int findRoom(String roomName) {
        for (int i = 0; i < roomAmount; i++) {
            if (getRoom(i).getRoomName().equalsIgnoreCase(roomName)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Sets the name of this hotel.
     * 
     * @param hotelName the new name of the hotel
     */ 
    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    /**
     * Checks whether this hotel is empty.
     * 
     * @return true if this hotel has no non-empty rooms, and false otherwise
     */
    public Boolean isHotelEmpty() {
        for (int i = 0; i < roomAmount; i++) {
            if (roomList.get(i).isRoomEmpty() == false){
                return false;
            }
        }
        return true;
    }

    /**
     * Sets the price per night for all rooms in this hotel.
     * 
     * @param amount the new price per night for all rooms in this hotel
     */
    public void setRoomPricePerNight(double amount) {
        if (amount >= 100) {
            this.roomPricePerNight = amount;
            for (int i = 0; i < roomAmount; i++) {
                roomList.get(i).setRoomPrice(amount);
            }
        }
    }

    /**
     * Finds the index of the first available room for the given dates.
     * 
     * @param checkInDate the check-in date
     * @param checkOutDate the check-out date
     * @return the index of the first available room, or -1 if no room is available */
    public int AvailableRoomIndex(int checkInDate, int checkOutDate) {
        if (checkInDate < checkOutDate) {
            for (int i = 0; i < roomAmount; i++) {
                if(roomList.get(i).checkRoomAvailability(checkInDate, checkOutDate)) {
                    return i;
                }
            }
        }
        return -1;
    }

    /**
     * Calculates the estimated earnings of this hotel.
     * 
     * @return the estimated earnings of this hotel
     */
    public double getEstimatedEarnings() {
        double total = 0;
        for (Room room : roomList) {
            total += room.getEstimatedEarnings();
        }

        return total;
    }

    /**
     * Displays all reservations in this hotel.
     */
    public void displayReservations() {
        for (Room room : roomList) {
            room.displayReservations();
        }
    }

    /**
     * Displays all rooms in this hotel.
     */
    public void displayRooms() {
        for (Room room : roomList) {
            System.out.println("\tRoom Number: " + room.getRoomName());            
        }
    }
    
    /**
     * Finds the index of the room with a reservation matching the given ID.
     * 
     * @param reservationID the ID of the reservation to find
     * @return the index of the room with the matching reservation, or -1 if no such reservation exists
     */
    public int findRes(String reservationID) {
        for (int i = 0; i < roomAmount; i++) {
            for (int j = 0; j < getRoom(i).getReservationAmount(); j++) {
                if (getRoom(i).getReservationList().get(j).getReservationID().equalsIgnoreCase(reservationID)){
                    return i;
                }
            }
        }

        return -1;
    }

    /**
     * Counts the number of empty rooms on a given day.
     * 
     * @param day the day to check
     * @return the number of empty rooms on the given day 
     */
    public int checkEmptyRooms(int day) {
        int ctr = 0;
        if (day > 0 || day < 32) {
            for (int i = 0; i < roomAmount; i++) {
                if(roomList.get(i).getStatus(day-1).equalsIgnoreCase("X")) {
                    ctr++;
                }
            }
            return ctr;
        }
        return 0;
    }

    
}