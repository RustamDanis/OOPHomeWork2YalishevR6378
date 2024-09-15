import java.util.ArrayList;
import java.util.List;

interface QueueBehaviour {
    void takeInQueue(Human human);    // добавить человека в очередь

    void releaseFromQueue();          // удалить человека из очереди
}

// Интерфейс, описывающий поведение магазина
interface MarketBehaviour {
    void acceptOrder();               // принять заказ

    void handOutOrder();              // отдать заказ
}

// Класс, представляющий человека
class Human {
    String name;

    public Human(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}

// Класс Market, реализующий оба интерфейса
class Market implements QueueBehaviour, MarketBehaviour {
    private List<Human> queue; // очередь из людей
    private boolean hasOrder = false; // флаг наличия заказа

    public Market() {
        this.queue = new ArrayList<>();
    }

    // Добавление человека в очередь
    @Override
    public void takeInQueue(Human human) {
        queue.add(human);
        System.out.println(human + " вошел(ла) в очередь.");
    }

    // Удаление человека из очереди
    @Override
    public void releaseFromQueue() {
        if (!queue.isEmpty()) {
            Human human = queue.remove(0);
            System.out.println(human + " покинул(а) очередь.");
        } else {
            System.out.println("Очередь пуста.");
        }
    }

    // Принять заказ
    @Override
    public void acceptOrder() {
        if (!queue.isEmpty()) {
            hasOrder = true;
            System.out.println("Заказ принят у " + queue.get(0));
        } else {
            System.out.println("Очередь пуста, некому принимать заказ.");
        }
    }

    // Отдать заказ
    @Override
    public void handOutOrder() {
        if (hasOrder && !queue.isEmpty()) {
            System.out.println("Заказ отдан " + queue.get(0));
            hasOrder = false;
            releaseFromQueue();
        } else {
            System.out.println("Нет заказов для отдачи.");
        }
    }

    // Обновление состояния магазина
    public void update() {
        acceptOrder();
        handOutOrder();
    }

    public static void main(String[] args) {
        Market market = new Market();
        Human human1 = new Human("Алексей");
        Human human2 = new Human("Мария");

        market.takeInQueue(human1);    // Человек входит в очередь
        market.takeInQueue(human2);    // Еще один человек в очереди
        market.update();               // Обновление состояния: принимаем и отдаем заказы
        market.update();               // Повторное обновление состояния
    }
}