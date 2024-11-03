package com.livrini.restaurant.dto;

import com.livrini.restaurant.entity.Status;
import lombok.Data;

import java.util.Date;

@Data
public class CommandeDTO {
    private Date date;
    private Status status;
    private Long userId;
    private Long menuId;
}
