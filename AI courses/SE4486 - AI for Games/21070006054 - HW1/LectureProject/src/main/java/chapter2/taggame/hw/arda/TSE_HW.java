package chapter2.taggame.hw.arda;

import chapter2.*;
import chapter2.taggame.TagArena;
import chapter2.taggame.TagPlayer;
import chapter2.taggame.TagSteeringEngine;
import math.geom2d.Vector2D;

import java.util.List;
import java.util.Objects;

/**
 * TODO: Implement a tag steering engine generating the steering behaviors for a tag player
 *      ! Note that you should implement SteeringBehavior classes to be generated in this class
 *      ! The implemented steering behaviors are supposed to be returning Dynamic Accelearitons.
 *      ! NOT Kinematic.
 */
class DynamicSeek implements SteeringBehavior {

    TagPlayer playerTarget;

    public DynamicSeek(TagPlayer playerTarget){
        this.playerTarget = playerTarget;
    }

    @Override
    public Acceleration getSteering(StaticInfo staticInfo, Velocity velocity) {
        Vector2D playerMePos = staticInfo.getPos();
        Velocity playerMeVelocity = velocity;

        Vector2D playerTargetPos = playerTarget.getStaticInfo().getPos();
        Velocity playerTargetVelocity = playerTarget.getVelocity();
        Vector2D playerTargetVelocityLinear = playerTargetVelocity.getLinear();
        double playerTargetVelocityAngular = playerTargetVelocity.getAngular();
        double playerTargetAngle = playerTarget.getStaticInfo().getOrientation();

        double playerTargetVelocityDest_x = playerTargetVelocityLinear.x() * Math.cos(playerTargetAngle) - playerTargetVelocityLinear.y() * Math.sin(playerTargetAngle);
        double playerTargetVelocityDest_y = playerTargetVelocityLinear.x() * Math.sin(playerTargetAngle) + playerTargetVelocityLinear.y() * Math.cos(playerTargetAngle);

        playerTargetVelocityLinear.plus(new Vector2D(playerTargetVelocityDest_x, playerTargetVelocityDest_y));

        Vector2D targetDest = new Vector2D(playerTargetPos.x() + playerTargetVelocityLinear.x(),playerTargetPos.y() + playerTargetVelocityLinear.y());

        Vector2D playerMeNewVelocityLinear = targetDest.minus(playerMePos);


        Vector2D accelaration = playerMeNewVelocityLinear.minus(new Vector2D(playerMeVelocity.getLinear().x(), playerMeVelocity.getLinear().y()));


        // I used 5, since at MovingEntity MAX_SPEED is mentioned as 5 but cant be accessed since it's private
        if(accelaration.norm() > 5)
            accelaration = accelaration.normalize();

        double angle = calculateAngle(playerMeVelocity.getLinear().x(), playerMeVelocity.getLinear().y(),
                playerMeNewVelocityLinear.x(), playerMeNewVelocityLinear.y());

        return new Acceleration(accelaration, angle, AccelerationType.Dynamic);
    }

    public double calculateAngle(double x1, double y1, double x2, double y2) {

        double dotProduct = x1 * x2 + y1 * y2;

        double magnitudeV1 = Math.sqrt(x1 * x1 + y1 * y1);
        double magnitudeV2 = Math.sqrt(x2 * x2 + y2 * y2);


        double cosTheta = dotProduct / (magnitudeV1 * magnitudeV2);

        double angleInRadians = Math.acos(cosTheta);
        return angleInRadians;
    }
}

class Flee implements SteeringBehavior{

    public Flee(){
    }

    @Override
    public Acceleration getSteering(StaticInfo staticInfo, Velocity velocity) {

        Vector2D velocityLin = new Vector2D(700, 500).minus(staticInfo.getPos());
        Vector2D accelaration = velocityLin.minus(velocity.getLinear());

        // I used 5, since at MovingEntity MAX_SPEED is mentioned as 5 but cant be accessed since it's private
        if(accelaration.norm() > 5)
            accelaration = accelaration.normalize();

        return new Acceleration(accelaration, 0.0, AccelerationType.Dynamic);

    }
}

public class TSE_HW implements TagSteeringEngine {
    @Override
    public SteeringBehavior getSteeringBehavior(TagPlayer player, TagArena arena) {

        if(player.isTag()){
            TagPlayer playerTarget = arena.getPlayers().get(pickTarget(player, arena));

            return new DynamicSeek(playerTarget);
        }

        else {
            return new Flee();
        }
    }

    public TagPlayer findTaggedlayer(TagPlayer player, TagArena arena){
        TagPlayer playerTag = null;
        for (TagPlayer tp : arena.getPlayers()) {
            if (Objects.equals(tp, player))
                continue;
            if (tp.isTag()){
                playerTag = tp;
                break;
            }
        }
        return playerTag;
    }

    public double calculateDistance(TagPlayer playerMe, TagPlayer playerOther) {
        Vector2D playerOther2D = playerOther.getStaticInfo().getPos();
        Vector2D playerMe2D = playerMe.getStaticInfo().getPos();

        Vector2D direction = playerOther2D.minus(playerMe2D);

        double distance = direction.norm();
        return distance;
    }

    public int calculateDistances(TagPlayer playerMe, TagArena arena){
        List<TagPlayer> playersList = arena.getPlayers();
        double lowestDistance = 999999999;
        int lowestIndex = -1;

        for (int i = 0; i < playersList.size(); i++) {
            TagPlayer playerOther = playersList.get(i);

            if(Objects.equals(playerMe, playerOther))
                continue;

            double distance = calculateDistance(playerMe, playerOther);
            if(distance < lowestDistance){
                lowestDistance = distance;
                lowestIndex = i;
            }
        }

        return lowestIndex;
    }

    public TagPlayer calculateLowestDistance(TagPlayer playerMe, TagArena arena) {
        return arena.getPlayers().get(calculateDistances(playerMe, arena));
    }


    public int pickTarget(TagPlayer playerMe, TagArena arena){

        int maxIndex = -1;
        int maxScore = -999999;
        List<TagPlayer> playersList = arena.getPlayers();

        for (int i = 0; i < playersList.size(); i++) {
            TagPlayer player = playersList.get(i);

            if(player.score() > maxScore){
                maxScore = player.score();
                maxIndex = i;
            }

        }

        return maxIndex;
    }
}
