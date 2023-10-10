package com.book.shop.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DashValue {
    private Integer totalAccount;
    private Integer accountMonth;
    private Integer totalBook;
    private Integer bookMonth;
}
