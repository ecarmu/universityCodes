public class MoveLeftCommand implements Command{
    GameCharacter character;

    @Override
    public void execute() {
        character.moveLeft();
    }

    public MoveLeftCommand(GameCharacter character){
        this.character = character;
    }
}
