import java.util.Scanner;

public class Admin extends Machine {
    
    private static Scanner admScanner = new Scanner(System.in);

    public static void deleteToy() {
        System.out.println("Введите имя игрушки");
        String nameToy = admScanner.nextLine();
        if (!check_toy(nameToy)) {
            stock.remove(selectToy(nameToy));
            System.out.println("игрушка удалена");
            Sorting.start();
        }
        else{
            System.out.println("такого имени нет");
        }
    }


    public static void edit_fortune() {
        try {
            System.out.println("Введите имя игрушки");
            String nameToy = admScanner.nextLine();
            if (!check_toy(nameToy)) { //если такой нет
                System.out.println("Введите шанс выпадения");
                selectToy(nameToy).setWeight(Integer.parseInt(admScanner.nextLine().replaceAll("\\s", "")));
                System.out.println("успешно");
                Sorting.start();
            } else {
                System.out.println("нет такой игрушки");
            }
        } catch (Exception e) {
            System.out.println("! Ошибка ввода !");
            // admScanner.close();
        }
    }
    

    public static void add_new() {
        System.out.println("Введите \"имя\" \"шанс выпадения в %\" \"количество шт.\"\nНапример: lego 20% 5 ");
        String[] input = admScanner.nextLine().split(" ");
        add_toy(input);
        if(stock.size()>0){
            Sorting.start();
        }
    }

}
