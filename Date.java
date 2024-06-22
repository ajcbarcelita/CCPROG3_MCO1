public class Date {
    private int day;

    private Date() {
        //prevents instantiation w/o initialization
    }

    public Date(int day) {
        this.day = day;
    }

    public int getDate() {
        return this.day;
    }

    public void setDate(int day) {
        this.day = day;
    }
}
