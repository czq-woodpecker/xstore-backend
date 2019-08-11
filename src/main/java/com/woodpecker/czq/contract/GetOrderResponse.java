package com.woodpecker.czq.contract;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class GetOrderResponse {
    private long orderId;
    private String productName;
    private Double productPrice;
    private String productUnit;
    private int amount;
}
