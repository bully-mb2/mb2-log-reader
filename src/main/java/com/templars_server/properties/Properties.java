package com.templars_server.properties;

import java.util.*;
import java.util.stream.Collectors;

public class Properties extends java.util.Properties {

    @Override
    public synchronized Set<Map.Entry<Object, Object>> entrySet() {
        return Collections.synchronizedSet(
                super.entrySet()
                        .stream()
                        .sorted(Comparator.comparing(e -> e.getKey().toString()))
                        .collect(Collectors.toCollection(LinkedHashSet::new)));
    }

}
