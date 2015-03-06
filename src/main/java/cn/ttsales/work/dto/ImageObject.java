package cn.ttsales.work.dto;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Polygon;
import java.awt.geom.GeneralPath;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

public class ImageObject {
	public static final String FORMNAME_PNG = "png";
	public static final String FORMNAME_JPG = "jpg";
	private BufferedImage cImage;
	private Graphics g;
	private int width;
	private int height;
	
	public ImageObject() {
		width = 320;
		height = 500;
		cImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
	}

	public ImageObject(BufferedImage image) {
		this.cImage = image;
		this.width = image.getWidth();
		this.height = image.getHeight();
	}
	
	public ImageObject(String imgType, int width, int height) {
		if (!FORMNAME_JPG.equals(imgType) || !FORMNAME_PNG.equals(imgType)) {
			throw new IllegalArgumentException("该对象只支持jpg，png格式");
		}
		this.width = width;
		this.height = height;
		cImage = new BufferedImage(width, height, FORMNAME_JPG.equals(imgType) ? BufferedImage.TYPE_INT_BGR: BufferedImage.TYPE_INT_ARGB);
	}
	
	public void drawString(String words, Font font, Color color, int x, int y) {
		g = cImage.getGraphics();
		g.setFont(font);
		g.setColor(color);
		g.drawString(words, x, y);
		g.dispose();
	}
	
	public void drawBackground(Color color) {
		g = cImage.getGraphics();
		g.setColor(color);
		g.fillRect(0, 0, width, height);
		g.dispose();
	}
	
	public void mergeImage(InputStream input, int bx, int by, int wid, int hei) {
		g = cImage.getGraphics();
		try {
			g.drawImage(ImageIO.read(input), bx, by, wid, hei, null);
		} catch (IOException e) {
			e.printStackTrace();
		}
		g.dispose();
	}
	
	public void mergeImage(BufferedImage aimage, int bx, int by, int wid, int hei) {
		g = cImage.getGraphics();
		g.drawImage(aimage, bx, by, wid, hei, null);
		g.dispose();
	}
	
	public void mergeImage(String filePath, int bx, int by, int wid, int hei) throws FileNotFoundException {
		if (filePath.equals("")) {
			throw new IllegalArgumentException("文件名不能为空");
		}
		File nImage = new File(filePath);
		if (!nImage.exists()) {
			throw new FileNotFoundException("文件不存在");
		}

		g = cImage.getGraphics();
		try {
			g.drawImage(ImageIO.read(nImage), bx, by, wid, hei, null);
		} catch (IOException e) {
			e.printStackTrace();
		}
		g.dispose();
	}
	
	public BufferedImage scaleImage(float scaleRadio) {
		if (scaleRadio == 0) {
			throw new IllegalArgumentException ("scaleRadio应该大于1");
		}
		
	 	Image i = cImage.getScaledInstance((int)(cImage.getWidth() / scaleRadio) , (int)(cImage.getHeight() / scaleRadio), Image.SCALE_SMOOTH);
	 	BufferedImage bi = new BufferedImage((int)(cImage.getWidth() / scaleRadio), (int)(cImage.getHeight() / scaleRadio), BufferedImage.TYPE_INT_ARGB);
	 	bi.getGraphics().drawImage(i, 0, 0, (int)(cImage.getWidth() / scaleRadio), (int)(cImage.getHeight() / scaleRadio), null);
	 	cImage =  bi;
	 	return cImage;
	}
	
	public void drawTriangle(int x1, int y1, int x2, int y2, int x3, int y3, Color bc) {
		g = cImage.getGraphics();
		Polygon tri = new Polygon();
		tri.addPoint(x1, y1);
		tri.addPoint(x2, y2);
		tri.addPoint(x3, y3);
		g.setColor(bc);
		g.drawPolygon(tri);
		g.dispose();
	}
	
	public void fillPolygon(Polygon polygon, Color color) {
		g = cImage.getGraphics();
		g.setColor(color);
		g.fillPolygon(polygon);
		g.dispose();
	}
	
	public void drawLine(int x1, int y1, int x2, int y2, Color c) {
		g = cImage.getGraphics();
		g.setColor(c);
		g.drawLine(x1, y1, x2, y2);
		g.dispose();
	}
	
	public void drawBorder(int x, int y, int xLen, int yLen, Color color) {
		g = cImage.getGraphics();
		g.setColor(color);
		g.drawRect(x, y, xLen, yLen);
		g.dispose();
	}
	
	public void circleRectangleIamge(BufferedImage aimage, int x, int y, int w, int h, int arcw, int arch) {
		RoundRectangle2D rect = new RoundRectangle2D.Double(x, y, w, h, arcw, arch);
		GeneralPath path = new GeneralPath();
		path.append(rect, false);
		g = cImage.getGraphics();
		g.setClip(path);
		g.drawImage(aimage, x, y, w, h, null);
	}
	
	public void drawRoundRectangle2D(Color color, int x, int y, int w, int h, int rw, int rh) {
		g = cImage.getGraphics();
		g.setColor(color);
		g.drawRoundRect(x, y, w, h, rw, rh);
		g.dispose();
	}
	
	public BufferedImage createImage(InputStream input, int[] imgP, int[] imgS, String[] strs, int[] x, int[] y) throws IOException {
		return createImage(ImageIO.read(input), imgP, imgS, strs, x, y);
	}

	public BufferedImage createImage(String filepath, String[] strs, int[] imgP, int[] imgS, int[] x, int[] y) throws IOException {
		return createImage(ImageIO.read(new File(filepath)), imgP, imgS, strs, x, y);
	}
	
	private BufferedImage createImage(BufferedImage image, int[] imgP, int[] imgS, String[] strs, int[] x, int[] y) {
		if (image != null && imgP != null && imgS != null) {
			if (imgP.length != imgS.length) {
				throw new IllegalArgumentException("(imgP、imgS)参数长度必须相等");
			}
			cImage.getGraphics().drawImage(image, imgP[0], imgP[1], imgS[0], imgS[1], null);
		}
		
		if (strs != null && x != null && y != null) {
			if (x.length != y.length || x.length != strs.length) {
				throw new IllegalArgumentException("(x、y)参数长度必须相等");
			}
			
			for (int i = 0; i < x.length; i++) {
				cImage.getGraphics().drawString(strs[i], x[i], y[i]);
			}
		}
		
		return cImage;
	}

	public void saveIamge(String filePath, String formName) {
		File f = new File(filePath);
		if (!f.getParentFile().exists()) {
			f.getParentFile().mkdirs();
		}
		try {
			ImageIO.write(cImage, formName, f);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public BufferedImage getImage() {
		return cImage;
	}

	public void setImage(BufferedImage image) {
		this.cImage = image;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}
	
	public void setFont(Font font) {
		this.cImage.getGraphics().setFont(font);
	}
	
	public void setColor(Color color) {
		this.cImage.getGraphics().setColor(color);
	}
}
