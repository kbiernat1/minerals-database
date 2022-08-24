package com.crud.minerals.specification;

import com.crud.minerals.repository.SearchOps;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class SearchCriteria {
    private String key;
    private SearchOps operation;
    private Object value;
}
