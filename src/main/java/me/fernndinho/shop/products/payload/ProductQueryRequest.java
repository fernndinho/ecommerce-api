package me.fernndinho.shop.products.payload;

import com.google.common.collect.Lists;
import lombok.Data;

import java.util.List;

@Data
public class ProductQueryRequest {
    private List<String> categories = Lists.newArrayList();
    private List<String> colors = Lists.newArrayList();
    private String orderBy = "slug";
    private boolean asc = true;
    private int page = 0;
}
