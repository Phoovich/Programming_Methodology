package logic.stone;

import java.util.ArrayList;

import logic.game.GameManager;

public class Crystal extends HardStone {
	private int value;

	public Crystal(int posX, int posY) {
		this(posX, posY, 10);
	}

	public Crystal(int posX, int posY, int value) {
		super(posX, posY, 5);
		setValue(value);
	}

	public void dig(int digPower) {
		super.dig(digPower);
		int score = GameManager.getInstance().getGameScore();

		GameManager.getInstance().setGameScore(score + 1);
	}
	
	public void destroy() {
		super.destroy();
		int score = GameManager.getInstance().getGameScore();

		GameManager.getInstance().setGameScore(score + value);
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		if (value < 1)
			value = 1;
		this.value = value;
	}

}
