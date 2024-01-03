package org.chinocarbon.judgesystem.Utils;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

/**
 * @author ChinoCarbon
 * @since 2022/3/2-9:12 PM
 */
public class EncodeUtils
{
    public static String encode(String init)
    {
        byte[] firstEncode = Base64.getEncoder().encode(init.getBytes(StandardCharsets.UTF_8));
        for(int i = 0; i < firstEncode.length; i ++)
        {
            firstEncode[i] = (byte) (127 - firstEncode[i]);
        }
        byte[] secondEncode = Base64.getEncoder().encode(firstEncode);
        return new String(secondEncode);
    }

    public static String decode(String s)
    {
        byte[] firstDecode = Base64.getDecoder().decode(s.getBytes(StandardCharsets.UTF_8));
        for(int i = 0; i < firstDecode.length; i ++)
        {
            firstDecode[i] = (byte) (127 - firstDecode[i]);
        }
        byte[] secondDecode = Base64.getDecoder().decode(firstDecode);

        return new String(secondDecode);
    }
}
