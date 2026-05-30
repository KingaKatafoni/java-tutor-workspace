package pl.kinga.kolekcjegeneryki.ecommerce.ecommercedeque;

import java.util.ArrayDeque;
import java.util.Deque;

public class BrowsingHistory {
    private Deque<String> history;

    public BrowsingHistory() {
        this.history = new ArrayDeque<>();
    }

    public void visitPage(String url) {
        history.push(url);
    }

    public String goBack() {
        if (history.isEmpty()) {
            System.out.println("History is empty!");
            return null;
        }
        String lastPage = history.pop();
        if (history.isEmpty()) {
            System.out.println("History is empty!");
            return null;
        }
        System.out.println("I go back to page: " + history.peek());
        return lastPage;
    }

    public String currentPage() {
        if (history.isEmpty()) {
            System.out.println("History is empty!");
            return null;
        }
        return history.peek();
    }

    public int getHistorySize() {
        return history.size();
    }

    public void printHistory() {
        for (String page : history) {
            System.out.println(page);
        }
    }
}
