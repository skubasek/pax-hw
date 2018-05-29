package com.sk.hw_2.price;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

/**
 */
public class PairFinder {
    private static class ItemPrice {
        String item;
        long price;

        public ItemPrice(String item, long price) {
            this.item = item;
            this.price = price;
        }

        @Override
        public String toString() {
            return "ItemPrice{" +
                    "item='" + item + '\'' +
                    ", price=" + price +
                    '}';
        }
    }
    private static class FileReader {
        private List<ItemPrice> getItemsFrom(String fileName) {

            List<ItemPrice> items = new ArrayList<>();
            try {
                Path path = Paths.get(getClass().getClassLoader().getResource(fileName).toURI());
                Stream<String> lines = Files.lines(path);
                lines.forEach(
                        line -> {
                            String[] split = line.split(", ");
                            items.add(new ItemPrice(split[0], Long.valueOf(split[1])));
                        }
                );

                lines.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return items;
        }
    }

    private static class PriceMatcher {
        private List<ItemPrice> items;

        public PriceMatcher(List<ItemPrice> items) {
            this.items = items;
        }

        public List<ItemPrice> findPair(long cardBalance) {
            List<ItemPrice> matchingPairs = new ArrayList<>();
            for (int i = items.size() - 1; i >= 1; i--) {
                ItemPrice item = items.get(i);
                if (item.price > cardBalance) {
                    continue;
                }
                for (int j = i - 1; j >= 0; j--) {
                    ItemPrice secondItem = items.get(j);
                    long total = item.price + secondItem.price;
                    if (total <= cardBalance) {
                        matchingPairs.add(secondItem);
                        matchingPairs.add(item);
                        return matchingPairs;
                    }
                }
            }
            return matchingPairs;
        }
    }

    public static void main(String[] args) {
        List<ItemPrice> items = new FileReader().getItemsFrom("prices.txt");
        List<ItemPrice> pair = new PriceMatcher(items).findPair(Long.valueOf(args[0]));
        if (pair.isEmpty()) {
            System.out.println("Not possible");
        } else {
            System.out.print(pair.get(0).item + " " +  pair.get(0).price + ", ");
            System.out.print(pair.get(1).item + " " + pair.get(1).price + "\n");
        }
        
    }
}
