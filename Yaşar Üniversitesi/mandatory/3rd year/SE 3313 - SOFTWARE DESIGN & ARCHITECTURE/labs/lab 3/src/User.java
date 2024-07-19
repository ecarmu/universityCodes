import java.util.Map;

public class User implements Observer{

    public String name;

    public User(String name, MemberShip memberShip) {
        this.name = name;
        this.memberShip = memberShip;
    }

    public MemberShip memberShip;

    @Override
    public void update(String channelName) {

        if(memberShip == MemberShip.NORMAL)
        System.out.println("Wake up " + name + " !! " + channelName + " uploaded a new video");
        if(memberShip == MemberShip.PREMUIM)
            System.out.println("Wake up " + name + " !! " + channelName + " uploaded a new video without ads for members");
    }
}
