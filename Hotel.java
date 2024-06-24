import java.util.*; //for arraylist

public class Hotel {
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

    //getter
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

    public void addRooms(int amount) {
        for (int i = 0; i < amount; i++) {
            Room tempRoom = new Room(roomPrefix + (roomAmount + i), roomPricePerNight);
            roomList.add(tempRoom);
            roomAmount++;
        }
    }

    public void removeRooms(int amount) {
        int ctr = 0;
        for (int i = roomAmount-1; i >= 0 && ctr < amount; i--) {
            if(roomList.get(i).isRoomEmpty()) {
                roomList.remove(i);
                ctr++;
                roomAmount--;
            }
        }
    }

    public Room findRoom(String roomName) {
        for (Room room : roomList) {
            if (room.getRoomName().equals(roomName)) {
                return room;
            }
        }
        return null;
    }

    public int getRoomAmount() {
        return roomAmount;
    }

    public Room getRoom(int index) {
        return roomList.get(index);
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
        this.roomPricePerNight = amount;
        for (int i = 0; i < roomAmount; i++) {
            roomList.get(i).setRoomPrice(amount);
        }
    }

    public int AvailableRoomIndex(Date checkInDate, Date checkOutDate) {
        for (int i = 0; i < roomAmount; i++) {
            if(roomList.get(i).checkRoomAvailability(checkInDate, checkOutDate)) {
                return i;
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
}