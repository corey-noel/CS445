
public class SimpleRGB {

    private int[][][] img;
    private int width, height;

    public SimpleRGB(int aWidth, int aHeight) {
        img = new int[aWidth][aHeight][3];
        width = aWidth;
        height = aHeight;

        for (int i = 0; i < width; i++)
            for (int j = 0; j < height; j++)
                for (int k = 0; k < 3; k++)
                    img[i][j][k] = 0;
    }

    /**
     * Gets the width of this image.
     * @return the width of this image.
     */
    public int getWidth() { return width; }

    /**
     * Gets the height of this image.
     * @return the height of this image.
     */
    public int getHeight() { return height; }

    /**
     * Sets the red value at coordinate (x,y) to aRed.
     * @param x the x coordinate of this image.
     * @param y the y coordinate of this image.
     * @param aRed the red value (0 - 255)
     */
    public void setRed(int x, int y, int aRed) { img[x][y][0] = aRed % 256; }

    /**
     * Sets the green value at coordinate (x,y) to aGreen.
     * @param x the x coordinate of this image.
     * @param y the y coordinate of this image.
     * @param aGreen the green value (0 - 255)
     */
    public void setGreen(int x, int y, int aGreen) { img[x][y][1] = aGreen % 256; }

    /**
     * Sets the blue value at coordinate (x,y) to aBlue.
     * @param x the x coordinate of this image.
     * @param y the y coordinate of this image.
     * @param aBlue the blue value (0 - 255)
     */
    public void setBlue(int x, int y, int aBlue) { img[x][y][2] = aBlue % 256; }

    /**
     * Gets the red value at coordinate (x,y).
     * @param x the x coordinate of this image.
     * @param y the y coordinate of this image.
     * @return the value of red at coordinate (x,y).
     */
    public int getRed(int x, int y) { return img[x][y][0]; }

    /**
     * Gets the green value at coordinate (x,y).
     * @param x the x coordinate of this image.
     * @param y the y coordinate of this image.
     * @return the value of green at coordinate (x,y).
     */
    public int getGreen(int x, int y) { return img[x][y][1]; }

    /**
     * Gets the blue value at coordinate (x,y).
     * @param x the x coordinate of this image.
     * @param y the y coordinate of this image.
     * @return the value of blue at coordinate (x,y).
     */
    public int getBlue(int x, int y) { return img[x][y][2]; }

    /**
     * Get the NEW image containing only the red color.
     * The red values of this new image should be exactly
     * the same as red value of this image. The green and
     * blue values of this new image should be 0s.
     * @return the NEW image (SimpleRGB) containing only
     * the red color of this image.
     */
    public SimpleRGB getRedImage() {
        SimpleRGB newImg = new SimpleRGB(width, height);
        for (int i = 0; i < width; i++)
            for (int j = 0; j < height; j++)
                newImg.setRed(i, j, this.getRed(i, j));

        return newImg;
    }

    /**
     * Get the NEW image containing only the green color.
     * The green values of this new image should be exactly
     * the same as green value of this image. The red and
     * blue values of this new image should be 0s.
     * @return the NEW image (SimpleRGB) containing only
     * the green color of this image.
     */
    public SimpleRGB getGreenImage() {
        SimpleRGB newImg = new SimpleRGB(width, height);
        for (int i = 0; i < width; i++)
            for (int j = 0; j < height; j++)
                newImg.setGreen(i, j, this.getGreen(i, j));

        return newImg;
    }

    /**
     * Get the NEW image containing only the blue color.
     * The blue values of this new image should be exactly
     * the same as blue value of this image. The red and
     * green values of this new image should be 0s.
     * @return the NEW image (SimpleRGB) containing only
     * the blue color of this image.
     */
    public SimpleRGB getBlueImage() {
        SimpleRGB newImg = new SimpleRGB(width, height);
        for (int i = 0; i < width; i++)
            for (int j = 0; j < height; j++)
                newImg.setBlue(i, j, this.getBlue(i, j));

        return newImg;
    }

    /**
     * Get the NEW image representing the greyscale of this
     * image. The grey colors are colors that the red, green
     * and blue value are exactly the same. To convert an RGB
     * image into a greyscale image, use the following formula
     * to calculate the new value.
     *    (0.21 * red) + (0.72 * green) + (0.07 * blue)
     * For example, suppose the (R,G,B) value of this image at
     * coordinate (10,20) are (10,100,200), since
     *    (0.21 * 10) + (0.72 * 100) + (0.07 * 200) = 88
     * the (R,G,B) value of the new greyscale image at (10,20)
     * should be (88,88,88).
     * @return the NEW image representing the greyscale of this
     * image.
     */
    public SimpleRGB getGreyImage() {
        SimpleRGB newImg = new SimpleRGB(width, height);
        int grayVal;

        for (int i = 0; i < width; i++)
            for (int j = 0; j < height; j++) {
                grayVal = (int)(
                    this.getRed(i, j) * (.21) +
                    this.getGreen(i, j) * (.72) +
                    this.getBlue(i, j) * (.07));
                newImg.setRed(i, j, grayVal);
                newImg.setGreen(i, j, grayVal);
                newImg.setBlue(i, j, grayVal);
            }

        return newImg;
    }
}