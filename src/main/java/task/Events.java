package task;

public class Events extends Tasks{
    private String start;
    private String end;

    public Events(String description, String start, String end) {
        super(description);
        this.start = start;
        this.end = end;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "(from: " + start + " to: " + end + ")";
    }
}