package com.davidskeppstrom.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ResourceException extends Exception {
    private static final long serialVersionUID = 8651357067688984525L;

    private String resourceKey;

    private String errorCode;

    private String message;
}
