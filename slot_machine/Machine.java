import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;

public class Machine extends Toy {
    /* игрушки в наличии */
    protected static LinkedList<Toy> stock = new LinkedList<>();
    /* игрушки готовые к продаже */
    protected static LinkedList<String> raffle = new LinkedList<>();

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

        try {
            if (input[0].isEmpty()) {
                throw new Exception("пустая строка");
            }
            if (Integer.parseInt(data[2]) <= 0) {
                throw new Exception("количество должно быть больше нуля");
            }

            if (check_toy(data[0])) { // если такой игрушки еще нет
                stock.add(new Toy(data[0], Integer.parseInt(data[1]), Integer.parseInt(data[2])));
                System.out.println("успешно");

            } else {
                System.out.printf("%s игрушка уже есть\n", data[0]);
            }

        } catch (Exception e) {
            System.out.printf("-! Ошибка добавления %s !- \n", e.getMessage());
        }
    }

    /* начало игры */
    public static void startGame() {
        if (raffle.size() > 0) {
            unpacking(raffle);
        } else {
            System.out.println("автомат пуст");
        }
    }


    // получает из очереди
    private static void unpacking(LinkedList<String> list) {
        if (list.size() > 0) {
            // для отладки
            /* for (String s : list) {
                System.out.println(s);
            } */
            String name = list.getFirst();
            for (Toy toy : stock) {
                if (toy.getName().equals(name)) {
                    toy.decrease();
                    FilesOp.exportFile("./history.log", getinfo(toy));
                    System.out.println("Вы получили " + toy.getName());
                    list.removeFirst();
                    if (toy.getCount() == 0) {
                        stock.remove(toy);
                    }
                    break;
                }
            }
        } else {
            stock.poll();
        }
    }
    
    private static String getinfo(Toy item) {
        SimpleDateFormat formatDate = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        String date = formatDate.format(new Date());
        return String.format("id %d %s вероятность=%s \t %s",item.getId(),item.getName(),item.getWeight(),date);
    }


}
