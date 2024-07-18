public class AttackCommand implements Command{
    GameCharacter character;
    @Override
    public void execute() {
        character.attack();
    }

    public AttackCommand(GameCharacter character){
        this.character = character;
    }
}
