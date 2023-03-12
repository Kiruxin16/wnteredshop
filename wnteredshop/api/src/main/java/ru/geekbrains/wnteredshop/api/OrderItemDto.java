package ru.geekbrains.wnteredshop.api;


import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;

@Schema(description = "Модель продукта из списка заказа")
public class OrderItemDto {

    @Schema(description = "ID продукта ", required = true, example = "1")
    private Long id;


    @Schema(description = "Название продукта ", required = true, example = "Bread")
    private String productTitle;

    @Schema(description = "Имя заказа", required = true)
    private String orderTitle;

    @Schema(description = "Количество одинаковых продуктов", required = true, example = "3")
    private int quantity;

    @Schema(description = "Цена за продукт", required = true, example = "40.00")
    private BigDecimal pricePerProduct;

    @Schema(description = "Стоимость товаров одного наименования", required = true, example = "120.00")
    private BigDecimal price;
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProductTitle() {
        return productTitle;
    }

    public void setProductTitle(String productTitle) {
        this.productTitle = productTitle;
    }

    public String getOrderTitle() {
        return orderTitle;
    }

    public void setOrderTitle(String orderTitle) {
        this.orderTitle = orderTitle;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getPricePerProduct() {
        return pricePerProduct;
    }

    public void setPricePerProduct(BigDecimal pricePerProduct) {
        this.pricePerProduct = pricePerProduct;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }


}
