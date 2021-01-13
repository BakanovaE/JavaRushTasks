package com.javarush.games.snake;

import com.javarush.engine.cell.Game;
import com.javarush.engine.cell.*;

import java.util.ArrayList;
import java.util.List;

public class Snake {
    int x;
    int y;
    private List<GameObject> snakeParts = new ArrayList<>();
    private static final String HEAD_SIGN = "\uD83D\uDC7E";
    private static final String BODY_SIGN = "\u26AB";
    public boolean isAlive = true;
    private Direction direction = Direction.LEFT;
    public int getLength() {
        return snakeParts.size();
    }

    public boolean checkCollision(GameObject gameObject) {
        for (GameObject part : snakeParts) {
            if (part.x == gameObject.x && part.y == gameObject.y) {
                return true;
            }
        }
        return false;
    }
    public GameObject createNewHead() {
        GameObject oldHead = snakeParts.get(0);
        switch (direction) {
            case LEFT:
                return new GameObject(oldHead.x-1, oldHead.y);
            case DOWN:
                return new GameObject(oldHead.x, oldHead.y+1);
                case RIGHT:
                return new GameObject(oldHead.x+1, oldHead.y);

            case UP:
                return new GameObject(oldHead.x, oldHead.y-1);
        }

        return null;
    }
    public void removeTail() {
        snakeParts.remove(snakeParts.size()-1);
    }
    public void move(Apple apple) {
        GameObject newHead = createNewHead();
        if (newHead.x>=SnakeGame.WIDTH || newHead.x<0 || newHead.y>=SnakeGame.HEIGHT || newHead.y <0) {
            isAlive = false;
        } else {
            if (!checkCollision(newHead)) {
            if (newHead.x == apple.x && newHead.y == apple.y) {

                    snakeParts.add(0, newHead);
                    apple.isAlive = false;
            } else {
                    snakeParts.add(0, newHead);
                    removeTail();
                }
            }
            else {
                isAlive = false;
            }
        }
    }
    public void setDirection(Direction direction) {
        if (this.direction==Direction.UP && snakeParts.get(0).y!=snakeParts.get(1).y && (direction == Direction.RIGHT || direction == Direction.LEFT)) {
            this.direction = direction;
        }
        if (this.direction==Direction.DOWN && snakeParts.get(0).y!=snakeParts.get(1).y && (direction == Direction.RIGHT || direction == Direction.LEFT)) {
            this.direction = direction;
        }
        if (this.direction==Direction.LEFT && snakeParts.get(0).x!=snakeParts.get(1).x && (direction == Direction.UP || direction == Direction.DOWN)) {
            this.direction = direction;
        }
        if (this.direction==Direction.RIGHT &&snakeParts.get(0).x!=snakeParts.get(1).x && (direction == Direction.UP || direction == Direction.DOWN)) {
            this.direction = direction;
        }
    }

    public void draw(Game game) {
        for (int i = 0; i < snakeParts.size(); i++) {
            if (isAlive) {
                if (snakeParts.get(i) == snakeParts.get(0)) {
                    game.setCellValueEx(snakeParts.get(0).x, snakeParts.get(0).y, Color.NONE, HEAD_SIGN, Color.BLACK, 75);
                } else {
                    game.setCellValueEx(snakeParts.get(i).x, snakeParts.get(i).y, Color.NONE, BODY_SIGN, Color.BLACK, 75);
                }
            } else if (snakeParts.get(i) == snakeParts.get(0)) {
                game.setCellValueEx(snakeParts.get(0).x, snakeParts.get(0).y, Color.NONE, HEAD_SIGN, Color.RED, 75);
            } else {
                game.setCellValueEx(snakeParts.get(i).x, snakeParts.get(i).y, Color.NONE, BODY_SIGN, Color.RED, 75);
            }
        }
    }
    public Snake(int x, int y) {
        this.x = x;
        this.y = y;
        snakeParts.add(new GameObject(x, y));
        snakeParts.add(new GameObject(x+1, y));
        snakeParts.add(new GameObject(x+2, y));
    }


}
