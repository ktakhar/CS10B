public class Store {
  
  // instance fields (State = Instance)
  String productType;
  int inventoryCount;
  double inventoryPrice;
  
  // constructor method (Behavior = Method)
  public Store(String product, int count, double price) {
    productType = product;
    inventoryCount = count;
    inventoryPrice = price;
  }
  
  // main method
  public static void main(String[] args) {
    Store cookieShop = new Store("cookies", 12, 3.75);
    System.out.println("Product: " + cookieShop.productType);
    System.out.println("Inventory: " + cookieShop.inventoryCount);
    System.out.println("Count: " + cookieShop.inventoryPrice);
  }
}