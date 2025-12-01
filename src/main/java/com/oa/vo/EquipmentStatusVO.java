package com.oa.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EquipmentStatusVO {
    private Integer validEquipmentNum;
    private Integer EquipmentNum;
}
