package ru.geekbrains.junior.lesson1.task2;

import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Корзина
 * @param <T> Еда
 */
public class Cart<T extends Food> {

    //region Поля

    /**
     * Товары в магазине
     */
    private final ArrayList<T> foodstuffs;
    private final UMarket market;
    private final Class<T> clazz;

    //endregion

    //region Конструкторы

    /**
     * Создание нового экземпляра корзины
     * @param market принадлежность к магазину
     */
    public Cart(Class<T> clazz, UMarket market)
    {
        this.clazz = clazz;
        this.market = market;
        foodstuffs = new ArrayList<>();
    }


    //endregion
    public void cardBalancing2(){
        final boolean[] proteins = {false};
        final boolean[] fats = {false};
        final boolean[] carbohydrates = {false};

        foodstuffs.stream()
        .filter(f -> {
            if (!proteins[0] && f.getProteins())proteins[0] = true;
            else if (!fats[0] && f.getFats())fats[0] = true;
            else if (!carbohydrates[0] && f.getCarbohydrates())carbohydrates[0] = true;
            if (proteins[0] && fats[0] && carbohydrates[0]) return true;

            if (proteins[0] && fats[0] && carbohydrates[0]){
                System.out.println("Корзина уже сбалансирована по БЖУ.");
                return true;
            }

            return true;
        })
        .forEach(f -> {
                    if (!proteins[0] && f.getProteins()){
                        proteins[0] = true;
                        foodstuffs.add(f);
                    }
                    else if (!fats[0] && f.getFats()){
                        fats[0] = true;
                        foodstuffs.add(f);
                    }
                    else if (!carbohydrates[0] && f.getCarbohydrates()){
                        carbohydrates[0] = true;
                        foodstuffs.add(f);
                    }
                    if (proteins[0] && fats[0] && carbohydrates[0])
                        return;

            if (proteins[0] && fats[0] && carbohydrates[0])
                System.out.println("Корзина сбалансирована по БЖУ.");
            else
                System.out.println("Невозможно сбалансировать корзину по БЖУ.");
        });


    }


    /**
     * Балансировка корзины
     */
    public void cardBalancing()
    {
        boolean proteins = false;
        boolean fats = false;
        boolean carbohydrates = false;

        for (var food : foodstuffs)
        {
            if (!proteins && food.getProteins())
                proteins = true;
            else
            if (!fats && food.getFats())
                fats = true;
            else
            if (!carbohydrates && food.getCarbohydrates())
                carbohydrates = true;
            if (proteins && fats && carbohydrates)
                break;
        }

        if (proteins && fats && carbohydrates)
        {
            System.out.println("Корзина уже сбалансирована по БЖУ.");
            return;
        }

        for (var thing : market.getThings(clazz))
        {
            if (!proteins && thing.getProteins())
            {
                proteins = true;
                foodstuffs.add(thing);
            }
            else if (!fats && thing.getFats())
            {
                fats = true;
                foodstuffs.add(thing);
            }
            else if (!carbohydrates && thing.getCarbohydrates())
            {
                carbohydrates = true;
                foodstuffs.add(thing);
            }
            if (proteins && fats && carbohydrates)
                break;
        }

        if (proteins && fats && carbohydrates)
            System.out.println("Корзина сбалансирована по БЖУ.");
        else
            System.out.println("Невозможно сбалансировать корзину по БЖУ.");

    }

    public Collection<T> getFoodstuffs() {
        return foodstuffs;
    }

    /**
     * Распечатать список продуктов в корзине
     */
    public void printFoodstuffs(){
        /*int index = 1;
        for (var food : foodstuffs)
            System.out.printf("[%d] %s (Белки: %s Жиры: %s Углеводы: %s)\n", index++, food.getName(), food.getProteins() ? "Да" : "Нет",
                    food.getFats() ? "Да" : "Нет", food.getCarbohydrates() ? "Да" : "Нет");
         */
        AtomicInteger index = new AtomicInteger(1);
        foodstuffs.forEach(food -> System.out.printf("[%d] %s (Белки: %s Жиры: %s Углеводы: %s)\n",
                index.getAndIncrement(), food.getName(),
                food.getProteins() ? "Да" : "Нет",
                food.getFats() ? "Да" : "Нет",
                food.getCarbohydrates() ? "Да" : "Нет"));

    }

}
