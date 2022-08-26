package com.example.demo.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author ssq
 * @date 2022/05/26 17:01
 */
@TableName("consumer_goods")
public class ConsumerGoods implements Serializable {
    private static final long serialVersionUID = 8703802516059126120L;
    private Long id;

    @TableField("item_name")
    private String itemName;

    @TableField("barcode")
    private String barcode;

    @TableField("category_name")
    private String categoryName;

    @TableField("sub_category")
    private String subCategory;

    @TableField("brand")
    private String brand;

    @TableField("spu_name")
    private String spuName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getSubCategory() {
        return subCategory;
    }

    public void setSubCategory(String subCategory) {
        this.subCategory = subCategory;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getSpuName() {
        return spuName;
    }

    public void setSpuName(String spuName) {
        this.spuName = spuName;
    }

}
