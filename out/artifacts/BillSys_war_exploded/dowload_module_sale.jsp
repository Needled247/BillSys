<%@page language="java"     pageEncoding="gb2312"
        import="java.net.*,java.io.*"
        %>
<%
    //�����ļ�����ʱ�����ļ�������ķ�ʽ����
    //����response.reset()���������еģ�>���治Ҫ���У��������һ����

    response.reset();//���Լ�Ҳ���Բ���
    response.setContentType("application/x-download");
    String filedownload = "D://sale.xls";
    String filedisplay = "sale.xls";
    filedisplay = URLEncoder.encode(filedisplay,"UTF-8");
    response.addHeader("Content-Disposition","attachment;filename=" + filedisplay);

    OutputStream outp = null;
    FileInputStream in = null;
    try
    {
        outp = response.getOutputStream();
        in = new FileInputStream(filedownload);

        byte[] b = new byte[1024];
        int i = 0;

        while((i = in.read(b)) > 0)
        {
            outp.write(b, 0, i);
        }
        outp.flush();
    }
    catch(Exception e)
    {
        System.out.println("Error!");
        e.printStackTrace();
    }
    finally
    {
        if(in != null)
        {
            in.close();
            in = null;
        }
        if(outp != null)
        {
            outp.close();
            outp = null;
        }
    }
%>

