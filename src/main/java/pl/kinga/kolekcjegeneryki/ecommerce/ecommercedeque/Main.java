package pl.kinga.kolekcjegeneryki.ecommerce.ecommercedeque;

public class Main {
    public static void main(String[] args) {
        BrowsingHistory browsingHistory = new BrowsingHistory();

        browsingHistory.visitPage("home");
        browsingHistory.visitPage("products");
        browsingHistory.visitPage("product/laptop");
        browsingHistory.visitPage("cart");
        browsingHistory.visitPage("checkout");

        System.out.println("Current page: " + browsingHistory.currentPage());
        browsingHistory.goBack();
        browsingHistory.goBack();

        System.out.println("Current page: " + browsingHistory.currentPage());
        System.out.println("-----Browsing History------");
        browsingHistory.printHistory();

        browsingHistory.goBack();
        browsingHistory.goBack();
        browsingHistory.goBack();
    }
}
