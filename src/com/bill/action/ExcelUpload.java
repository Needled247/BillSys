package com.bill.action;

import com.bill.tool.InsertExlTool;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.PrintWriter;

/**
 * Created with IntelliJ IDEA.
 * User: HP
 * Date: 13-6-8
 * Time: 上午12:10
 * To change this template use File | Settings | File Templates.
 */
public class ExcelUpload extends ActionSupport {
    private File fileInput;
    private String fileInputFileName;
    private String InsArea;
    private String fileInputContentType;

    public String getFileInputFileName() {
        return fileInputFileName;
    }

    public void setFileInputFileName(String fileInputFileName) {
        this.fileInputFileName = fileInputFileName;
    }

    public String getFileInputContentType() {
        return fileInputContentType;
    }

    public void setFileInputContentType(String fileInputContentType) {
        this.fileInputContentType = fileInputContentType;
    }

    public File getFileInput() {
        return fileInput;
    }

    public void setFileInput(File fileInput) {
        this.fileInput = fileInput;
    }

    public String getInsArea() {
        return InsArea;
    }

    public void setInsArea(String insArea) {
        InsArea = insArea;
    }

    @Override
    public String execute() throws Exception {
        String realPath = ServletActionContext.getServletContext().getRealPath("/upload");
        String filePath = realPath+"\\"+fileInputFileName;
        HttpServletResponse response = ServletActionContext.getResponse();
        response.setContentType("text/html;charset=GBK");
        PrintWriter out = response.getWriter();
        if(fileInput !=null ){
            File destFile = new File(new File(realPath), fileInputFileName);//根据 parent 抽象路径名和 child 路径名字符串创建一个新 File 实例。
            if(!destFile.getParentFile().exists())//判断路径是否存在
                destFile.getParentFile().mkdirs();//如果不存在，则创建此路径
            //将文件保存到硬盘上，因为action运行结束后，临时文件就会自动被删除
            FileUtils.copyFile(fileInput, destFile);
            int insNum = new InsertExlTool().insertDB(filePath,InsArea);
            if(insNum>0){
                response.sendRedirect("NumManage.jsp?upload=ok");
            }
            else {
                response.sendRedirect("NumManage.jsp?upload=fail");
            }

        }
        return null;
    }
}
