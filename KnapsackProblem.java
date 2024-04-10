import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class KnapsackProblem {
    public static void main(String[] args) {
        int itemsCount = 5;
        int maxBackpackWeight = 8;
        Item[] items = {
            new Item("Ананас", 3, 2),
            new Item("Фотоаппарат", 10, 5),
            new Item("Кепка", 5, 1),
            new Item("Книга", 4, 2),
            new Item("Гантель", 2, 6),
        };
        //  Промежуточные состояния рюкзака
        Backpack[][] tempBackpack = new Backpack[itemsCount + 1][maxBackpackWeight + 1];

        for (int i = 0; i < itemsCount + 1; i++) {
            for (int j = 0; j < maxBackpackWeight + 1; j++) {
                if (i == 0 || j == 0) {
                    tempBackpack[i][j] = new Backpack(new Item[]{}, 0);
                } else if (i == 1) {
                    // Кладем первый предмет, если влезает по весу
                    tempBackpack[1][j] = items[0].weight <= j ? new Backpack(new Item[]{items[0]}, items[0].price)
                        : new Backpack(new Item[]{}, 0);
                } else {
                    // Если предмет не влезает по весу, копируем максимум из ячейки сверху
                    if (items[i - 1].weight > j)
                        tempBackpack[i][j] = tempBackpack[i - 1][j];
                    else {
                        // Новая цена = цена предмета + цена для (максимальный вес предыдущего состояния рюкзака - вес предмета)
                        int newPrice = items[i - 1].price + tempBackpack[i - 1][j - items[i - 1].weight].price;
                        // Если предыдущий максимум цены больше новой, скопируем его
                        if (tempBackpack[i - 1][j].price > newPrice)
                            tempBackpack[i][j] = tempBackpack[i - 1][j];
                        else {
                            // Иначе новый набор вещей: массив из предмета + массив из вещей без его веса
                            tempBackpack[i][j] = new Backpack(
                                Stream.concat(
                                    Arrays.stream(new Item[]{items[i - 1]}),
                                    Arrays.stream(tempBackpack[i - 1][j - items[i - 1].weight].items)
                                ).toArray(Item[]::new),
                                newPrice);
                        }
                    }
                }
            }
        }

        for (int i = 1; i < itemsCount + 1; i++) {
            for (int j = 1; j < maxBackpackWeight + 1; j++) {
                System.out.print(tempBackpack[i][j].prettyPrint() + " ");
            }
            System.out.println();
        }
    }

    public static class Item {
        public final String name;
        public final int price;
        public final int weight;

        public Item(String name, int price, int weight) {
            this.name = name;
            this.price = price;
            this.weight = weight;
        }
    }

    public static class Backpack {
        public Item[] items;
        public int price;

        public Backpack(Item[] items, int price) {
            this.items = items;
            this.price = price;
        }

        public String prettyPrint() {
            return null != items ? Arrays.stream(items).map(i -> i.name).collect(Collectors.joining(" и ")) + ": " + this.price + ";" : "";
        }
    }
}
