import java.io.*;
import java.util.*;
import java.util.zip.*;
public class zip1 {

    //gzip

    private String zipFileName;      // 目的地Zip文件
    private String sourceFileName;   //源文件（带压缩的文件或文件夹）

    public zip1(String zipFileName,String sourceFileName)
    {
        this.zipFileName=zipFileName;
        this.sourceFileName=sourceFileName;
    }

    public void zip() throws Exception
    {
        //File zipFile = new File(zipFileName);
        System.out.println("processing...");

        //创建zip输出流
        ZipOutputStream out = new ZipOutputStream( new FileOutputStream(zipFileName));

        //创建缓冲输出流
        BufferedOutputStream bos = new BufferedOutputStream(out);

        File sourceFile = new File(sourceFileName);

        //调用函数
        compress(out,bos,sourceFile,sourceFile.getName());

        bos.close();
        out.close();
        System.out.println("finished");

    }

    public void compress(ZipOutputStream out,BufferedOutputStream bos,File sourceFile,String base) throws Exception
    {

        if(sourceFile.isDirectory())
        {


            File[] flist = sourceFile.listFiles();

            if(flist.length==0)
            {
                System.out.println(base+"/");
                out.putNextEntry(  new ZipEntry(base+"/") );
            }
            else
            {
                for(int i=0;i<flist.length;i++)
                {
                    compress(out,bos,flist[i],base+"/"+flist[i].getName());
                }
            }
        }
        else
        {
            out.putNextEntry( new ZipEntry(base) );
            FileInputStream fos = new FileInputStream(sourceFile);
            BufferedInputStream bis = new BufferedInputStream(fos);

            int tag;
            System.out.println(base);

            while((tag=bis.read())!=-1)
            {
                bos.write(tag);
            }
            bis.close();
            fos.close();

        }
    }
}
public class TestZip
{

    public static void main(String[] args)
    {
        zip1 zipCom = new zip1("D:\\电影.zip","F:\\电影");
        try
        {
            zipCom.zip();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

}
