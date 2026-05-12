package pl.kinga.oop.telecommunication;

public class Main {
    public static void main(String[] args) {
        TicketPriority[] all = TicketPriority.values();
        for (TicketPriority ticketPriority : all) {
            System.out.println(ticketPriority.getSlaDescription());
        }

        for (TicketPriority ticketPriority : all) {
            if (ticketPriority.isUrgent()) {
                System.out.println(ticketPriority);
            }
        }

        System.out.println(TicketPriority.valueOf("HIGH").getSlaHours());

        TicketPriority priority = TicketPriority.HIGH;
        switch (priority) {
            case CRITICAL -> System.out.println("Eskalacja do managera");
            case HIGH -> System.out.println("Standard queue");
            case MEDIUM -> System.out.println("Additional annotation");
            case LOW -> System.out.println("Just info");
        }
    }
}
