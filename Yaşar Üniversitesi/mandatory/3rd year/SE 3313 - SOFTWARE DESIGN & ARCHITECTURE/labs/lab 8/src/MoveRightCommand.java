public class MoveRightCommand implements Command{
    GameCharacter character;

    @Override
    public void execute() {
        character.moveRight();
    }

    public MoveRightCommand(GameCharacter character){
        this.character = character;
    }
}
