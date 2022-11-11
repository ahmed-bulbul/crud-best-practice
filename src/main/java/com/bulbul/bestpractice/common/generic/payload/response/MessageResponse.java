package com.bulbul.bestpractice.common.generic.payload.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MessageResponse {
    Long id;
    Set<Long> ids;
    private String message;

    public MessageResponse(String message) {
        this.message = message;
    }

    public MessageResponse(String message, Long id) {
        this.message = message;
        this.id = id;
    }

    public MessageResponse(String message, Set<Long> ids) {
        this.message = message;
        this.ids = ids;
    }
}
