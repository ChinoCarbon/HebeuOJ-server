package org.chinocarbon.judgesystem.core;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class CmdClient
{
    // 中文有乱码的可以试着改一下编码格式。
    private static final Charset charset = StandardCharsets.UTF_8;
    // 初始化
    public CmdClient()
    {
        try
        {
            /*
              Runtime.getRuntime().exec()方法本质和windows上的运行一样，只能执行一个命令来启动一个程序。
              这里启动的是sh解释器，可以根据需要改成其他shell解释器。适用mac，linux系统。
              对于windows，则是运行C:\Windows\System32\cmd.exe，同时执行的也不是shell命令，而是dos命令。
             */
            Process process = Runtime.getRuntime().exec("/bin/sh");
            // 获取程序的输出流和输入流
            inputStream = process.getInputStream();
            errorStream = process.getErrorStream();
            outputStream = process.getOutputStream();
            /*
              输出数据需要，若执行的命令没有输出结果，就会阻塞读取流的线程。
              为了防止主线程被阻塞，所以声明新的线程专门盯着输出数据。
             */
            new Thread(this::ReadCmdOut).start();
            new Thread(this::ReadCmdError).start();
        }
        catch (IOException e)
        { throw new RuntimeException("cmd初始化异常", e); }
        System.out.println("控制台初始化完毕");
    }

    private final InputStream inputStream;
    private final InputStream errorStream;
    private final OutputStream outputStream;

    // 运行命令
    public void run(String cmd)
    {
        try
        {
            outputStream.write((cmd + "\n").getBytes(charset));
            outputStream.flush();
        }
        catch (Exception e)
        { throw new RuntimeException("命令执行错误：" + cmd, e); }
    }

    private void ReadCmdOut()
    {
        try
        { print(inputStream); }
        catch (Exception e)
        { throw new RuntimeException("获取输出流异常", e); }
        System.out.println("正常输出流结束");
    }

    private void ReadCmdError()
    {
        try
        { print(errorStream); }
        catch (Exception e)
        { throw new RuntimeException("获取错误流异常", e); }
        System.out.println("异常输出流结束");
    }

    /**
     * 这里直接把数据打印出来了，有其他需求的可以在这里写判来确定下一步来执行什么。
     */
    private void print(InputStream inputStream) throws IOException
    {
        byte[] bytes = new byte[1024];
        int i;
        while (true)
        {
            i = inputStream.read(bytes);
            if (i == -1) break;
            System.out.print(new String(bytes, 0, i, charset));
        }
        System.out.println();
    }
}
