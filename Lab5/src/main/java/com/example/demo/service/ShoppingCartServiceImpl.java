package com.example.demo.service;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import com.example.demo.model.DB;
import com.example.demo.model.Item;

@SessionScope
@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {

    // Dữ liệu giỏ hàng lưu theo id sản phẩm
    Map<Integer, Item> cart = new HashMap<>();

    @Override
    public Item add(Integer id) {
        Item item = DB.items.get(id); // Lấy thông tin sản phẩm từ DB giả lập
        if (item != null) {
            Item existing = cart.get(id);
            if (existing == null) {
                item.setQty(1);
                cart.put(id, item);
            } else {
                existing.setQty(existing.getQty() + 1);
            }
            return item;
        }
        return null;
    }

    @Override
    public void remove(Integer id) {
        cart.remove(id);
    }

    @Override
    public Item update(Integer id, int qty) {
        Item item = cart.get(id);
        if (item != null) {
            item.setQty(qty);
            if (qty <= 0) {
                cart.remove(id);
            }
        }
        return item;
    }

    @Override
    public void clear() {
        cart.clear();
    }

    @Override
    public Collection<Item> getItems() {
        return cart.values();
    }

    @Override
    public int getCount() {
        return cart.values().stream()
                .mapToInt(Item::getQty)
                .sum();
    }

    @Override
    public double getAmount() {
        return cart.values().stream()
                .mapToDouble(item -> item.getPrice() * item.getQty())
                .sum();
    }
}
