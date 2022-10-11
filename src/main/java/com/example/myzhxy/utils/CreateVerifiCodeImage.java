package com.example.myzhxy.utils;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

/**
 * 验证码图片工具类
 *
 * @Author hongxiaobin
 * @Time 2022/10/11-11:09
 */
public class CreateVerifiCodeImage {
    private static int WIDTH = 90;
    private static int HEIGHT = 35;
    private static int FONT_SIZE = 20;
    private static char[] verifiCode;
    private static BufferedImage verifiCodeImage;

    /**
     * 获取验证码图片
     *
     * @Param: no
     * @Return: BufferedImage
     */
    public static BufferedImage getVerifiCodeImage() {
        verifiCodeImage = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_BGR);
        Graphics graphics = verifiCodeImage.getGraphics();
        verifiCode = generateCheckCode();
        drawBackground(graphics);
        graphics.dispose();
        return verifiCodeImage;
    }

    /**
     * 获取验证码
     *
     * @Param: no
     * @Return: char[]
     */
    private static char[] getVerifiCode() {
        return verifiCode;
    }

    /**
     * 生成随机验证码
     *
     * @Param: no
     * @Return: char[]
     */
    private static char[] generateCheckCode() {
        String chars = "0123456789abcdefghijklmnopqrstuvwxyz" + "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        char[] rands = new char[4];
        for (int i = 0; i < 4; i++) {
            int rand = (int) (Math.random() * (10 + 26 * 2));
            rands[i] = chars.charAt(rand);
        }
        return rands;
    }

    /**
     * 绘制验证码
     *
     * @Param: Graphics g, char[] rands
     * @Return: void
     */
    private static void drawRands(Graphics g, char[] rands) {
        g.setFont(new Font("Console", Font.BOLD, FONT_SIZE));
        for (int i = 0; i < rands.length; i++) {
            g.setColor(getRandomColor());
            g.drawString("" + rands[i], i * FONT_SIZE + 10, 25);
        }
    }

    /**
     * 绘制验证码图片背景
     *
     * @Param: Graphics g
     * @Return: void
     */
    private static void drawBackground(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, WIDTH, HEIGHT);
//        绘制干扰点
        for (int i = 0; i < 200; i++) {
            int x = (int) (Math.random() * WIDTH);
            int y = (int) (Math.random() * HEIGHT);
            g.setColor(getRandomColor());
            g.drawOval(x, y, 1, 1);
        }
    }

    /**
     * 生成随机颜色
     *
     * @Param: no
     * @Return: Color
     */
    private static Color getRandomColor() {
        Random random = new Random();
        return new Color(random.nextInt(220), random.nextInt(220), random.nextInt(220));
    }
}
