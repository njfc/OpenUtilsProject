package org.njfc.openutils;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RenameDir {
    static FileFilter dirFilter = new FileFilter() {
        @Override
        public boolean accept(File pathname) {
            return pathname.isDirectory();
        }
    };
    public static void main(String[] args) {
        String baseDir = "";
//        rename(new File(baseDir + "/xcom/backend/saas-service_comp"));
        rename2(new File(baseDir + "/SaaS/service/merchant-service_comp"));
        System.out.println("Finish!!!!!!!!!!!!");
    }

    static void rename(File dir){
        if(dir.getName().contains("xapp") || dir.getName().contains("xcom")){
            String newName = dir.getName().replace("xapp", "saas").replace("xcom", "saas");
            System.out.print("rename " + dir.getAbsolutePath() + "  -----> " + newName);
            File newFile = new File(dir.getParentFile().getAbsolutePath() + File.separator + newName);
            boolean rst = dir.renameTo(newFile);
            System.out.println(" ---> " + rst);
            if(rst) {
                dir = newFile;
            }
        }
        if(dir.isDirectory()){
            for(File sDir: dir.listFiles()){
                rename(sDir);
            }
        } else if (dir.getName().endsWith(".java")
                || dir.getName().endsWith(".xml")
                || dir.getName().endsWith(".yml")
                || dir.getName().endsWith(".html")
                || dir.getName().endsWith(".ftl")){
            try {
                String context = FileUtils.readFileToString(dir);
                if(context.contains("xcom") || context.contains("xapp")
                        || context.contains("XCOM") || context.contains("XAPP")) {
                    System.out.println("replace com/xapp in: " + dir.getAbsolutePath());
                    FileUtils.writeStringToFile(dir, context.replace("xcom", "saas")
                            .replace("xapp", "saas")
                            .replace("XCOM", "SAAS")
                            .replace("XAPP", "SAAS"), false);
                }
            } catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    static void rename2(File dir){
        if(dir.getName().contains("saas")){
            String newName = dir.getName().replace("saas", "merchant");
            System.out.print("rename " + dir.getAbsolutePath() + "  -----> " + newName);
            File newFile = new File(dir.getParentFile().getAbsolutePath() + File.separator + newName);
            boolean rst = dir.renameTo(newFile);
            System.out.println(" ---> " + rst);
            if(rst) {
                dir = newFile;
            }
        }
        if(dir.isDirectory()){
            for(File sDir: dir.listFiles()){
                rename2(sDir);
            }
        } else if (dir.getName().endsWith(".java")
                || dir.getName().endsWith(".xml")
                || dir.getName().endsWith(".yml")
                || dir.getName().endsWith(".html")
                || dir.getName().endsWith(".ftl")){
            try {
                String context = FileUtils.readFileToString(dir);
                if(context.contains("saas")
                        || context.contains("SAAS")) {
                    System.out.println("replace com/xapp in: " + dir.getAbsolutePath());
                    FileUtils.writeStringToFile(dir, context.replace("saas", "merchant")
                            .replace("SAAS", "MERCHANT"), false);
                }
            } catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
