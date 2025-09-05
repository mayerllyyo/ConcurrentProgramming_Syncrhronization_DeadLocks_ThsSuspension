package edu.eci.arsw.highlandersim;

import java.util.List;
import java.util.Random;

public class Immortal extends Thread {

    private ImmortalUpdateReportCallback updateCallback=null;
    
    private int health;
    
    private int defaultDamageValue;

    private final List<Immortal> immortalsPopulation;

    private final String name;

    private final Random r = new Random(System.currentTimeMillis());

    public volatile boolean paused=false;

    public volatile boolean IsActuallyPaused=false;

    public volatile boolean isDead=false;

    

    public Immortal(String name, List<Immortal> immortalsPopulation, int health, int defaultDamageValue, ImmortalUpdateReportCallback ucb) {
        super(name);
        this.updateCallback=ucb;
        this.name = name;
        this.immortalsPopulation = immortalsPopulation;
        this.health = health;
        this.defaultDamageValue=defaultDamageValue;
    }

    public void run() {

        while (true) {

            if (this.isDead) {
                break; 
            }

            if(paused){
                synchronized(this){
                    try {
                        IsActuallyPaused=true;
                        wait();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }

            Immortal im;

            

            int myIndex = immortalsPopulation.indexOf(this);

            int nextFighterIndex = r.nextInt(immortalsPopulation.size());

            //avoid self-fight
            if (nextFighterIndex == myIndex) {
                nextFighterIndex = ((nextFighterIndex + 1) % immortalsPopulation.size());
            }

            im = immortalsPopulation.get(nextFighterIndex);

            if (!im.isDead) {
                this.fight(im);
            }

            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

    }

    public void fight(Immortal i2) {
    Immortal first = this;
    Immortal second = i2;

        if (first.name.compareTo(second.name) > 0) {
            first = i2;
            second = this;
        }

        synchronized (first) {
            synchronized (second) {
                if (i2.getHealth() > 0) {
                    i2.changeHealth(i2.getHealth() - defaultDamageValue);
                    this.health += defaultDamageValue;
                    if(i2.getHealth()<=0){
                        i2.isDead = true; 
                    }
                    updateCallback.processReport("Fight: " + this + " vs " + i2 + "\n");
                } else {
                    updateCallback.processReport(this + " says:" + i2 + " is already dead!\n");
                }
            }
            if (this.health <= 0) {
                this.isDead = true;
            }
        }
    }

    public void changeHealth(int v) {
        health = v;
    }

    public int getHealth() {
        return health;
    }

    @Override
    public String toString() {

        return name + "[" + health + "]";
    }

    public void pause(){
        paused=true;

    }
    public void resumeImmortal(){
        paused = false;
        IsActuallyPaused=false;
        synchronized(this){
            notifyAll();
        }
    }

}
