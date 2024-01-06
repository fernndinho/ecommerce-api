package me.fernndinho.shop.shared.converters;

import com.google.common.collect.Lists;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import java.util.Arrays;
import java.util.List;

@Converter
public class StringListConverter implements AttributeConverter<List<String>, String> {
    @Override
    public String convertToDatabaseColumn(List<String> strings) {
        if(strings == null) return null;
        if(strings.isEmpty()) return "";

        StringBuilder sb = new StringBuilder();

        sb.append(strings.get(0));

        for(int i = 1; i < strings.size(); i++) {
            sb.append(",");
            sb.append(strings.get(i));
        }

        return sb.toString();
    }

    @Override
    public List<String> convertToEntityAttribute(String s) {
        if(s == null) return null;

        return Lists.newArrayList(s.split(","));
    }
}
