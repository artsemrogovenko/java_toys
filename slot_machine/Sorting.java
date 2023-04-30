
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Date;

public class Sorting extends Machine{


    public static void start() {
        // Пример PriorityQueue с компаратором
        LinkedList<Toy> list = new LinkedList<>(stock);
        if (list.size() > 0) {
            Queue<Toy> goods = new PriorityQueue<>(list.size(), fortuneComparator);
            // goods.clear();
            goods.addAll(list);
            unpacking(goods);
        }else{
            System.out.println("автомат пуст");
        }
    }

    // Анонимный класс компаратора
    public static Comparator<Toy> fortuneComparator = new Comparator<Toy>() {
        @Override
        public int compare(Toy t1, Toy t2) {
            return (int) (t2.getWeight() - t1.getWeight());
        }
    };
    
    
    // получает из очереди
    private static void unpacking(Queue<Toy> list) {
        if (list.peek().getCount()>0) {
            Toy item = list.peek();
            if (item == null) {
                return;
                //break;
            }
                item.decrease();
                raffle.push(item.getName());
                FilesOp.exportFile("./history.log", getinfo(item));
                System.out.println("Вы получили " + item.getName());

                if (item.getCount() == 0) {
                    list.remove(item);
                    stock.remove(item);
                } 

            System.out.println("rafle="+raffle);
            System.out.println("list="+list);
        }
        else {
            list.poll();
            stock.poll();
        }
    }


    
    private static String getinfo(Toy item) {
        SimpleDateFormat formatDate = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        String date = formatDate.format(new Date());
        return String.format("%s удача=%s \t %s",item.getName(),item.getWeight(),date);
    }


}
