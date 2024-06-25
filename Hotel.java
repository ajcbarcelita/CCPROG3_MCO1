import java.util.*; //for arraylist

public class Hotel {
    Scanner sc = new Scanner(System.in);
    private String hotelName;
    private ArrayList<Room> roomList;
    private String roomPrefix;
    private int roomAmount;
    private double roomPricePerNight = 1299.00;

    public Hotel(String hotelName, int roomAmount, String prefix) {
        this.hotelName = hotelName;
        roomList = new ArrayList<Room>();
        this.roomAmount = roomAmount;
        this.roomPrefix = prefix;
        createRooms();
    }

    public Hotel(String hotelName, int roomAmount, String prefix,double price) {
        this.hotelName = hotelName;
        roomList = new ArrayList<Room>();
        this.roomAmount = roomAmount;
        this.roomPrefix = prefix;
        this.roomPricePerNight = price;
        createRooms();
    }

    public String getHotelName() {
        return this.hotelName;
    }

    public String getHotelPrefix() {
        return this.roomPrefix;
    }

    //room management methods - note to add a confirmation of some sorts to confirm action
    public ArrayList<Room> getRoomList() {
        return this.roomList;
    }

    public void createRooms() {
        for (int i = 1; i <= roomAmount; i++) {
            Room tempRoom = new Room(roomPrefix + i, roomPricePerNight);
            roomList.add(tempRoom);
        }
    }

    public int countEmptyRooms() {
        int ctr = 0;
        for (int i = 0; i < roomAmount; i++) {
            if (roomList.get(i).isRoomEmpty() == true){
                ctr++;
            }
        }
        return ctr;
    }

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

    public int findRoom(String roomName) {
        for (int i = 0; i < roomAmount; i++) {
            if (getRoom(i).getRoomName().equalsIgnoreCase(roomName)) {
                return i;
            }
        }
        return -1;
    }

    public int getRoomAmount() {
        return roomAmount;
    }

    public Room getRoom(int index) {
        if (index < roomAmount || index >= 0){
            return roomList.get(index);
        }
        else{
            return null;
        }
    }

    public double getRoomPrice() {
        return roomPricePerNight;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public Boolean isHotelEmpty() {
        for (int i = 0; i < roomAmount; i++) {
            if (roomList.get(i).isRoomEmpty() == false){
                return false;
            }
        }
        return true;
    }

    public void setRoomPricePerNight(double amount) {
        if (amount >= 100) {
            this.roomPricePerNight = amount;
            for (int i = 0; i < roomAmount; i++) {
                roomList.get(i).setRoomPrice(amount);
            }
        }
    }

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

    public double getEstimatedEarnings() {
        double total = 0;
        for (Room room : roomList) {
            total += room.getEstimatedEarnings();
        }

        return total;
    }

    public void displayReservations() {
        for (Room room : roomList) {
            room.displayReservations();
        }
    }

    public void displayRooms() {
        for (Room room : roomList) {
            System.out.println("\tRoom Number: " + room.getRoomName());            
        }
    }
    
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