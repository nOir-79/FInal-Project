import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.HashMap;

public class Main {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        RestaurantManager manager = new RestaurantManager();
        while (true) {
            System.out.println("  Main Menu:");
            System.out.println("1) Search Restaurants:");
            System.out.println("2) Search Food Items:");
            System.out.println("3) Add Restaurant:");
            System.out.println("4) Add Food Item to the Menu:");
            System.out.println("5) Exit System:");

            int option = scanner.nextInt();

            if (option == 1) {
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
                    String name;
                    System.out.println("Enter the name of the restaurant you want to search:");
                    name = scanner.nextLine();
                    restaurant = manager.RestaurantByName(name);
                    if (restaurant.size() == 0) {
                        System.out.println("No such restaurant with this name");
                    } else {
                        System.out.println("Restaurant with this name:");
                        for (int i = 0; i < restaurant.size(); i++) {
                            PrintRestaurant(restaurant.get(i));
                        }
                    }
                }

                else if (option1 == 2) {
                    List<Restaurant> restaurant = new ArrayList<>();
                    double lower, upper;
                    System.out
                            .println("Enter the lower range and the upper range of the restaurant you want to search:");
                    lower = scanner.nextDouble();
                    upper = scanner.nextDouble();
                    restaurant = manager.RestaurantByScore(lower, upper);
                    if (restaurant.size() == 0) {
                        System.out.println("No such restaurant in this range");
                    } else {
                        System.out.println("Restaurant in this range:");
                        for (int i = 0; i < restaurant.size(); i++) {
                            PrintRestaurant(restaurant.get(i));
                        }
                    }

                } else if (option1 == 3) {
                    List<Restaurant> restaurant = new ArrayList<>();
                    String categoryName;
                    System.out
                            .println("Enter the lower range and the upper range of the restaurant you want to search:");
                    categoryName = scanner.nextLine();
                    restaurant = manager.RestaurantByCategory(categoryName);
                    if (restaurant.size() == 0) {
                        System.out.println("No such restaurant of this category");
                    } else {
                        System.out.println("Restaurants of this category:");
                        for (int i = 0; i < restaurant.size(); i++) {
                            PrintRestaurant(restaurant.get(i));
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
                            PrintRestaurant(restaurant.get(i));
                        }
                    }
                } else if (option == 5) {

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
                            PrintRestaurant(restaurant.get(i));
                        }
                    }
                } else if (option == 6) {
                    HashMap<String, List<String>> CategoryRestaurant = new HashMap<>();
                    CategoryRestaurant = manager.CategoryWise();

                    for (String key : CategoryRestaurant.keySet()) {
                        System.out.print(key + ": ");
                        for (int i = 0; i < CategoryRestaurant.get(key).size(); i++) {
                            System.out.println(CategoryRestaurant.get(key).get(i) + ", ");
                        }
                    }
                }

                else if (option == 7) {

                } else {
                    System.out.println("Invalid Option");
                }

            }

            else if (option == 2) {
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
                    String name;
                    System.out.println("Enter a food Item name:");
                    name = scanner.nextLine();
                    List<Food> food = new ArrayList<>();
                    food = manager.FoodByName(name);
                    if (food.size() == 0) {
                        System.out.println("No such food item with this name");
                    } else {
                        for (int i = 0; i < food.size(); i++) {
                            PrintFood(food.get(i));
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
                            PrintFood(food.get(i));
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
                            PrintFood(food.get(i));
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
                            PrintFood(food.get(i));
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
                            PrintFood(food.get(i));
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
                            PrintFood(food.get(i));
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
                            PrintFood(food.get(i));
                        }
                    }
                } else if (option2 == 8) {
                    HashMap<String, Integer> temp = new HashMap<>();
                    temp = manager.RestaurantFoodCount();

                    for (String name : temp.keySet()) {
                        System.out.println(name + ": " + temp.get(name));
                    }
                } else if (option == 9) {

                } else {
                    System.out.println("Invalid Input");
                }
            } else if (option == 3) {
                int Id, ZipCode;
                String Name, price;
                double Score;
                List<String> Category = new ArrayList<>();
                System.out.println("Enter the name of the Restaurant:");
                Name = scanner.nextLine();
                System.out.println("Enter the Id of the Restaurant:");
                Id = Integer.parseInt(scanner.nextLine());
                System.out.println("Enter the Score of the Restaurant:");
                Score = Double.parseDouble(scanner.nextLine());
                System.out.println("Enter the price of the Restaurant:");
                price = scanner.nextLine();
                System.out.println("Enter the ZipCode of the Restaurant:");
                ZipCode = Integer.parseInt(scanner.nextLine());
                while (true) {
                    System.out.println(
                            "How many categories does the Restaurant have?(atleast one and atmost 3 categories)");
                    int temp = Integer.parseInt(scanner.nextLine());
                    if (temp < 0 && temp > 3) {
                        System.out.println("Invalid number of Category.Try Again");
                    } else {
                        for (int i = 1; i <= temp; i++) {
                            String category;
                            System.out.println("Enter category(" + i + "):");
                            category = scanner.nextLine();
                            Category.add(category);
                        }
                        break;
                    }
                }
                manager.AddRestaurant(Id, Name, Score, price, ZipCode, Category);
            } else if (option == 4) {
                int Id;
                String Name, category;
                double price;
                System.out.println("Enter the name of the Food:");
                Name = scanner.nextLine();
                System.out.println("Enter the Id of the Restaurant:");
                Id = Integer.parseInt(scanner.nextLine());
                System.out.println("Enter the Price of the Food:");
                price = Double.parseDouble(scanner.nextLine());
                System.out.println("Enter the Category:");
                category = scanner.nextLine();
                manager.AddFood(Id, category, Name, price);
            } else if (option == 5) {
                break;
            }

        }
        scanner.close();
    }

    public static void PrintRestaurant(Restaurant restaurant) {
        System.out.println("Restaurant Name: " + restaurant.name);
        System.out.println("Restaurant Id: " + restaurant.Id);
        System.out.println("Restaurant Score: " + restaurant.score);
        System.out.println("Restaurant Price: " + restaurant.price);
        System.out.println("Restaurant Zip Code: " + restaurant.ZipCode);
        System.out.println("Restaurant Categories: ");
        for (int i = 0; i < restaurant.categories.size(); i++) {
            System.out.println((i + 1) + ". " + restaurant.categories.get(i));
        }
    }

    public static void PrintFood(Food food) {
        System.out.println("Food Name: " + food.name);
        System.out.println("Restaurant Id: " + food.RestaurantId);
        System.out.println("Food Price: " + food.price);
        System.out.println("Food Category: " + food.category);
    }
}
