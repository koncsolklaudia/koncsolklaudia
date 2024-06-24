package com.example.urlaliasertrial.dtos;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class AliasDTO {
    private Integer id;
    private String url;
    private String alias;
    private int hitCount;
}
