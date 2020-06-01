package io.gomint.proxy.entity;

import lombok.Getter;

/**
 * @author geNAZt
 * @version 1.0
 */
public enum Attribute {

    // Base entity ones
    ABSORPTION( "minecraft:absorption", 0, Float.MAX_VALUE, 0 ),
    KNOCKBACK_RESISTANCE( "minecraft:knockback_resistance", 0, 1, 0 ),
    HEALTH( "minecraft:health", 0, 20, 20 ),
    MOVEMENT_SPEED( "minecraft:movement", 0, 1f, 0.1f ),
    FOLLOW_RANGE( "minecraft:follow_range", 0, 2048, 16 ),
    ATTACK_DAMAGE( "minecraft:attack_damage", 0, 10, 1 ),

    // Player based ones
    SATURATION( "minecraft:player.saturation", 0, 20, 5 ),
    EXHAUSTION( "minecraft:player.exhaustion", 0, 5, 0.41f ),
    HUNGER( "minecraft:player.hunger", 0, 20, 20 ),
    EXPERIENCE_LEVEL( "minecraft:player.level", 0, 24791, 0 ),
    EXPERIENCE( "minecraft:player.experience", 0, 1, 0 );

    @Getter
    private final String key;
    @Getter
    private final float minValue;
    @Getter
    private final float maxValue;
    private final float defaultValue;

    Attribute( String key, float minValue, float maxValue, float defaultValue ) {
        this.key = key;
        this.minValue = minValue;
        this.maxValue = maxValue;
        this.defaultValue = defaultValue;
    }

}
