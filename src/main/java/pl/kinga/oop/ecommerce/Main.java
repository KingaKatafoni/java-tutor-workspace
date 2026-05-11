package pl.kinga.oop.ecommerce;

public class Main {
    public static void main(String[] args) {
        ShoppingCart shoppingCart = new ShoppingCart();

        CartItem itemOne = new CartItem("Cheese", 32.99, 2);
        CartItem itemTwo = new CartItem("Ham", 12.99, 4);
        CartItem itemThree = new CartItem("Bread", 2.99, 1);

        shoppingCart.addItem(itemOne);
        shoppingCart.addItem(itemTwo);
        shoppingCart.addItem(itemThree);

        System.out.printf("Sum from calculateTotal(): %.2f%n", shoppingCart.calculateTotal());
        System.out.println(shoppingCart.toString());
        System.out.println(shoppingCart.getItems().add(itemOne));


    }
}
