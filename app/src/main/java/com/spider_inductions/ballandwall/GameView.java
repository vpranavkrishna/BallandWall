package com.spider_inductions.ballandwall;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.media.AudioAttributes;
import android.media.SoundPool;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
public class GameView extends View {
    private static final String TAG = "GameView";
    private static final int ball_radius = 20;
    private static final int wall_height1 = 300;
    private static final int wall_height2 = 400;
    private static final int wall_width = 50;
    private int dx = 1;
    private int dx2 =1;
    private int inc =0;
    private int cx =200;
    private int cy= getHeight()*3/4 -ball_radius;
    private Rect rect1 = new Rect();
    private Rect rect2 = new Rect();
    private Rect rect3 = new Rect();
    private Rect rect4 = new Rect();
    private Paint paint = new Paint();
    private int taps= 0;
    private int start =0;
    private boolean touched =false;
    private boolean stop = false;
    private long starttime;
    private long stopTime;
    private datatransfer game;
    private int pass =0 ;
    AudioAttributes audioAttributes = new AudioAttributes.Builder().setUsage(AudioAttributes.USAGE_GAME).setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION).build();
    SoundPool soundPool = new SoundPool.Builder().setMaxStreams(3).setAudioAttributes(audioAttributes).build();
    int sound = soundPool.load(getContext(), R.raw.ping_pong_8bit_beeep, 1);


    public GameView(Context context) {
        super(context);
    }
    public GameView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }
    public GameView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
    @Override
    protected void onDraw(Canvas canvas)
    {
        if(start ==0)
        {
            cy = getHeight()*3/4 - ball_radius;
            starttime = System.currentTimeMillis();
        }

        if(!stop) {
            gamerect1();
            if (inc > 200) {
                if(inc>1000 && inc<2000)
                {
                    dx =dx +5;
                    dx2 = dx2 +5;
                }
                else if(inc>2000)
                {
                    dx=dx+10;
                    dx2 = dx2 +10;
                    Log.d(TAG, "onDraw: elseif");
                }
                gamerect2();
            }

            if (touched && cy > (getHeight()*3/4 - 500)) {
                cy = cy - 10;


            } else if (!touched && cy < getHeight()*3/4 - 20) {
                cy = cy + 10;

            } else {
                touched = false;
            }
            paint.setColor(Color.WHITE);
            canvas.drawCircle(cx, cy, ball_radius, paint);
            canvas.drawRect(rect1, paint);
            canvas.drawRect(rect2, paint);
            canvas.drawRect(rect3, paint);
            canvas.drawRect(rect4, paint);
            game.update(stop,taps);
            invalidate();
        }
        }


    private void gamerect2() {

        if (rect2.right >= 0) {
            rect2.set(getWidth() - wall_width - dx2, getHeight()*3/4 - wall_height2, getWidth() - dx2, getHeight()*3/4);
        } else {
                dx2 = 1;
            rect2.set(getWidth() - wall_width - dx2, getHeight()*3/4 - wall_height2, getWidth() - dx2, getHeight()*3/4);
        }
        if(rect1.contains(cx,cy))
        {
            Log.d(TAG, "gamerect2:true ");
            stop = true;
            stopTime = System.currentTimeMillis();
//            game.update(stopTime);
        }

        dx2 = dx2 + 5;

    }

    private void gamerect1() {

        if(rect1.right>=0)
        {
            rect1.set(getWidth()-wall_width-dx,getHeight()*3/4-wall_height1,getWidth() -dx,getHeight()*3/4);
        }
        else
        {
                dx = 1;
            rect1.set(getWidth()-wall_width-dx,getHeight()*3/4-wall_height1,getWidth() -dx,getHeight()*3/4);
        }
        if(rect2.contains(cx,cy))
        {
            Log.d(TAG, "gamerect2:true ");
            stopTime = System.currentTimeMillis();
//            game.update(stopTime);
            stop = true;
        }
        dx = dx+5;
        inc++;
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(event.getAction()== MotionEvent.ACTION_DOWN)
        {
            start =1;
            touched = true;
            taps++;
            soundPool.play(sound, 1, 1, 0, 0, 1);
        }

        invalidate();
        return true;
    }
    public void intializeinterface(datatransfer game) {
        this.game = game;
    }

    public interface datatransfer {
        void update(boolean stop, int taps);
    }
}
