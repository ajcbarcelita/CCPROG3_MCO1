public class Date {
    private int day;

    private Date() {
        //private constructor to prevent instantiation w/o initialization
    }

    public Date(int day) {
        if (day < 1 || day > 31) {
            throw new IllegalArgumentException("Day should only be from 1 to 31."); //there's code i need to add to ensure the program doesnt crahs when this error is thrown
        }
        this.day = day;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }
}
