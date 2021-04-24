package com.bok.bank.dto;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.Currency;

public class NewCardDTO {
    public String name;
    public String type;
    public Currency currency;
    public String label;

    public NewCardDTO() {
        /** JENKINS */
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("name", name)
                .append("type", type)
                .append("currency", currency)
                .append("label", label)
                .toString();
    }
}
