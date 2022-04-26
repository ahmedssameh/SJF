
import java.util.ArrayList;
import java.util.Scanner;

public class SJF
{
    ArrayList<Process> processes = new ArrayList<>();
    ArrayList<Process> completeProcess = new ArrayList<>();
    ArrayList<Process> arrivedProcess = new ArrayList<>();

    public void getInputProcess() throws CloneNotSupportedException {
        System.out.print("Enter number of processes : ");
        Scanner input = new Scanner(System.in);
        int NumberProcessess = input.nextInt();
        for (int i = 0; i < NumberProcessess; i++)
        {
            Process p = new Process();
            System.out.print("Enter Process " + (i + 1) + " Name : ");
            p.processName = input.next();
            System.out.print("Enter Process " + (i + 1) + " Arrival Time : ");
            p.arrivalTime = input.nextInt();
            System.out.print("Enter Process " + (i + 1) + " Burst Time : ");
            p.burstTime = input.nextInt();
            System.out.print("Enter Process " + (i + 1) + " Priority number : ");
            p.priorityNumber = input.nextInt();
            processes.add(p);
            System.out.println();
        }
    }
    public void addArrivedProcess(int value) {
        for (int j = 0; j < value; j++)
        {
            for (Process process : processes) {
                if (!arrivedProcess.contains(process) && process.arrivalTime == j) {
                    arrivedProcess.add(process);
                }
            }
        }
    }
    public int FindMinArrivalTime() {
        int minimum=9999;
        if (processes.size()!=0)
            minimum= processes.get(0).arrivalTime;
        for (int i = 1; i < processes.size(); i++)
        {
            if (processes.get(i).arrivalTime < minimum)
            {
                minimum = processes.get(i).arrivalTime;
            }
        }
        return minimum;
    }
    public Process findMinProcess(ArrayList<Process> P) {
        int mini = P.get(0).burstTime;
        int indxMini = 0;
        for (int i = 1; i < P.size(); i++)
        {
            if (P.get(i).burstTime < mini)
            {
                mini = P.get(i).burstTime;
                indxMini = i;
            }
            else if (P.get(i).burstTime == mini)
            {
                if (P.get(i).arrivalTime < P.get(indxMini).arrivalTime)
                {
                    indxMini = i;
                }
            }
        }
        return P.get(indxMini);
    }
    public void shortestJobFirst() throws CloneNotSupportedException {
        int mini = FindMinArrivalTime();
        Process proces = new Process();
        //System.out.println("process size"+processes.size());

        for (int time = mini; processes.size() != 0 ; time++) //tim =0
        {

            for (Process process : processes) {
                if (process.arrivalTime == time) {
                    arrivedProcess.add(process);
                }
            }
            if (arrivedProcess.size() != 0)
            {
                proces = findMinProcess(arrivedProcess);
            }
            if (completeProcess.size() == 0)
            {
                proces.startTime = mini;
                proces.endTime = mini + proces.burstTime;
                proces.waitingTime = 0;
                proces.turnaroundTime = proces.burstTime;
                Process temp = (Process) proces.clone();
                completeProcess.add(temp);
                arrivedProcess.remove(proces);
                processes.remove(proces);
                addArrivedProcess(proces.endTime);
            }
            else
            {
                proces.startTime = time;
                proces.endTime = proces.startTime + proces.burstTime;
                proces.waitingTime = Math.abs(proces.startTime - proces.arrivalTime);
                proces.turnaroundTime = proces.endTime - proces.arrivalTime;
                Process temp = (Process) proces.clone();
                completeProcess.add(temp);
                //System.out.println("TIME" + time);//"TIME" << time << endl;
                arrivedProcess.remove(proces);
                processes.remove(proces);
                addArrivedProcess(proces.endTime);
            }
            time = FindMinArrivalTime()-1;
        }

        for (Process process : completeProcess) {
            System.out.println("process name : " + process.processName);
            System.out.println("process start time : " + process.startTime);
            System.out.println("process end time : " + process.endTime);
            System.out.println("process waiting time : " + process.waitingTime);
            System.out.println("process turnaround time : " + process.turnaroundTime);
            System.out.println("process priority number : " + process.priorityNumber);
            System.out.println("-----------------------");
        }
        float averageWaitingTime = 0;
        float averageturnAroundTime = 0;
        for (Process process : completeProcess) {
            averageWaitingTime += process.waitingTime;
            averageturnAroundTime += process.turnaroundTime;
        }
        System.out.println("Average waiting time is : " + averageWaitingTime/ completeProcess.size());
        System.out.println("Average turn around time is : " + averageturnAroundTime / completeProcess.size());
        completeProcess.clear();
    }
    public static void main(String []args)throws CloneNotSupportedException{
        while (true){
            System.out.println("Choose 1: input process" +"2: output"+" 3:Exit");
            Scanner in = new Scanner(System.in);
            int x = in.nextInt();
            SJF s = new SJF();
            while (x==1){
                s.getInputProcess();
                System.out.println("Choose 1: input process" +"2: output"+" 3:Exit");
                x = in.nextInt();
            }
            if (x==2){
                s.shortestJobFirst();
            }
            else if (x==3)
                break;
        }
    }
}

