import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;

public class RestaurantManager {
    List<Restaurant> restaurants = new ArrayList<>();
    List<Food> menu = new ArrayList<>();
    List<String> restaurantCategory = new ArrayList<>();

    public RestaurantManager() {
        final String RESTAURANT_FILE_NAME = "restaurant.txt";
        final String MENU_FILE_NAME = "menu.txt";
        try {
            BufferedReader restaurant_buffer = new BufferedReader(new FileReader(RESTAURANT_FILE_NAME));
            while (true) {
                String restaurant_info = restaurant_buffer.readLine();
                if (restaurant_info == null)
                    break;
                String[] array = restaurant_info.split(",", -1);

                Restaurant restaurant = new Restaurant();
                restaurant.Id = Integer.parseInt(array[0]);
                restaurant.name = array[1];
                restaurant.score = Double.parseDouble(array[2]);
                restaurant.price = array[3];
                restaurant.ZipCode = Integer.parseInt((array[4]));

                if (array.length == 8) {
                    for (int i = 0; i < 3; i++) {
                        restaurant.categories.add(array[5 + i]);
                    }
                } else if (array.length == 7) {
                    for (int i = 0; i < 2; i++) {
                        restaurant.categories.add(array[5 + i]);
                    }
                } else if (array.length == 6) {
                    for (int i = 0; i < 1; i++) {
                        restaurant.categories.add(array[5 + i]);
                    }
                }
                int tempSize = restaurant.categories.size();
                int tempsize2 = restaurantCategory.size();
                if (tempsize2 != 3) {
                    for (int i = 0; i < tempSize; i++) {
                        for (int j = 0; j < tempsize2; j++) {
                            if (!restaurant.categories.get(i).equals(restaurantCategory.get(j))) {
                                restaurantCategory.add(restaurant.categories.get(i));
                            }
                        }
                    }
                }
                restaurants.add(restaurant);

            }
            restaurant_buffer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            BufferedReader menu_buffer = new BufferedReader(new FileReader(MENU_FILE_NAME));
            while (true) {
                String menu_info = menu_buffer.readLine();
                if (menu_info == null)
                    break;

                String[] menu_array = menu_info.split(",", -1);
                Food food = new Food();
                food.RestaurantId = Integer.parseInt(menu_array[0]);
                food.category = menu_array[1];
                food.name = menu_array[2];
                food.price = Double.parseDouble(menu_array[3]);
                menu.add(food);
            }
            menu_buffer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    List<Restaurant> RestaurantByName(String name) {
        List<Restaurant> temp = new ArrayList<>();
        int restaurant_count = restaurants.size();
        for (int i = 0; i < restaurant_count; i++) {
            if (restaurants.get(i).name.equalsIgnoreCase(name)) {
                temp.add(restaurants.get(i));
                return temp;
            }
        }
        return temp;
    }

    List<Restaurant> RestaurantByScore(double lowerRange, double upperRange) {
        List<Restaurant> temp = new ArrayList<>();
        int restaurant_count = restaurants.size();
        for (int i = 0; i < restaurant_count; i++) {
            if (restaurants.get(i).score >= lowerRange && restaurants.get(i).score <= upperRange) {
                temp.add(restaurants.get(i));
            }
        }
        return temp;
    }

    List<Restaurant> RestaurantByCategory(String category) {
        List<Restaurant> temp = new ArrayList<>();
        int restaurant_count = restaurants.size();
        for (int i = 0; i < restaurant_count; i++) {
            if (restaurants.get(i).name.equalsIgnoreCase(category)) {
                temp.add(restaurants.get(i));
            }
        }
        return temp;
    }

    List<Restaurant> RestaurantByPrice(String price) {
        List<Restaurant> temp = new ArrayList<>();
        int restaurant_count = restaurants.size();
        for (int i = 0; i < restaurant_count; i++) {
            if (restaurants.get(i).name.equalsIgnoreCase(price)) {
                temp.add(restaurants.get(i));
            }
        }
        return temp;
    }

    List<Restaurant> RestaurantByZipCode(int ZipCode) {
        List<Restaurant> temp = new ArrayList<>();
        int restaurant_count = restaurants.size();
        for (int i = 0; i < restaurant_count; i++) {
            if (restaurants.get(i).ZipCode == ZipCode) {
                temp.add(restaurants.get(i));
            }
        }
        return temp;
    }

    HashMap<String, List<String>> CategoryWise() {
        HashMap<String, List<String>> restaurantList = new HashMap<>();
        int categoryLength = restaurantCategory.size();
        int restaurantLength = restaurants.size();
        for (int i = 0; i < categoryLength; i++) {
            List<String> temp = new ArrayList<>();
            for (int j = 0; j < restaurantLength; j++) {
                for (int k = 0; k < restaurants.get(j).categories.size(); k++) {
                    if (restaurants.get(j).categories.get(k).equals(restaurantCategory.get(i))) {
                        temp.add(restaurants.get(j).name);
                    }
                }
            }
            restaurantList.put(restaurantCategory.get(i), temp);
        }
        return restaurantList;
    }

    List<Food> FoodByName(String name) {
        List<Food> temp = new ArrayList<>();
        for (int i = 0; i < menu.size(); i++) {
            if (menu.get(i).name.equals(name)) {
                temp.add(menu.get(i));
            }
        }
        return temp;
    }

    List<Food> FoodByNameInRestaurant(String FoodName, String RestaurantName) {
        List<Food> temp = new ArrayList<>();
        for (int i = 0; i < restaurants.size(); i++) {
            if (restaurants.get(i).name.equals(RestaurantName)) {
                for (int j = 0; i < menu.size(); j++) {
                    if (menu.get(j).name.equals(FoodName)) {
                        temp.add(menu.get(i));
                    }
                }
            }
        }
        return temp;
    }

    List<Food> FoodByCategory(String category) {
        List<Food> temp = new ArrayList<>();
        for (int i = 0; i < menu.size(); i++) {
            if (menu.get(i).category.equals(category)) {
                temp.add(menu.get(i));
            }
        }
        return temp;
    }

    List<Food> FoodByCategoryInRestaurant(String Category, String RestaurantName) {
        List<Food> temp = new ArrayList<>();
        int Id = -1;
        for (int i = 0; i < restaurants.size(); i++) {
            if (restaurants.get(i).name.equals(RestaurantName)) {
                Id = restaurants.get(i).Id;
            }
        }
        for (int i = 0; i < menu.size(); i++) {
            if (menu.get(i).RestaurantId == Id) {
                if (menu.get(i).category.equals(Category)) {
                    temp.add(menu.get(i));
                }
            }
        }
        return temp;
    }

    List<Food> FoodByPriceRange(double lower, double upper) {
        List<Food> temp = new ArrayList<>();
        for (int i = 0; i < menu.size(); i++) {
            if ((menu.get(i).price >= lower) && (menu.get(i).price <= upper)) {
                temp.add(menu.get(i));
            }
        }
        return temp;
    }

    List<Food> FoodByPriceRangeInRestaurant(double lower, double upper, String RestaurantName) {
        List<Food> temp = new ArrayList<>();
        for (int i = 0; i < restaurants.size(); i++) {
            if (restaurants.get(i).name.equals(RestaurantName)) {
                for (int j = 0; i < menu.size(); j++) {
                    if ((menu.get(j).price >= lower) && (menu.get(j).price <= lower)) {
                        temp.add(menu.get(i));
                    }
                }
            }
        }
        return temp;
    }

    List<Food> CostliesItemInRestaurant(String RestaurantName) {
        List<Food> temp = new ArrayList<>();
        int Id = -1;
        for (int i = 0; i < restaurants.size(); i++) {
            if (restaurants.get(i).name.equals(RestaurantName)) {
                Id = restaurants.get(i).Id;
            }
        }
        double costly = -1;
        for (int i = 0; i < menu.size(); i++) {
            if (menu.get(i).RestaurantId == Id) {
                if (menu.get(i).price > costly)
                    costly = menu.get(i).price;
            }
        }

        for (int i = 0; i < menu.size(); i++) {
            if (menu.get(i).RestaurantId == Id) {
                if (menu.get(i).price == costly) {
                    temp.add(menu.get(i));
                }
            }
        }
        return temp;
    }

    HashMap<String, Integer> RestaurantFoodCount() {
        HashMap<String, Integer> temp = new HashMap<>();
        for (int i = 0; i < restaurants.size(); i++) {
            int count = 0;
            for (int j = 0; j < menu.size(); j++) {
                if (restaurants.get(i).Id == menu.get(j).RestaurantId) {
                    count++;
                }
            }
            temp.put(restaurants.get(i).name, count);

        }
        return temp;
    }

    void AddRestaurant(int Id, String Name, double Score, String Price, int ZipCode, List<String> categories) {
        Restaurant restaurant = new Restaurant(Id, Name, Score, Price, ZipCode, categories);
        restaurants.add(restaurant);
    }

    void AddFood(int RestaurantId, String Category, String Name, double Price) {
        Food food = new Food(RestaurantId, Category, Name, Price);
        menu.add(food);
    }

    public void PrintRestaurant(Restaurant restaurant) {
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

    public void PrintFood(Food food) {
        System.out.println("Food Name: " + food.name);
        System.out.println("Restaurant Id: " + food.RestaurantId);
        System.out.println("Food Price: " + food.price);
        System.out.println("Food Category: " + food.category);
    }

}