package edu.nju.ticketsystem.tools;


import java.util.Date;

public class GenerateLoginCode {
    private static GenerateLoginCode generateLoginCode = new GenerateLoginCode();

    public static GenerateLoginCode getInstance(){
        return generateLoginCode;
    }

    public String generateLoginCode(int num){
        String suffix = "";
        String loginCode = String.valueOf(num);
        for (int i=0;i<7-loginCode.length();i++){
            suffix += "0";
        }
        loginCode += suffix;
        return loginCode;
    }
}
