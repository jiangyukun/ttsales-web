package cn.ttsales.work.web.common.util;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Hashtable;
import java.util.Map;

import javax.imageio.ImageIO;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

public class QRCodeUtil {

	private static final int BIT_COLOR = 0xFF000000;
	private static final int BACKGROUND_COLOR = 0xFFFFFFFF;
	private static final int FRAME_COLOR = 0xFFDDDDDD;


	private static BitMatrix textToBitMatrix(String text, int width, int height)
			throws WriterException {
		Map<EncodeHintType, Object> hints = new Hashtable<EncodeHintType, Object>();
		// 内容所使用编码
		hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
		hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);  
		hints.put(EncodeHintType.MARGIN, 1); 
		BitMatrix bitMatrix = new MultiFormatWriter().encode(text,
				BarcodeFormat.QR_CODE, width, height, hints);
		return bitMatrix;
	}
    /** 
     * 把传入的原始图像按高度和宽度进行缩放，生成符合要求的图标 
     *  
     * @param srcImageFile 
     *            源文件地址 
     * @param height 
     *            目标高度 
     * @param width 
     *            目标宽度 
     * @param hasFiller 
     *            比例不对时是否需要补白：true为补白; false为不补白; 
     * @throws IOException 
     */ 
    private static BufferedImage scale(BufferedImage srcImage, int height,  
            int width, boolean hasFiller){  
        double ratio = 0.0; // 缩放比例  
 
        Image destImage = srcImage.getScaledInstance(width, height,  
                BufferedImage.SCALE_SMOOTH);  
        // 计算比例  
        if ((srcImage.getHeight() > height) || (srcImage.getWidth() > width)) {  
            if (srcImage.getHeight() > srcImage.getWidth()) {  
                ratio = (new Integer(height)).doubleValue()  
                        / srcImage.getHeight();  
            } else {  
                ratio = (new Integer(width)).doubleValue()  
                        / srcImage.getWidth();  
            }  
            AffineTransformOp op = new AffineTransformOp(  
                    AffineTransform.getScaleInstance(ratio, ratio), null);  
            destImage = op.filter(srcImage, null);  
        }  
        if (hasFiller) {// 补白  
            BufferedImage image = new BufferedImage(width, height,  
                    BufferedImage.TYPE_INT_RGB);  
            Graphics2D graphic = image.createGraphics();  
            graphic.setColor(new Color(BACKGROUND_COLOR));  
            graphic.fillRect(0, 0, width, height);  
            if (width == destImage.getWidth(null))  
                graphic.drawImage(destImage, 0,  
                        (height - destImage.getHeight(null)) / 2,  
                        destImage.getWidth(null), destImage.getHeight(null),  
                        new Color(BACKGROUND_COLOR), null);  
            else 
                graphic.drawImage(destImage,  
                        (width - destImage.getWidth(null)) / 2, 0,  
                        destImage.getWidth(null), destImage.getHeight(null),  
                        new Color(BACKGROUND_COLOR), null);  
            graphic.dispose();  
            destImage = image;  
        }  
        return (BufferedImage) destImage;  
    }
	
	
	public static BufferedImage toQRCode(String text, int width,  
            int height, BufferedImage srcImage) throws WriterException{  
		
        // 生成二维码  
        BitMatrix matrix = textToBitMatrix(text, width, height);  
   
		int image_size = width<height?width/5:height/5;
		int image_half_size = image_size/2;
		int frame_width = image_size/20;
        // 读取源图像  
        BufferedImage scaleImage = scale(srcImage, image_size,  
        		image_size, true);  
        int[][] srcPixels = new int[image_size][image_size];  
        for (int i = 0; i < scaleImage.getWidth(); i++) {  
            for (int j = 0; j < scaleImage.getHeight(); j++) {  
                srcPixels[i][j] = scaleImage.getRGB(i, j);  
            }  
        }  

        // 二维矩阵转为一维像素数组  
        int halfW = matrix.getWidth() / 2;  
        int halfH = matrix.getHeight() / 2;  
        int[] pixels = new int[width * height];  
   
        for (int y = 0; y < matrix.getHeight(); y++) {  
            for (int x = 0; x < matrix.getWidth(); x++) {  
                // 读取图片  
                if (x > halfW - image_half_size  
                        && x < halfW + image_half_size  
                        && y > halfH - image_half_size  
                        && y < halfH + image_half_size) {  
                    pixels[y * width + x] = srcPixels[x - halfW  
                            + image_half_size][y - halfH + image_half_size];  
                }   
                // 在图片四周形成边框  
                else if ((x > halfW - image_half_size - frame_width  
                        && x < halfW - image_half_size + frame_width  
                        && y > halfH - image_half_size - frame_width && y < halfH  
                        + image_half_size + frame_width)  
                        || (x > halfW + image_half_size - frame_width  
                                && x < halfW + image_half_size + frame_width  
                                && y > halfH - image_half_size - frame_width && y < halfH  
                                + image_half_size + frame_width)  
                        || (x > halfW - image_half_size - frame_width  
                                && x < halfW + image_half_size + frame_width  
                                && y > halfH - image_half_size - frame_width && y < halfH  
                                - image_half_size + frame_width)  
                        || (x > halfW - image_half_size - frame_width  
                                && x < halfW + image_half_size + frame_width  
                                && y > halfH + image_half_size - frame_width && y < halfH  
                                + image_half_size + frame_width)) {  
                    pixels[y * width + x] = FRAME_COLOR;  
                } else {  
                    // 此处可以修改二维码的颜色，可以分别制定二维码和背景的颜色；  
                    pixels[y * width + x] = matrix.get(x, y) ? BIT_COLOR 
                            : BACKGROUND_COLOR;  
                }  
            }  
        }  
   
        BufferedImage image = new BufferedImage(width, height,  
                BufferedImage.TYPE_INT_RGB);  
        image.getRaster().setDataElements(0, 0, width, height, pixels);  
   
        return image;  
    }  
   
  


	public static BufferedImage toQRCode(String text, int width, int height) throws WriterException {

		BitMatrix bitMatrix = textToBitMatrix(text, width, height);
		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				image.setRGB(x, y, bitMatrix.get(x, y) ? BIT_COLOR : BACKGROUND_COLOR);
			}
		}

		return image;
	}
	public static void toQRCode(String text, int width, int height,
			String format, File outputFile) throws IOException, WriterException {
		BufferedImage image = toQRCode(text, width, height);
		if (!ImageIO.write(image, format, outputFile)) {
			throw new IOException("Could not write an image of format "
					+ format + " to " + outputFile);
		}
	}

	public static void toQRCode(String text, int width, int height,
			String format, OutputStream outputStream) throws IOException,
			WriterException {
		BufferedImage image = toQRCode(text, width, height);
		if (!ImageIO.write(image, format, outputStream)) {
			throw new IOException("Could not write an image of format "
					+ format + " to outputStream");
		}
	}

	public static void toQRCode(String text, File outputFile)
			throws IOException, WriterException {
		toQRCode(text, 300, 300, "jpg", outputFile);
	}

	public static void toQRCode(String text, OutputStream outputStream)
			throws IOException, WriterException {
		toQRCode(text, 300, 300, "jpg", outputStream);
	}
	public static void toQRCode(String text, int width, int height, BufferedImage srcImage,
			String format, File outputFile) throws IOException, WriterException {
		BufferedImage image = toQRCode(text, width, height, srcImage);
		if (!ImageIO.write(image, format, outputFile)) {
			throw new IOException("Could not write an image of format "
					+ format + " to " + outputFile);
		}
	}

	public static void toQRCode(String text, int width, int height, BufferedImage srcImage,
			String format, OutputStream outputStream) throws IOException,
			WriterException {
		BufferedImage image = toQRCode(text, width, height, srcImage);
		if (!ImageIO.write(image, format, outputStream)) {
			throw new IOException("Could not write an image of format "
					+ format + " to outputStream");
		}
	}

	public static void toQRCode(String text, BufferedImage srcImage, File outputFile)
			throws IOException, WriterException {
		toQRCode(text, 300, 300, srcImage, "jpg", outputFile);
	}

	public static void toQRCode(String text, BufferedImage srcImage, OutputStream outputStream)
			throws IOException, WriterException {
		toQRCode(text, 300, 300, srcImage, "jpg", outputStream);
	}
	/**
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		String text1 = "Who are you? ";
		text1 +=text1+=text1+=text1+=text1+=text1+=text1;

		String text2 = "http://sas.xlcpk.com/SASWeb/store/reachedSignIn/init.do?storeId=2&userId=zhangmizhong";
		File outputFile1 = new File("D:" + File.separator + "QRCode_1.jpg");
		//QRCodeUtil.toQRCode(text1, outputFile1);
		File outputFile2 = new File("D:" + File.separator + "QRCode_2.jpg");
		
		
		
		
		File srcImgFile = new File("D:" + File.separator + "0.jpg");
 
        @SuppressWarnings("unused")
		BufferedImage srcImage = ImageIO.read(srcImgFile); 
        BufferedImage srcImage2 = new BufferedImage(1,1,BufferedImage.TYPE_INT_RGB);
        srcImage2.setRGB(0, 0, BACKGROUND_COLOR);
        QRCodeUtil.toQRCode(text2,1300,1300,srcImage2,"jpg",outputFile2);
        QRCodeUtil.toQRCode(text2,300,300,srcImage2 ,"jpg",outputFile1);
//        QRCodeUtil.toQRCode(text1,800,800,srcImage ,"jpg",outputFile1);

		
	}

}
