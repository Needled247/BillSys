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
 * Time: ����12:10
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
            File destFile = new File(new File(realPath), fileInputFileName);//���� parent ����·������ child ·�����ַ�������һ���� File ʵ����
            if(!destFile.getParentFile().exists())//�ж�·���Ƿ����
                destFile.getParentFile().mkdirs();//��������ڣ��򴴽���·��
            //���ļ����浽Ӳ���ϣ���Ϊaction���н�������ʱ�ļ��ͻ��Զ���ɾ��
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
