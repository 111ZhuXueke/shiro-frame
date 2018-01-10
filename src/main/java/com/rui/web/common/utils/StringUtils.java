package com.rui.web.common.utils;

/**
 * Stirng util类
 * @author : zhuxueke
 * @since : 2017-12-26 19:53
 **/
public class StringUtils {

    /**
     * 替换模板中的占位符
     * @author : zhuxueke
     * @since : 2017/12/26 19:53
     */
    private static String getNewContent(String content,String[] target,Object[] replaceValue){
        for (int i = 0; i < target.length; i++) {
            String targetName = "${" + target[i] + "}";
            if(content.indexOf(targetName) != -1){
                targetName = "\\$\\{" + target[i] + "\\}";
                String newContent = content.replaceAll(targetName,replaceValue[i].toString());
                return newContent;
            }
        }
        return content;
    }

    public static void main(String[] args){
        String str = "尊敬的${agreement.borrowerBeneficiary}您好，您于${loanDate}的借款金额${principal}元已全款结清。如有任何问题可拨打客服热线400-860-8858 转人工咨询。【重信金融】";
        System.out.println(getNewContent(str,new String[]{"principal"},new Object[]{"啦啦啦啦绿绿绿绿"}));
    }
}
