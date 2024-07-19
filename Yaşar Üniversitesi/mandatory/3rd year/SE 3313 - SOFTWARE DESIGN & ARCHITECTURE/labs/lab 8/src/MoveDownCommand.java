public class MoveDownCommand implements Command{
    GameCharacter character;

    @Override
    public void execute() {
        character.moveDown();
    }

    public MoveDownCommand(GameCharacter character){
        this.character = character;
    }
}
