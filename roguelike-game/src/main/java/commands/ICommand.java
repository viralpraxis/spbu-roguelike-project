package commands;

public interface ICommand {
    public void execute();

    default void undo() {
        throw new UnsupportedOperationException();
    }
}
