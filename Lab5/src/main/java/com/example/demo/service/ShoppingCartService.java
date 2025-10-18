package com.example.demo.service;

import java.util.Collection;
import com.example.demo.model.Item;

public interface ShoppingCartService {
    /**
     * Thêm mặt hàng vào giỏ hoặc tăng số lượng nếu đã có
     */
    Item add(Integer id);

    /**
     * Xóa mặt hàng khỏi giỏ
     */
    void remove(Integer id);

    /**
     * Cập nhật số lượng mặt hàng trong giỏ
     */
    Item update(Integer id, int qty);

    /**
     * Xóa toàn bộ giỏ hàng
     */
    void clear();

    /**
     * Lấy danh sách tất cả mặt hàng
     */
    Collection<Item> getItems();

    /**
     * Lấy tổng số lượng mặt hàng
     */
    int getCount();

    /**
     * Lấy tổng tiền của giỏ hàng
     */
    double getAmount();
}
