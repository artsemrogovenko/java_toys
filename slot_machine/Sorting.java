
import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class Sorting extends Machine{

    public static void start() {
        // Пример PriorityQueue с компаратором
        LinkedList<Toy> temp = new LinkedList<Toy>();
        for (Toy i: stock) {
            temp.add(i);
        }
        LinkedList<Toy> list = new LinkedList<>(temp);
        if (list.size() > 0) {
            Queue<Toy> goods = new PriorityQueue<>(list.size(), fortuneComparator);
            // goods.clear();
            goods.addAll(list);
            raffle.clear();
            raffle.addAll(Shuffle.mix(goods));
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
            
}
