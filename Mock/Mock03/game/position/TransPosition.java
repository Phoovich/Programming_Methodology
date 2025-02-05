package game.position;

import game.control.State;

public class TransPosition {
    private final Position from;
    private final Position to;
    private final Status status;

    public TransPosition(Position from, Position to, Status status) {
        this.from = from;
        this.to = to;
        this.status = status;
    }

    public TransPosition(Position from, Position to) {
        this.from = from;
        this.to = to;
        this.status = Status.NORMAL;
    }

    @Override
    public String toString() {
        return "Transition Move from " + from + " to " + to + ". With status: " + status;
    }

    public Position getFrom() {
        return from;
    }

    public Position getTo() {
        return to;
    }

    public Status getStatus() {
        return status;
    }
}
