import com.google.gson.Gson;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        System.out.println(new Gson().toJson(new ArrayList<Integer>()));
    }
}
