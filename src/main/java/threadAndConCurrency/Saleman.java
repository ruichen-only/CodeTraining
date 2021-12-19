package threadAndConCurrency;

import java.util.ArrayList;
import java.util.List;

public class Saleman {
    private List<Product> products = new ArrayList<>();

    public synchronized void addProduct(Product product) {
        while (products.size() > 2) {
            System.out.println("货架已满，可以进行销售");
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        products.add(product);
        System.out.println("销售员添加第" + products.size() + "个产品");
        notifyAll();
    }

    public synchronized void removeProduct() {
        while (products.size() == 0) {
            System.out.println("当前货物已卖完，请等待上货");
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("顾客买第" + products.size() + "个产品");
        products.remove(products.size() - 1);
        notifyAll();
    }
}
