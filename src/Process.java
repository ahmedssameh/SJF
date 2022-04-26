
import java.util.Vector;

public class Process implements Cloneable{

    public  String processName;
    public int arrivalTime;
    public int burstTime;
    public int endTime;
    public int startTime;
    public double waitingTime;
    public double turnaroundTime;
    public int priorityNumber;

    protected Object clone() throws CloneNotSupportedException{
        return super.clone();
    }
    public Process()
    {
        processName = "";
        arrivalTime = 0;
        burstTime = 0;
        endTime = 0;
        startTime = 0;
        waitingTime = 0;
        turnaroundTime = 0;
        priorityNumber=0;
    }
}