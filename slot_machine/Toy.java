public class Toy{
    
    private static int added;
    private int id;
    private String name;
    private int weight;
    private int count;

    public Toy() {
    }

    public Toy(String n, int w, int c) {
        added++;
        this.id = added;
        this.name = n;
        this.weight = w;
        this.count = c;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int f) {
        this.weight = f;
    }

    public int getCount() {
        return count;
    }
/* уменьшить количество игрушки */
    public void decrease() {
        if (this.count > 0) {
            this.count--;
        }
    }
    
  
    public String info() {
        return String.format("id:%d %s шанс:%d количество:%d", id, name, weight, count);
    }


}
