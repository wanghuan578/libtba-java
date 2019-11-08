package com.spirit;

import com.spirit.tba.tools.TbaBase64Utils;
import com.spirit.tba.tools.TbaRsaUtils;
import org.junit.Before;
import org.junit.Test;
import java.util.Map;


public class TbaRsaUtilsTest
{
    private String publicKey;
    private String privateKey;

    @Before
    public void init() {
        try {
            Map<String, Object> keyMap = TbaRsaUtils.genKeyPair();

            publicKey = TbaRsaUtils.getPublicKey(keyMap);
            privateKey = TbaRsaUtils.getPrivateKey(keyMap);

            System.out.println("公钥：" + publicKey);
            System.out.println("私钥：" + privateKey);
        }
        catch (Exception e) {

        }
    }

    @Test
    public void rsaTest()
    {

        try {

            System.out.println("\n密钥对构造完毕,甲方将公钥公布给乙方，开始进行加密数据的传输\n");
            String str = "甲向已数据流方向测试";
            System.out.println("数据原文: " + str);

            byte[] code1 = TbaRsaUtils.encryptByPrivateKey(str.getBytes(), privateKey);
            System.out.println("甲方使用私钥加密数据：\n[" + TbaBase64Utils.encode(code1) + "]");

            byte[] decode1 = TbaRsaUtils.decryptByPublicKey(code1, publicKey);
            System.out.println("\n乙方使用甲方提供的公钥对数据进行解密数据：\n[" + new String(decode1) + "]");

            System.out.println("\n<=====================================================================\n");

            str = "已向甲数据流方向测试";
            System.out.println("数据原文:" + str);

            byte[] code2 = TbaRsaUtils.encryptByPublicKey(str.getBytes(), publicKey);
            System.out.println("\n乙方使用公钥对数据进行加密数据：\n[" + TbaBase64Utils.encode(code2) + "]");

            byte[] decode2 = TbaRsaUtils.decryptByPrivateKey(code2, privateKey);
            System.out.println("\n甲方使用私钥对数据进行解密数据：\n[" + new String(decode2) + "]");
        }
        catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
        }
    }

    @Test
    public void digitalSignatureTest() {

        try {

            String str = "甲的通讯内容";
            byte[] code1 = TbaRsaUtils.encryptByPrivateKey(str.getBytes(), privateKey);
            String digitalSignature = TbaRsaUtils.signature(code1, privateKey);
            System.out.println("甲发布数字签名： \n" + digitalSignature);
            System.out.println("");
            if (TbaRsaUtils.verify(code1, publicKey, digitalSignature)) {
                System.out.println("签名被确认！");
            }
            else {
                System.out.println("签名被拒绝");
            }
        }
        catch (Exception e) {

        }
    }
}
