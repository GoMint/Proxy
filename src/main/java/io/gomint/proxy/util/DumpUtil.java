/*
 * Copyright (c) 2017, GoMint, BlackyPaw and geNAZt
 *
 * This code is licensed under the BSD license found in the
 * LICENSE file in the root directory of this source tree.
 */

package io.gomint.proxy.util;

import com.google.common.base.Strings;
import io.gomint.jraknet.PacketBuffer;
import io.gomint.taglib.NBTTagCompound;

import java.util.List;
import java.util.Map;

/**
 * @author geNAZt
 * @version 1.0
 */
public class DumpUtil {

    public static void dumpPacketbuffer( PacketBuffer buffer ) {
        // Header


        StringBuilder lineBuilder = new StringBuilder();
        StringBuilder stringRepBuilder = new StringBuilder();
        while ( buffer.getRemaining() > 0 ) {
            for ( int i = 0; i < 32 && buffer.getRemaining() > 0; ++i ) {
                byte b = buffer.readByte();
                String hex = Integer.toHexString( ( (int) b ) & 0xFF );
                if ( hex.length() < 2 ) {
                    hex = "0" + hex;
                }

                stringRepBuilder.append( (char) (b & 0xFF) );
                lineBuilder.append( hex );
                if ( i + 1 < 32 && buffer.getRemaining() > 0 ) {
                    lineBuilder.append( " " );
                }
            }

            lineBuilder.append( " " ).append( stringRepBuilder );
            lineBuilder.append( "\n" );

            System.out.print( lineBuilder.toString() );
            lineBuilder = new StringBuilder();
            stringRepBuilder = new StringBuilder();
        }

        buffer.resetPosition();
    }

    public static void dumpByteArray( byte[] bytes, int skip ) {
        int count = 0;
        int overall = 0;

        StringBuilder stringBuilder = new StringBuilder( "0: ");
        StringBuilder ascii = new StringBuilder("");

        int hadBytes = 0;
        for ( byte aByte : bytes ) {
            String hex = Integer.toHexString( aByte & 255 );
            if ( hex.length() == 1 ) {
                hex = "0" + hex;
            }

            ascii.append(new String(new byte[]{aByte}));

            if ( hadBytes++ == skip ) {
                stringBuilder.delete( stringBuilder.length() - 1, stringBuilder.length() );
                stringBuilder.append( "[" ).append( hex ).append( "]" );
            } else {
                stringBuilder.append( hex ).append( " " );
            }

            overall++;
            if ( count++ == 32 ) {
                stringBuilder.append(" | ").append(ascii).append( "\n" ).append( overall ).append( ": " );
                ascii = new StringBuilder("");
                count = 0;
            }
        }

        System.out.println( stringBuilder );
    }

    public static void dumpByteArray( byte[] bytes ) {
        int count = 0;
        int overall = 0;
        StringBuilder stringBuilder = new StringBuilder( "0: ");

        for ( byte aByte : bytes ) {
            String hex = Integer.toHexString( aByte & 255 );
            if ( hex.length() == 1 ) {
                hex = "0" + hex;
            }

            stringBuilder.append( hex ).append( " " );
            overall++;
            if ( count++ == 16 ) {
                stringBuilder.append( "\n" ).append( overall ).append( ": " );
                count = 0;
            }
        }

        System.out.println( stringBuilder );
    }

    public static void dumpNBTCompund( NBTTagCompound compound ) {
        System.out.println( "COMPOUND START" );
        dumpNBTTag( compound, 0 );
        System.out.println( "COMPOUND END" );
    }

    private static void dumpNBTTag( NBTTagCompound entity, int depth ) {
        for ( Map.Entry<String, Object> stringObjectEntry : entity.entrySet() ) {
            Object obj = stringObjectEntry.getValue();
            if ( obj instanceof List ) {
                System.out.println( Strings.repeat( " ", depth * 2 ) + stringObjectEntry.getKey() + ": [" );

                List v = (List) obj;
                if ( v.size() > 0 ) {
                    System.out.println( Strings.repeat( " ", ( depth + 1 ) * 2 ) + "-----------" );
                }

                for ( Object o : v ) {
                    if ( o instanceof NBTTagCompound ) {
                        dumpNBTTag( (NBTTagCompound) o, depth + 1 );
                        System.out.println( Strings.repeat( " ", ( depth + 1 ) * 2 ) + "-----------" );
                    } else {
                        System.out.println( Strings.repeat( " ", ( depth + 1 ) * 2 ) + o );
                    }
                }

                if ( v.size() > 0 ) {
                    System.out.println( Strings.repeat( " ", ( depth + 1 ) * 2 ) + "-----------" );
                }

                System.out.println( Strings.repeat( " ", depth * 2 ) + "]" );
            } else if ( obj instanceof NBTTagCompound ) {
                System.out.println( Strings.repeat( " ", depth * 2 ) + stringObjectEntry.getKey() + ": " );
                dumpNBTTag( (NBTTagCompound) obj, depth + 1 );
            } else {
                System.out.println( Strings.repeat( " ", depth * 2 ) + stringObjectEntry.getKey() + ": " + obj + "(" + obj.getClass() + ")" );
            }
        }
    }
}
