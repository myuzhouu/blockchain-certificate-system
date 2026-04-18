
package com.code.base;


import com.alibaba.fastjson.JSON;
import com.code.entity.Certificate_info;
import com.code.util.GlobalResult;
import com.code.utils.MyBlockchainObject;
import com.code.utils.MyBlockchainUtils;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.*;


@Controller
@RequestMapping("/blockchain")
@CrossOrigin("*") //允许跨域请求
public class BlockchainController {
    @Autowired   //注入对象
    public com.code.mapper.Certificate_infoMapper Certificate_infoMapper;

    @ResponseBody
    @RequestMapping("/join") //加入区块
    public Object apilist() {
        List<Certificate_info> degreesListA = Certificate_infoMapper.selectByMap(null);
        List<Certificate_info> degreesListB = new ArrayList<Certificate_info>();
        List<MyBlockchainObject> degreesObjects = MyBlockchainUtils.queryData(MyBlockchainUtils.dataFlag);
        for (MyBlockchainObject degreesObject : degreesObjects) {
            degreesListB.add(degreesObject.getResult());
        }
        // 移除 collection1 中存在于 collection2 的元素
        degreesListA.removeAll(degreesListB);
        for (Certificate_info degrees : degreesListA) {
            MyBlockchainObject obj2 = new MyBlockchainObject();
            obj2.setId(1);
            obj2.setName("user");
            obj2.setTimes("2025");
            String uuid = UUID.randomUUID().toString().replaceAll("-", "");
            obj2.setData(uuid);
            degrees.setUuid(uuid);
            obj2.setResult(degrees);
            MyBlockchainUtils.submitData(MyBlockchainUtils.dataFlag, JSON.toJSONString(obj2));
        }

        return GlobalResult.ok(degreesListA);
    }


    @ResponseBody
    @RequestMapping("/queryAll") //查询区块
    public GlobalResult queryAll(String keyword) {
        List<Certificate_info> resultList = new ArrayList<Certificate_info>();
        List<Certificate_info> degreesListB = new ArrayList<Certificate_info>();
        List<MyBlockchainObject> degreesObjects = MyBlockchainUtils.queryData(MyBlockchainUtils.dataFlag);
        for (MyBlockchainObject degreesObject : degreesObjects) {
            Certificate_info result = degreesObject.getResult();
            degreesListB.add(result);
        }
        Certificate_info drugInfo = new Certificate_info();
        drugInfo.setUuid("0");
        for (Certificate_info degrees : degreesListB) {
            String basePath = System.getProperty("user.dir") + "\\src\\main\\resources\\static\\uploads\\";
            String codeImg = degrees.getCert_num() + "_code.png";
            String QR_CODE_IMAGE_PATH = basePath + "/" + codeImg;
            String qrCodeContent = JSON.toJSONString(degrees);
            // 二维码宽度
            int width = 350;
            // 二维码高度
            int height = 350;
            generateQRCodeImage(qrCodeContent, width, height, QR_CODE_IMAGE_PATH);
            degrees.setCodeimg("/uploads/" + codeImg);
            drugInfo = degrees;
            resultList.add(degrees);
        }
        return GlobalResult.ok(resultList);
    }

    /**
     * 生成二维码图片工具类
     *
     * @param text
     * @param width
     * @param height
     * @param filePath
     * @throws WriterException
     * @throws IOException
     */
    private static void generateQRCodeImage(String text, int width, int height, String filePath) {
        try {
            QRCodeWriter qrCodeWriter = new QRCodeWriter();
            Map<EncodeHintType, Object> hints = new HashMap<>();
            // 设置纠错级别为高
            hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
            BitMatrix bitMatrix = null;
            bitMatrix = qrCodeWriter.encode(text, BarcodeFormat.QR_CODE, width, height, hints);
            Path path = FileSystems.getDefault().getPath(filePath);
            MatrixToImageWriter.writeToPath(bitMatrix, "PNG", path);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
