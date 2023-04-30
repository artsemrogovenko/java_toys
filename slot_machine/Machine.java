import java.util.LinkedList;
import java.util.Stack;

public class Machine extends Toy {
    /* игрушки в наличии */
    protected static LinkedList<Toy> stock = new LinkedList<>();
    /* игрушки готовые к продаже */
    protected static Stack<String> raffle = new Stack<>();

    /* проверка на наличие */
    public static boolean check_toy(String nameToy) {
        nameToy = nameToy.replaceAll("\\s", "");
        boolean nocontains = true;
        for (Toy toy : stock) { // проверка на имя игруши
            if (toy.getName().equals(nameToy)) {
                nocontains = false;
                break;
            }
        }
        return nocontains;
    }

    /* выбор игрушки по имени */
    public static Toy selectToy(String input) {
        input = input.replaceAll("\\s", "");
        for (Toy toy : stock) { // проверка на дубликат игруши
            if (toy.getName().equals(input)) {
                return toy;
            }
        }
        return new Toy();
    }

    public static void show_stock() {
        for (Toy toy : stock) {
            System.out.println(toy.info());
        }
    }

    /* добавление новой */
    public static void add_toy(String[] input) {
        String[] data = new String[3];
        int index = 0;
        for (String value : input) {
            if (!value.isEmpty()) {
                data[index] = value;
                index++;
            }
        }
        if (check_toy(data[0])) { // если такой игрушки еще нет
            try {
                stock.add(new Toy(data[0], Integer.parseInt(data[1]), Integer.parseInt(data[2])));
                System.out.println("успешно");
            } catch (Exception e) {
                System.out.println("-! Ошибка ввода !-");
            }
            
        } else {
            System.out.printf("%s игрушка уже есть\n", data[0]);
        }
    }
}
