package com.libgdx.crappybird.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.libgdx.crappybird.crappyBird;
import com.libgdx.crappybird.sprites.Bird;
import com.libgdx.crappybird.sprites.Pipe;

/**
 * Created by V413H4V on 12/13/2015.
 */
public class PlayState extends State {

    private Bird bird;
    private Texture bg;
    private Array<Pipe> pipes;
    private static final int PIPE_SPACING = 125;
    private static final int PIPE_COUNT = 4;

    public PlayState(GameStateManager gsm) {
        super(gsm);
        bird = new Bird(50,300);
        cam.setToOrtho(false, crappyBird.WIDTH/2 , crappyBird.HEIGHT/2);
        bg = new Texture("bg.png");
        pipes = new Array<Pipe>();

        for(int i=1; i <= PIPE_COUNT; i++){
            pipes.add(new Pipe(i * (PIPE_SPACING + Pipe.PIPE_WIDTH)));
        }
    }

    @Override
    protected void handleInput() {
        if(Gdx.input.justTouched()){
            bird.jump();
        }
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(cam.combined);
        sb.begin();
        sb.draw(bg, cam.position.x - (cam.viewportWidth / 2), 0);
        sb.draw(bird.getTexture(),bird.getPosition().x,bird.getPosition().y);
        for(Pipe pipe : pipes){
            sb.draw(pipe.getTopPipe(),pipe.getPosTopPipe().x,pipe.getPosTopPipe().y);
            sb.draw(pipe.getBottomPipe(), pipe.getPosBottomPipe().x, pipe.getPosBottomPipe().y);


        }
        sb.end();

    }

    @Override
    public void update(float dt) {
        handleInput();
        bird.update(dt);
        cam.position.x = bird.getPosition().x + 80; //offset of 80 just to get Camera slightly ahead of Bird

        for(Pipe pipe : pipes){
            if((pipe.getPosTopPipe().x + pipe.getTopPipe().getWidth()) < (cam.position.x - (cam.viewportWidth/2))){
                pipe.reposition(pipe.getPosTopPipe().x + (Pipe.PIPE_WIDTH + PIPE_SPACING) * PIPE_COUNT); //Doubt <-----
            }
            if(pipe.collide(bird.getBounds())){
                gsm.set(new MenuState(gsm));
            }
        }

        cam.update();
    }

    @Override
    public void dispose() {

    }

}
