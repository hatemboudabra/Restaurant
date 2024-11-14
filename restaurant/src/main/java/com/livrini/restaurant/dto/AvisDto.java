package com.livrini.restaurant.dto;

import lombok.Data;

import java.util.Date;
@Data
public class AvisDto {
    private Long rating;
    private String comment;
    private Date date;
    private Long commandId;
    private Long menuId;

}
