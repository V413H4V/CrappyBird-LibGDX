package com.libgdx.crappybird.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import java.util.Random;

/**
 * Created by V413H4V on 12/13/2015.
 */
public class Pipe {

    private Texture topPipe, bottomPipe;
    private Vector2 posTopPipe, posBottomPipe;
    private Random rand;
    private static final int FLUCTUATION = 130;
    private static final int PIPE_GAP = 100;
    private static final int LOWEST_OPENING = 120;
    public static final int PIPE_WIDTH = 52;

    private Rectangle topBounds,bottomBounds;

    public Pipe(int x) {
        topPipe = new Texture("toptube.png");
        bottomPipe = new Texture("bottomtube.png");
        rand = new Random();

        posTopPipe = new Vector2(x, rand.nextInt(FLUCTUATION) + PIPE_GAP + LOWEST_OPENING);
        posBottomPipe = new Vector2(x, posTopPipe.y - PIPE_GAP - bottomPipe.getHeight());

        topBounds = new Rectangle(posTopPipe.x, posTopPipe.y, topPipe.getWidth(), topPipe.getHeight());
        bottomBounds = new Rectangle(posBottomPipe.x, posBottomPipe.y, bottomPipe.getWidth(), bottomPipe.getHeight());

    }

    public Texture getTopPipe() {
        return topPipe;
    }

    public Texture getBottomPipe() {
        return bottomPipe;
    }

    public Vector2 getPosTopPipe() {
        return posTopPipe;
    }

    public Vector2 getPosBottomPipe() {
        return posBottomPipe;
    }

    public void reposition(float x){
        posTopPipe.set(x, rand.nextInt(FLUCTUATION) + PIPE_GAP + LOWEST_OPENING);
        posBottomPipe.set(x, posTopPipe.y - PIPE_GAP - bottomPipe.getHeight());
    }

    public Boolean collide(Rectangle player){
        boolean collision;
        collision = player.overlaps(topBounds) || player.overlaps(bottomBounds);
        return collision;
    }
}
