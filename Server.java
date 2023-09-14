import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Server {
    public static HashMap<String, ClientHandler> customerClient = new HashMap<>();
    public static HashMap<String, ClientHandler> restaurantClient = new HashMap<>();

    public static void main(String[] args) {
        RestaurantManager restaurantManager = new RestaurantManager();
        try {
            ServerSocket serverSocket = new ServerSocket(12345);
            System.out.println("Server is running on port 12345");
            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("New client connected");
                ClientHandler clientHandler = new ClientHandler(socket, restaurantManager);
                clientHandler.start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static synchronized void addCustomerHandler(String customerName, ClientHandler clientHandler) {
        customerClient.put(customerName, clientHandler);
    }

    public static synchronized void addRestaurantHandler(String restaurantName, ClientHandler clientHandler) {
        restaurantClient.put(restaurantName, clientHandler);
    }

    public static synchronized List<String> allRestaurants() {
        System.out.println("Inside allRestaurants");
        List<String> allRestaurants = new ArrayList<>();
        for (Map.Entry<String, ClientHandler> restaurant : restaurantClient.entrySet()) {
            allRestaurants.add(restaurant.getKey());
            System.out.println(restaurant.getKey());
        }
        return allRestaurants;
    }
}