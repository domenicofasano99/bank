package com.bok.bank.integration.dto;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class CardDTO {
    public String name;
    public String type;
    public String label;

    public CardDTO() {
        /** JENKINS */
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("name", name)
                .append("type", type)
                .append("label", label)
                .toString();
    }
}
