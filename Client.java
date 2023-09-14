import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        String hostName = "localhost";
        int portNumber = 12345;
        try {
            Socket socket = new Socket(hostName, portNumber);
            RestaurantManager manager;
            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
            out.writeObject("Customer");
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter your name: ");
            String name = scanner.nextLine();
            out.writeObject(name);
            manager = (RestaurantManager) in.readObject();
            System.out.println("Restaurant Manager Object Received");

            while (true) {
                System.out.println("  Main Menu:");
                System.out.println("1) Search Restaurants:");
                System.out.println("2) Search Food Items:");
                System.out.println("3) Order Food:");
                System.out.println("4) Exit System:");

                int option = scanner.nextInt();

                if (option == 1) {
                    out.writeObject(1);
                    System.out.println("Restaurant Searching Options:");
                    System.out.println("1) By Name");
                    System.out.println("2) By Score");
                    System.out.println("3) By Category");
                    System.out.println("4) By Price");
                    System.out.println("5) By Zip Code");
                    System.out.println("6) Different Category Wise List of Restaurants:");
                    System.out.println("7) Back to Main Menu");
                    int option1 = scanner.nextInt();
                    scanner.nextLine();
                    if (option1 == 1) {
                        List<Restaurant> restaurant = new ArrayList<>();
                        String restaurantName;
                        System.out.println("Enter the name of the restaurant you want to search:");
                        restaurantName = scanner.nextLine();
                        restaurant = manager.RestaurantByName(restaurantName);
                        if (restaurant.size() == 0) {
                            System.out.println("No such restaurant with this name");
                        } else {
                            System.out.println("Restaurant with this name:");
                            for (int i = 0; i < restaurant.size(); i++) {
                                manager.PrintRestaurant(restaurant.get(i));
                            }
                        }
                    }

                    else if (option1 == 2) {
                        List<Restaurant> restaurant = new ArrayList<>();
                        double lower, upper;
                        System.out
                                .println(
                                        "Enter the lower range and the upper range of the restaurant you want to search:");
                        lower = scanner.nextDouble();
                        upper = scanner.nextDouble();
                        restaurant = manager.RestaurantByScore(lower, upper);
                        if (restaurant.size() == 0) {
                            System.out.println("No such restaurant in this range");
                        } else {
                            System.out.println("Restaurant in this range:");
                            for (int i = 0; i < restaurant.size(); i++) {
                                manager.PrintRestaurant(restaurant.get(i));
                            }
                        }

                    } else if (option1 == 3) {
                        List<Restaurant> restaurant = new ArrayList<>();
                        String categoryName;
                        System.out
                                .println(
                                        "Enter the lower range and the upper range of the restaurant you want to search:");
                        categoryName = scanner.nextLine();
                        restaurant = manager.RestaurantByCategory(categoryName);
                        if (restaurant.size() == 0) {
                            System.out.println("No such restaurant of this category");
                        } else {
                            System.out.println("Restaurants of this category:");
                            for (int i = 0; i < restaurant.size(); i++) {
                                manager.PrintRestaurant(restaurant.get(i));
                            }
                        }
                    }

                    else if (option1 == 4) {
                        List<Restaurant> restaurant = new ArrayList<>();
                        String price;
                        System.out.println("Enter price of the restaurant you want to search:");
                        price = scanner.nextLine();
                        restaurant = manager.RestaurantByPrice(price);
                        if (restaurant.size() == 0) {
                            System.out.println("No such restaurant with this Price");
                        } else {
                            System.out.println("Restaurants of this Price:");
                            for (int i = 0; i < restaurant.size(); i++) {
                                manager.PrintRestaurant(restaurant.get(i));
                            }
                        }
                    } else if (option1 == 5) {

                        List<Restaurant> restaurant = new ArrayList<>();
                        int ZipCode;
                        System.out.println("Enter price of the restaurant you want to search:");
                        ZipCode = scanner.nextInt();
                        restaurant = manager.RestaurantByZipCode(ZipCode);
                        if (restaurant.size() == 0) {
                            System.out.println("No such restaurant with this Price");
                        } else {
                            System.out.println("Restaurants of this Price:");
                            for (int i = 0; i < restaurant.size(); i++) {
                                manager.PrintRestaurant(restaurant.get(i));
                            }
                        }
                    } else if (option1 == 6) {
                        HashMap<String, List<String>> CategoryRestaurant = new HashMap<>();
                        CategoryRestaurant = manager.CategoryWise();

                        for (String key : CategoryRestaurant.keySet()) {
                            System.out.print(key + ": ");
                            for (int i = 0; i < CategoryRestaurant.get(key).size(); i++) {
                                System.out.println(CategoryRestaurant.get(key).get(i) + ", ");
                            }
                        }
                    }

                    else if (option1 == 7) {

                    } else {
                        System.out.println("Invalid Option");
                    }

                }

                else if (option == 2) {
                    out.writeObject(2);
                    System.out.println("Food Item Searching Options:");
                    System.out.println("1) By Name:");
                    System.out.println("2) By Name in a Given Restaurant:");
                    System.out.println("3) By Category:");
                    System.out.println("4) By Category in a Given Restaurant:");
                    System.out.println("5) By Price Range:");
                    System.out.println("6) By Price Range in a Given Restaurant:");
                    System.out.println("7) Costliest Food Item(s) on the Menu in a Given Restaurant:");
                    System.out.println("8) List of Restaurants and Total Food Item on the Menu");
                    System.out.println("9) Back to Main Menu");
                    int option2 = scanner.nextInt();
                    scanner.nextLine();
                    if (option2 == 1) {
                        String foodName;
                        System.out.println("Enter a food Item name:");
                        foodName = scanner.nextLine();
                        List<Food> food = new ArrayList<>();
                        food = manager.FoodByName(foodName);
                        if (food.size() == 0) {
                            System.out.println("No such food item with this name");
                        } else {
                            for (int i = 0; i < food.size(); i++) {
                                manager.PrintFood(food.get(i));
                            }
                        }
                    } else if (option2 == 2) {
                        String FoodName, RestaurantName;
                        System.out.println("Enter a food Item name:");
                        FoodName = scanner.nextLine();
                        System.out.println("Enter a Restaurant name:");
                        RestaurantName = scanner.nextLine();
                        List<Food> food = new ArrayList<>();
                        food = manager.FoodByNameInRestaurant(FoodName, RestaurantName);
                        if (food.size() == 0) {
                            System.out.println("No such food item with this name in this restaurant");
                        } else {
                            for (int i = 0; i < food.size(); i++) {
                                manager.PrintFood(food.get(i));
                            }
                        }
                    } else if (option2 == 3) {
                        String category;
                        System.out.println("Enter a Category:");
                        category = scanner.nextLine();
                        List<Food> food = new ArrayList<>();
                        food = manager.FoodByCategory(category);
                        if (food.size() == 0) {
                            System.out.println("No such food item with this category");
                        } else {
                            for (int i = 0; i < food.size(); i++) {
                                manager.PrintFood(food.get(i));
                            }
                        }
                    }

                    else if (option2 == 4) {
                        String category, RestaurantName;
                        System.out.println("Enter a category:");
                        category = scanner.nextLine();
                        System.out.println("Enter a Restaurant name:");
                        RestaurantName = scanner.nextLine();
                        List<Food> food = new ArrayList<>();
                        food = manager.FoodByCategoryInRestaurant(category, RestaurantName);
                        if (food.size() == 0) {
                            System.out.println("No such food item in this category in this restaurant");
                        } else {
                            for (int i = 0; i < food.size(); i++) {
                                manager.PrintFood(food.get(i));
                            }
                        }
                    }

                    else if (option2 == 5) {
                        double upper, lower;
                        System.out.println("Enter the lower range:");
                        lower = scanner.nextDouble();
                        scanner.nextLine();
                        System.out.println("Enter the upper range:");
                        upper = scanner.nextDouble();
                        scanner.nextLine();
                        List<Food> food = new ArrayList<>();
                        food = manager.FoodByPriceRange(lower, upper);
                        if (food.size() == 0) {
                            System.out.println("No such food item in this price range");
                        } else {
                            for (int i = 0; i < food.size(); i++) {
                                manager.PrintFood(food.get(i));
                            }
                        }
                    } else if (option2 == 6) {
                        double upper, lower;
                        String RestaurantName;
                        System.out.println("Enter the lower range:");
                        lower = scanner.nextDouble();
                        scanner.nextLine();
                        System.out.println("Enter the upper range:");
                        upper = scanner.nextDouble();
                        scanner.nextLine();
                        System.out.println("Enter a restaurant name:");
                        RestaurantName = scanner.nextLine();
                        List<Food> food = new ArrayList<>();
                        food = manager.FoodByPriceRangeInRestaurant(lower, upper, RestaurantName);
                        if (food.size() == 0) {
                            System.out.println("No such food item in this price range");
                        } else {
                            for (int i = 0; i < food.size(); i++) {
                                manager.PrintFood(food.get(i));
                            }
                        }
                    } else if (option2 == 7) {
                        String RestaurantName;
                        System.out.println("Enter a Restaurant name:");
                        RestaurantName = scanner.nextLine();
                        List<Food> food = new ArrayList<>();
                        food = manager.CostliesItemInRestaurant(RestaurantName);
                        if (food.size() == 0) {
                            System.out.println("No such food item with this name in this restaurant");
                        } else {
                            for (int i = 0; i < food.size(); i++) {
                                manager.PrintFood(food.get(i));
                            }
                        }
                    } else if (option2 == 8) {
                        HashMap<String, Integer> temp = new HashMap<>();
                        temp = manager.RestaurantFoodCount();

                        for (String restaurantname : temp.keySet()) {
                            System.out.println(restaurantname + ": " + temp.get(restaurantname));
                        }
                    } else if (option2 == 9) {

                    } else {
                        System.out.println("Invalid Input");
                    }
                } else if (option == 3) {
                    System.out.println("Inside 3");
                    out.writeObject(3);
                    List<String> allRestaurants;
                    allRestaurants = (List<String>) in.readObject();
                    System.out.println("Received all restaurants");
                    while (true) {
                        System.out.println("Active Restaurants:");
                        HashMap<String, Integer> orders = new HashMap<>();
                        for (int i = 0; i < allRestaurants.size(); i++) {
                            System.out.println((i + 1) + ":" + allRestaurants.get(i));
                        }
                        System.out.println("Choose a restaurant:(-1 to exit)");
                        out.writeObject("Choosing restaurants");
                        int restaurantChoice = scanner.nextInt();
                        if (restaurantChoice == -1) {
                            out.writeObject(-1);
                            break;
                        } else if (restaurantChoice > allRestaurants.size()) {
                            System.out.println("Invalid Input");
                            continue;
                        } else {
                            out.writeObject(allRestaurants.get(restaurantChoice - 1));
                            while (true) {
                                out.writeObject("Ordering Food");
                                System.out.println("These are the foods availaible in the restaurant:");
                                int j = 0;
                                for (int i = 0; i < manager.menu.size(); i++) {
                                    if (manager.menu.get(i).restaurantName
                                            .equals(allRestaurants.get(restaurantChoice - 1))) {
                                        System.out.println((j + 1) + ": " + manager.menu.get(i).name);
                                        j++;
                                    }
                                }
                                System.out.println("Choose a food item:(-1 to exit)");
                                int foodChoice = scanner.nextInt();
                                scanner.nextLine();
                                if (foodChoice == -1) {
                                    break;
                                } else if (foodChoice > j) {
                                    System.out.println("Invalid Input");
                                    continue;
                                } else {
                                    System.out.println("Enter the quantity:");
                                    int quantity = scanner.nextInt();
                                    scanner.nextLine();
                                    if (quantity <= 0) {
                                        System.out.println("Invalid Input");
                                        continue;
                                    } else {
                                        String foodName = "";
                                        for (int i = 0; i < manager.menu.size(); i++) {
                                            if (manager.menu.get(i).restaurantName
                                                    .equals(allRestaurants.get(restaurantChoice - 1))) {
                                                foodName = manager.menu.get(i).name;
                                                break;
                                            }
                                        }
                                        orders.put(foodName, quantity);
                                        out.writeObject(orders);
                                        System.out.println("Order Placed");
                                    }

                                }
                            }
                        }
                    }

                } else if (option == 4) {
                    break;
                } else {
                    System.out.println("Invalid Input");
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
