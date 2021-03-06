package com.sg.syj.common.util;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.swetake.util.Qrcode;

public class QrcodeTool
{
	public static boolean writeFile(String content, String filePath) throws Exception
	{
		// 生成二维码QRCode图片
		try
		{
			BufferedImage bi = qRCodeCommon(content);
			File imgFile = new File(filePath);
			if(!imgFile.exists()){
				imgFile.mkdirs();
			}
			ImageIO.write(bi, "png", imgFile);
			return true;
		} catch (IOException e)
		{
			throw new  Exception(e);
		}
	}

	public static BufferedImage qRCodeCommon(String content)
	{
		BufferedImage bufImg = null;
		try
		{
			Qrcode qrcodeHandler = new Qrcode();
			// 设置二维码排错率，可选L(7%)、M(15%)、Q(25%)、H(30%)，排错率越高可存储的信息越少，但对二维码清晰度的要求越小
			qrcodeHandler.setQrcodeErrorCorrect('H');
			// qrcodeHandler.setStructureappend(13, 16, 123);
			// qrcodeHandler.setQrcodeEncodeMode('B');
			// 设置设置二维码尺寸，取值范围1-40，值越大尺寸越大，可存储的信息越大
			// qrcodeHandler.setQrcodeVersion(size);
			// 获得内容的字节数组，设置编码格式
			byte[] contentBytes = content.getBytes("utf-8");

			if (contentBytes.length > 0 && contentBytes.length < 800)
			{
				boolean[][] codeOut = qrcodeHandler.calQrcode(contentBytes);
				// 图片尺寸
				// int imgSize = 67 + 12 * (size - 1);
				int imgSize = codeOut.length * 3 + 4;
				// System.out.println("图片像素："+imgSize);
				bufImg = new BufferedImage(imgSize, imgSize, BufferedImage.TYPE_INT_RGB);
				Graphics2D gs = bufImg.createGraphics();
				// 设置背景颜色
				gs.setBackground(Color.WHITE);
				gs.clearRect(0, 0, imgSize, imgSize);
				// 设定图像颜色> BLACK
				gs.setColor(Color.BLACK);
				// // 设置偏移量，不设置可能导致解析出错
				int pixoff = 2;
				// System.out.println("内容长度："+contentBytes.length);
				// 输出内容> 二维码
				// System.out.println("码长度"+codeOut.length);
				for (int i = 0; i < codeOut.length; i++)
				{
					for (int j = 0; j < codeOut.length; j++)
					{
						if (codeOut[j][i])
						{
							gs.fillRect(j * 3 + pixoff, i * 3 + pixoff, 3, 3);
						}
					}
				}
				gs.dispose();
				bufImg.flush();
			} else
			{
				throw new Exception("QRCode content bytes length = " + contentBytes.length + " not in [0, 800].");
			}
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		return bufImg;
	}

}
