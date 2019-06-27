package cn.edu.sjtu.sip_server.controller;

import cn.edu.sjtu.sip_server.constant.Const;
import cn.edu.sjtu.sip_server.util.ImageUtil;
import cn.edu.sjtu.sip_server.util.TResult;
import cn.edu.sjtu.sip_server.util.TResultCode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/image")
@Slf4j
@Api("图片管理")
public class ImageController {
    /**
     * 上传图片
     *
     * @param file
     * @return
     */
    @PostMapping
    @ApiOperation("图片上传")
    public TResult add(@RequestParam("image") @ApiParam(value = "图片", required = true) MultipartFile file) {
        String imagePath = ImageUtil.saveImage(file);
        boolean result = (imagePath != null);
        TResult t = new TResult();
        if (result) {
            t.setSuccess(imagePath);
        } else {
            t.setFailure(TResultCode.BUSINESS_ERROR);
        }
        return t;
    }

    /**
     * 获取图片
     *
     * @param imgName
     * @param type
     * @return
     */
    @GetMapping("/picture/{imgName}.{type}")
    @ApiOperation("图片获取")
    public ResponseEntity getImage(@PathVariable(value = "imgName", required = true) String imgName,
                                   @PathVariable(value = "type", required = true) String type) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_JPEG);
        String filePath = ImageUtil.getResourcePath() + Const.IMAGE_PATH + imgName + "." + type;
        log.debug("The filePath:" + filePath);
        byte[] b = ImageUtil.getBytes(filePath);
        ResponseEntity e = null;
        if (b == null) {
            log.info(imgName + "." + type + " file not exits");
            e = ResponseEntity.badRequest()
                    .body(imgName + "." + type + " file not found!");
        } else {
            e = new ResponseEntity(b, headers, HttpStatus.CREATED);
        }
        return e;
    }
}