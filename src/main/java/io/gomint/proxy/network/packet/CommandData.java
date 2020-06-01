package io.gomint.proxy.network.packet;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

/**
 * @author geNAZt
 * @version 1.0
 */
@RequiredArgsConstructor
@Data
public class CommandData {

    private final String name;
    private final String description;
    private byte flags;
    private byte permission;
    private int aliasIndex = -1; // TODO: Unused due to 1.2 alias bug
    private List<List<Parameter>> parameters;

    @AllArgsConstructor
    @Data
    public static class Parameter {
        private String name;
        private int type;
        private boolean optional;
    }

}
