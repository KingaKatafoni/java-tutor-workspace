package pl.kinga.wielowatkowosc.lekcja6_1;

public class ThreadScenarios {
    public static void main(String[] args){
        // Scenario 1: E-commerce — system must send 500 confirmation emails after Black Friday.
        // Currently, sends them one by one (each takes ~2 seconds).
        // Total time: ~17 minutes. Users complain they wait too long for confirmation.
        // Will multithreading help? Why?
        //
        // Your answer: Will help, I/O-bound  even if we have short time of processing we have plenty of them and this time is multiply by amount of users

        // Scenario 2: Banking — calculating compound interest for a single account.
        // The calculation itself takes 0.001 seconds.
        // Will multithreading help? Why?
        //
        // Your answer: Not help we have relatively short time of single calculation 0.001sec and creating new thread will cost more.

        // Scenario 3: Healthcare — system receives lab results from 20 external labs via API.
        // Each API call takes 3-5 seconds (waiting for response).
        // Currently, calls them sequentially: 20 × 4s = ~80 seconds.
        // Will multithreading help? Why?
        //
        // Your answer: Will help  I/O-bound (waiting for API).

        // Scenario 4: Public sector — a report-generating application.
        // User clicks "Generate report" and the app freezes for 45 seconds.
        // User cannot even click "Cancel" during this time.
        // Will multithreading help? Why?
        //
        // Your answer: Multithread helps we cannot freeze the system when we're waiting for report

        // Scenario 5: Telecom — real-time billing system.
        // Must process 10,000 call records per second from multiple cell towers simultaneously.
        // A single-threaded approach handles ~2,000 records/second.
        // Will multithreading help? Why?
        //
        // Your answer: will help we have large scale of processes here.
    }
}
