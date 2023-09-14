import java.util.ArrayList;
import java.util.List;

class Restaurant {
    int Id;
    String name;
    double score;
    String price;
    int ZipCode;
    List<String> categories = new ArrayList<>();

    public Restaurant() {

    }

    public Restaurant(int Id, String name, double score, String price, int ZipCode, List<String> categories) {
        this.Id = Id;
        this.name = name;
        this.score = score;
        this.price = price;
        this.ZipCode = ZipCode;
        this.categories = categories;
    }
}
