import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class Shuffle extends Sorting {
    private static Map<Integer, String> doubles = new LinkedHashMap<Integer, String>();

    public static ArrayList<String> mix(Queue<Toy> listForMix) {
        doubles.clear();
        unpacking(listForMix);
        return writeQueue();
    }

    private static void unpacking(Queue<Toy> list) {
        if (list.peek().getCount() > 0) {
            while(true){
            Toy item = list.poll();
            if (item == null) {
                return;
            }
            fillCount(item);}
        } else {
            list.poll();
        }
    }

    /* заполняет список шансами и названием игрушки */
    private static void fillCount(Toy item) {
        String name = item.getName() + ";";
        if (!doubles.containsKey(item.getWeight())) { // если шанс удачи не встречался в колекции doubles
            doubles.put(item.getWeight(), name);
        } else { // иначе дописать в
            name += doubles.get(item.getWeight());
            doubles.put(item.getWeight(), name);
        }
    }

    /* записывает очередь выпадения */
    private static ArrayList<String> writeQueue() {
        ArrayList<String> readyoffer = new ArrayList<>();//очередь на выдачу
        for (Map.Entry<Integer, String> item : doubles.entrySet()) {
            // System.out.printf("Key: %s Value: %s \n", item.getKey(), item.getValue());
            // удаляю последнюю точку с запятой
            String naming = item.getValue().substring(0, item.getValue().length() - 1);
            //если встретились с одинаковой вероятностью
            if (item.getKey() >= 2) {
                // преобзазую в массив имена
                String[] same = naming.split(";");
                LinkedList<String> buffer = new LinkedList<>();
                // добавляю в строку имена равно количеству на складе
                for (String s : same) {
                    buffer.addAll(countName(s));
                }
                // перемешиваю
                readyoffer.addAll(mixQueue(buffer.toArray(new String[0])));
            } else {
                // если с таким шансом одна игрушка
                readyoffer.addAll(countName(naming));
            }
        }
        return readyoffer;
    }

private static LinkedList<String> countName(String search){
    LinkedList<String> count = new LinkedList<>();
    for(Toy t : stock){
        if(t.getName().equals(search)){
            for (int index = 0; index < t.getCount(); index++){
                count.add(search);
            }
            break;
        }
    }      
    //String[] stringArray = count.toArray(new String[0]);
    //return stringArray;
    return count;
}

/* миксует порядок одинаковых по шансу игрушек */
    private static ArrayList<String> mixQueue(String[] array) {
        ArrayList<String> names = new ArrayList<>();
        Collections.addAll(names, array);
        Collections.shuffle(names);
        return names;
    }
}
