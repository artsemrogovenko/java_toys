import java.util.Scanner;

public class Main {
    
    
    public static void main(String[] args) {
        // (pq.poll());//получает наимеьший и удаляет
        // (pq.peek());//получает наименьший без удаления
        String toyslist = "./toysfile.txt";
        Scanner sc = new Scanner(System.in);
        boolean loop = true;
        while (loop) {
            System.out.println(
            "\nГлавное меню:\n" +
            "1 добавить игрушку в автомат\n" +
            "2 просмотр оставшихся игрушек\n" +
            "3 редактировать шанс игрушки\n" +
            "4 испытать удачу\n" +
            "6 удалить игрушку\n" +
            String.format("7 добавить игрушки из файла %s\n", toyslist )+ 
            "0 выход\n");
            switch (sc.nextLine()) {
                case "1": Admin.add_new(); break;
                case "2": Machine.show_stock();break;
                case "3": Admin.edit_fortune();break;
                case "4": Sorting.start();break;
                case "6": Admin.deleteToy();break;
                case "7": FilesOp.getBucket(toyslist);break;
                case "0": loop = false; break;
                default: System.out.println("такой команды еще нет");
            }

        }
         sc.close();
       }




}