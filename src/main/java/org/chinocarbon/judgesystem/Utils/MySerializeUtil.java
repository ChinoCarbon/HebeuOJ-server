package org.chinocarbon.judgesystem.Utils;

import java.io.*;

/**
 * @author ChinoCarbon
 * @since 2022/6/23-1:29 PM
 */
public class MySerializeUtil
{
    public static void mySerialize(Object obj, String fileName) throws IOException
    {
        OutputStream out = new FileOutputStream(fileName);
        ObjectOutputStream objOut = new ObjectOutputStream(out);
        objOut.writeObject(obj);
        objOut.close();
    }

    /**
     * 从指定文件中反序列化对象
     */
    public static Object myDeserialize(String fileName) throws IOException, ClassNotFoundException {
        InputStream in = new FileInputStream(fileName);
        ObjectInputStream objIn = new ObjectInputStream(in);
        Object obj = objIn.readObject();
        return obj;
    }
}
