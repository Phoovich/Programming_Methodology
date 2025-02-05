package logic.stone;

import utils.GameUtilities;

// Already implemented
public class Stone {

    protected int posX;
    protected int posY;

    public Stone(int posX, int posY){
        this.posX = posX;
        this.posY = posY;
    }

    public void dig(int digPower) {
        destroy();
    }

    public void destroy() {
        GameUtilities.removeStone(this);
    }

    public int getPosX() {
        return posX;
    }

    public int getPosY(){
        return posY;
    }

    public void setPosX(int x){ this.posX = x; }

    public void setPosY(int y){ this.posY = y; }

}
