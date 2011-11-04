package com.boogiesoftware.hackdays.test;

/**
 * Created by IntelliJ IDEA.
 * User: kukkula
 * Date: 11/3/11
 * Time: 11:33 PM
 * To change this template use File | Settings | File Templates.
 */
import org.jbox2d.collision.AABB;  
import org.jbox2d.collision.shapes.CircleShape;
import org.jbox2d.collision.shapes.PolygonShape;

import org.jbox2d.common.Vec2;  
import org.jbox2d.dynamics.Body;  
import org.jbox2d.dynamics.BodyDef;  
import org.jbox2d.dynamics.World;  
  
import android.util.Log;
import org.jbox2d.pooling.WorldPool;

public class PhysicsWorld {  
    public int targetFPS = 40;  
    public int timeStep = (1000 / targetFPS);  
    public int iterations = 5;  
  
    private Body[] bodies = new Body[107];
    private int count = 0;  

    private World world;  
    private BodyDef groundBodyDef;  
    private PolygonShape groundShapeDef;
  
    public void create() {  
        // Step 1: Create Physics World Boundaries  

  
        // Step 2: Create Physics World with Gravity  
        Vec2 gravity = new Vec2((float) -10.0, (float) -10.0);
        boolean doSleep = true;  
        world = new World(gravity, doSleep);
  
        // Step 3: Create Ground Box  
        groundBodyDef = new BodyDef();  
        groundBodyDef.position.set(new Vec2((float) 0.0, (float) -10.0));  
        Body groundBody = world.createBody(groundBodyDef);  
        groundShapeDef = new PolygonShape();
        groundShapeDef.setAsBox((float) 50.0, (float) 10.0);  
        groundBody.createFixture(groundShapeDef, 1.0f);
    }  
  
    public void addBall() {  
        // Create Dynamic Body  
        BodyDef bodyDef = new BodyDef();  
        bodyDef.position.set((float) 6.0+count, (float) 24.0);  
        bodies[count] = world.createBody(bodyDef);  
  
        // Create Shape with Properties  
        CircleShape circle = new CircleShape();
        circle.m_radius = (float) 1.8;

        //circle.density = (float) 1.0;
  
        // Assign shape to Body  
        bodies[count].createFixture(circle, 1.0f);

        bodies[count].m_mass = 10;
  
        // Increase Counter  
        count += 1;  
    }  
  
    public void update() {  
        // Update Physics World  
        world.step(1,timeStep, iterations);
  
        // Print info of latest body
        for (int i = 0; i < bodies.length; i++) {
            Body body = bodies[i];
            if(body != null) {
                Vec2 position = body.getPosition();
                float angle = body.getAngle();
                Log.v("Physics Test", "Pos: (" + position.x + ", " + position.y + "), Angle: " + angle);
            }
        }
    }
}
